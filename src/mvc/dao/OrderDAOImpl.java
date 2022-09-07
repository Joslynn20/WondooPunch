package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Coupon;
import mvc.dto.DetailOption;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class OrderDAOImpl implements OrderDAO {

	ProductDAO productDAO = new ProductDAOImpl();
	OptionDAO optionDao = new OptionDAOImpl();
	CouponDAO couponDAO = new CouponDAOImpl();

	/**
	 * 주문하기 기능 1) Orders 테이블에 insert 2) Order_detail 테이블에 insert 3) 주문옵션 테이블에 insert
	 */
	@Override
	public int insertOrder(Orders order) throws SQLException, AddException, NotFoundException {
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

			if (result != 1) {
				con.rollback();
				throw new AddException("주문을 등록할 수 없습니다.");
			} else {
				int[] orderLineResult = insertOrderLine(con, order);
				for (int i : orderLineResult) {
					if (i != 1) {
						con.rollback();
						throw new AddException("주문 상세를 등록할 수 없습니다.");
					} else {
						int[] optionResult = insertOrderOption(con, order);
						for (int i1 : optionResult) { // 1이 아니면 주문 옵션 등록 실패 == 전체 rollback
							if (i1 != 1) {
								con.rollback();
								throw new AddException("주문 옵션 등록 실패입니다.");
							}
						}
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

	/**
	 * 전체 주문 가격 구하기
	 * 
	 * @param productCode
	 * @return 전체 주문 가격
	 * @throws SQLException
	 */
	private int getTotalPrice(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLinelist(); // 주문 상세 리스트

		int totalPrice = 0; // 전체 총 구매가격
		int orderPrice = 0; // 주문 상세 구매 가격
		int optionPrice = 0; // 옵션 가격 * 수량

		Coupon coupon = couponDAO.selectCouponByCouponCode(order.getCouponCode());
		int dc = coupon.getCouponDC(); // 쿠폰 할인율

		for (OrderLine orderLine : orderLineList) { // 주문상세 리스트
			String productCode = orderLine.getProductCode();
			Product product = productDAO.selectProductByProductCode(productCode);
			int productPrice = orderLine.getOrderQty() * product.getProductPrice();

			for (DetailOption detailOption : orderLine.getList()) { // 주문 상세 옵션 리스트
				Option option = optionDao.selectOptionByProductCode(productCode);
				optionPrice = option.getOptionPrice() * detailOption.getDetailOtionQty();
				detailOption.setOptionCode(option.getOptionCode()); // 주문상세 옵션에 옵션코드 저장
				detailOption.setDetailOptionPrice(optionPrice); // 주문상세옵션에 옵션 가격 저장
			}

			orderPrice = productPrice + optionPrice;
			int dcPrice = orderPrice * dc / 100;
			orderPrice -= dcPrice;

			orderLine.setOrderPrice(orderPrice); // 주문상세에 구매 가격 저장
			totalPrice += orderPrice;

		}
		return totalPrice;

	}

	/**
	 * 상품 총 구매 수량 구하기
	 * 
	 * @param order 객체
	 * @return 주문 총 수량
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public int getTotalQty(Orders order) throws SQLException, NotFoundException {
		List<OrderLine> orderLineList = order.getOrderLinelist();
		int totalQty = 0;
		for (OrderLine orderLine : orderLineList) {
			totalQty += orderLine.getOrderQty();
		}
		return totalQty;
	}

	/**
	 * 주문 상세 옵션 추가
	 * 
	 * @param Connection
	 * @param Order      객체
	 * @return 주문상세 등록 실행 결과
	 * @throws SQLException
	 * @throws AddException
	 */
	private int[] insertOrderLine(Connection con, Orders order) throws SQLException, AddException {
		PreparedStatement ps = null;
		String sql = "insert into order_detail values(OD_NO_SEQ.NEXTVAL, ?, ?,  ORDER_NO_SEQ.CURRVAL, ?)";
		// 리스트로 변경
		int[] result = null;

		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderLine : order.getOrderLinelist()) {
				ps.setInt(1, orderLine.getOrderQty()); // 구매 수량
				ps.setInt(2, orderLine.getOrderPrice()); // 구매 금액
				ps.setString(3, orderLine.getProductCode()); // 상품코드

				ps.addBatch();
				ps.clearParameters();
			}
			result = ps.executeBatch();

		} finally {
			DbUtil.dbClose(null, ps, null);
		}

		return result;
	}

	/**
	 * 주문 상세 옵션 등록
	 * 
	 * @param Connection
	 * @param order      객체
	 * @return int[] 실행 결과
	 * @throws SQLException
	 */
	private int[] insertOrderOption(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		String detailOptionSql = "insert into order_option values (OO_NO_SEQ.NEXTVAL, OD_NO_SEQ.CURRVAL, ?, ?, ?)";
		int result[] = null;

		try {
			ps = con.prepareStatement(detailOptionSql);
			for (OrderLine orderLine : order.getOrderLinelist()) {
				for (DetailOption detailOption : orderLine.getList()) {

					ps.setString(1, detailOption.getOptionCode()); // 옵션 코드
					ps.setInt(2, detailOption.getDetailOtionQty()); // 옵션 수량
					ps.setInt(3, detailOption.getDetailOptionNo()); // 옵션 금액

					ps.addBatch(); // 일괄처리할 작업에 추가
					ps.clearParameters();
				}
			}
			result = ps.executeBatch(); // 일괄처리

		} finally {
			DbUtil.dbClose(null, ps, null);
		}
		return result;
	}

	/**
	 * 주문 내역 보기 - 고객
	 */
	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();
		String sql = "select * from orders where m_id = ? desc order_no";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Orders orders = new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6));

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
	 */
	public List<OrderLine> selectOrderLine(Connection con, int orderNo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderLine> list = new ArrayList<OrderLine>();
		try {
			ps = con.prepareStatement("select * from order_detail where order_no=?");
			ps.setInt(1, orderNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5));
				orderLine.setList(selectOrderOption(con, orderLine)); // 주문상세에 주문옵션 정보 넣기
				list.add(orderLine);
			}
			
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		return list;
	}

	/**
	 * 주문 옵션 가져오기
	 * 
	 * @param con
	 * @param orderLine
	 * @return
	 * @throws SQLException
	 */
	private List<DetailOption> selectOrderOption(Connection con, OrderLine orderLine) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DetailOption> detailOption = new ArrayList<DetailOption>();
		String sql = "select * from order_option where OD_NO = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderLine.getOrderLineNo());
			rs = ps.executeQuery();

			while (rs.next()) {
				DetailOption option = new DetailOption(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5));
				detailOption.add(option);
			}

		} finally {
			DbUtil.dbClose(null, ps, rs);
		}

		return detailOption;
	}

	/**
	 * 퀵오더 - 주문 내역 3건 조회
	 */
	@Override
	public List<Orders> selectQuickOrder(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();
		String Sql = "select * from (select * from orders order by order_no desc) where rownum<=3";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(Sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Orders order = new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6));

				List<OrderLine> orderLineList = selectOrderLine(con, order.getOrderNo());
				order.setOrderLinelist(orderLineList);
				list.add(order);
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}

	/**
	 * 관리자 주문 내역 보기
	 */
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

			while (rs.next()) {
				Orders orders = new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6));

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
