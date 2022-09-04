package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class OrderDAOImpl implements OrderDAO {
	
	ProductDAO productDAO = new ProductDAOImpl();

	/**
	 * 주문하기
	 * 	1) Orders 테이블에 insert
	 *  2) Order_detail 테이블에 insert
	 *  3) 주문옵션 테이블에 insert
	 * */
	@Override
	public int insertOrder(Orders order) throws SQLException, AddException, NotFoundException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into orders values(ORDER_NO_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?)";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
	
			ps = con.prepareStatement(sql);
			ps.setInt(1, getTotalPrice(order));
			ps.setInt(2, getTotalQty(order));
			ps.setString(3, order.getUserId());
			ps.setString(4, order.getCouponCode());
			result = ps.executeUpdate();
			
			if(result ==0) {
				con.rollback();
			} 
			else{
				int [] orderLineResult = insertOrderLine(con, order);
				for(int i : orderLineResult) {
					if (i != 1) {
						con.rollback();
						throw new AddException("주문 상세를 등록할 수 없습니다.");
					}
				
				}
				
				con.commit();
			}
			
		} finally {
			con.commit();
			DbUtil.dbClose(con, ps, null);
		}
		
		return result;
	}
	
	public int[] insertOrderLine(Connection con, Orders order) throws SQLException, AddException{
		PreparedStatement ps = null;
		String sql = "insert into order_detail values(OD_NO_SEQ.NEXTVAL, ?, ?,  ORDER_NO_SEQ.CURRVAL, ?, ?)";
		int result [] = null;
		int count = 0;
		
		try{
			ps = con.prepareStatement(sql);
			for (OrderLine orderLine : order.getOrderLinelist()) {
				Product product = productDAO.productSelectByproductCode(orderLine.getProductCode());
				
				ps.setInt(1, orderLine.getOrderQty());
				ps.setInt(2, orderLine.getOrderQty()*product.getProductPrice());
				ps.setString(3, product.getProductCode());
				ps.setString(4, product.getCategoryCode());
				
				int orderLineResult = ps.executeUpdate();
				if(orderLineResult==0) {
					con.rollback();
					throw new AddException("주문 상세 등록 실패입니다.");
				}
				else {
					result[count++] = orderLineResult;
					int optionResult = insertOrderOption(con, orderLine);
					if(optionResult == 0){
							con.rollback();
							throw new AddException("주문 옵션을 등록할 수 없습니다.");
					}
				}
			}
			
		} finally {
			DbUtil.dbClose(null, ps, null);
		}
		
		return result;
	}
	
	public int insertOrderOption(Connection con, OrderLine orderLine) throws SQLException {
		PreparedStatement ps = null;
		String coffeOptionSql = "insert coffee_order_option values(COO_NO_SEQ.NEXTVAL, OD_NO_SEQ.CURRVAL, ?, ?, ?)";
		String dessertOptionSql = "insert DESSERT_ORDER_OPTION values(DOO_NO_SEQ.NEXTVAL, OD_NO_SEQ.CURRVAL, ?, ?)";
		int result = 0;
		
		try {
			String productCode = orderLine.getProductCode();
			if(productCode.toUpperCase().equals("D")) {
				ps = con.prepareStatement(dessertOptionSql);
				ps.setInt(1, orderLine.getDessertOption().getHotQty());
				ps.setInt(2, orderLine.getDessertOption().getCheeseQty());
			}
			else {
				ps = con.prepareStatement(coffeOptionSql);
				ps.setInt(1, orderLine.getCoffeOption().getShotQty());
				ps.setInt(2, orderLine.getCoffeOption().getCreamQty());
				ps.setInt(3, orderLine.getCoffeOption().getSyrupQty());
			}
			result = ps.executeUpdate();			
			
		} finally {
			DbUtil.dbClose(null, ps, null);
		}
  		return result;
	}
	
	/**
	 * 상품 총 구매 금액 
	 * @throws SQLException 
	 * @throws NotFoundException 
	 * */
	public int getTotalPrice(Orders order) throws SQLException, NotFoundException {
		List<OrderLine> orderLineList = order.getOrderLinelist();
		int totalPrice = 0;
		for(OrderLine orderLine : orderLineList) {
			Product product = productDAO.productSelectByproductCode(orderLine.getProductCode());
			if (product == null) throw new NotFoundException("상품번호 오류입니다.");
			totalPrice += orderLine.getOrderQty()*product.getProductPrice();
		}
		return totalPrice;
	}
	
	/**
	 * 상품 총 구매 수량 구하기
	 * @throws SQLException 
	 * @throws NotFoundException 
	 * */
	public int getTotalQty(Orders order) throws SQLException, NotFoundException {
		List<OrderLine> orderLineList = order.getOrderLinelist();
		int totalQty = 0;
		for(OrderLine orderLine : orderLineList) {
			totalQty += orderLine.getOrderQty();
		}
		return totalQty;
	}
	
	
	/**
	 * 주문 내역 보기 - 고객
	 * */
	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();
		String sql = "select * from orders where m_id = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Orders orders = new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
						rs.getString(4), rs.getString(5), rs.getString(6));
				
				List<OrderLine> orderLineList = selectOrderLine(con, orders.getOrderNo());
				orders.setOrderLinelist(orderLineList);
				list.add(orders);
			}
			
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * 주문 상세 가져오기
	 * */
	public List<OrderLine> selectOrderLine(Connection con, int orderNo)throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<OrderLine> list = new ArrayList<OrderLine>();
		 try {
		   ps= con.prepareStatement("select * from order_detail where order_no=?");
		   ps.setInt(1, orderNo);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	OrderLine orderLine  = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
	        			rs.getInt(4), rs.getString(5), rs.getString(6));
	        	String productCode = orderLine.getProductCode();
	        	if (productCode.toUpperCase().equals("D")) {
	        		DesertOption dessertOpion = selectDessertOption(con, orderLine);
	        		orderLine.setDessertOption(dessertOpion);
	        	}
	        	else {
	        		CoffeeOption coffeeOption = selectCoffeeOption(con, orderLine);
	        		orderLine.setCoffeOption(coffeeOption);
	        	}
	        	list.add(orderLine);
	        }
    }finally {
    	DbUtil.dbClose(null, ps, rs);
    }
		return list;
    }
	
	/**
	 * 디저트 옵션 가져오기
	 * @throws SQLException 
	 * */
	private DesertOption selectDessertOption(Connection con, OrderLine orderLine) throws SQLException {
		PreparedStatement ps=null;
		ResultSet rs=null;
		DesertOption dessertOption = null;
		String sql = "select * from DESSERT_ORDER_OPTION where OD_NO = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderLine.getOrderLineNo());
			rs = ps.executeQuery();
			if (rs.next()) {
				dessertOption = new DesertOption(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4));
			}
			
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		
		return dessertOption;
	}

	/**
	 * 주문 옵션 가져오기
	 * @throws SQLException 
	 * */
	public CoffeeOption selectCoffeeOption(Connection con, OrderLine orderLine) throws SQLException {
		PreparedStatement ps=null;
		ResultSet rs=null;
		CoffeeOption coffeeOption = null;
		String sql = "select * from COFFEE_ORDER_OPTION where OD_NO = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderLine.getOrderLineNo());
			rs = ps.executeQuery();
			if (rs.next()) {
				coffeeOption = new CoffeeOption(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4), rs.getInt(5));
			}
			
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		
		return coffeeOption;
		
	}
	


	/**
	 * 관리자 주문 내역 보기
	 * */
	@Override
	public List<Orders> selectAllOrders() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();
		String sql = "select * from orders";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Orders orders = new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
						rs.getString(4), rs.getString(5), rs.getString(6));
				
				List<OrderLine> orderLineList = selectOrderLine(con, orders.getOrderNo());
				orders.setOrderLinelist(orderLineList);
				list.add(orders);
			}
			
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

}
