package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Product;

public class SalesDAOImpl implements SalesDAO {

	public SalesDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	/* 
	 *  해당하는 날짜에 대한 , 판매 총액 ,주문 완료건수 ,  주문 상품 수량
	 * */ 
	public String selectSalesBydate(String date) throws SQLException {
		// TODO Auto-generated method stub
	 
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
	    String sales=null;
		String sql="SELECT  SUM(ORDER_TOTAL_PRICE),COUNT(ORDER_NO), SUM(ORDER_TOTAL_QTY) \r\n"
				+ "  FROM ORDERS \r\n"
				+ "  WHERE TO_CHAR(ORDER_DATE,'YYYY-MM-DD')=?";
		try {
		       con=DbUtil.getConnection();
			   ps=con.prepareStatement(sql);
			   ps.setString(1, date);
			   rs=ps.executeQuery();
			   if(rs.next()) {
		         sales= date+"|| 1.일 매출 총액="+rs.getInt(1)+",  2.주문 완료 건수="+rs.getInt(2)+", 3.전체 주문 수량="+rs.getInt(3);		   
				   
			   }
			   
			   
		}finally {
			
			DbUtil.dbClose(con, ps, rs);
			
		}
		
	
		return sales;
	}

	/* 
	 *   누적 판매 총액 , 누적 주문 완료건수 ,  누적 주문 상품 수량
	 * */ 
	

	public String selectAllSales() throws SQLException {
		// TODO Auto-generated method stub
		
		
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
	    String sales=null;
		String sql="SELECT SUM(ORDER_TOTAL_PRICE),COUNT(ORDER_NO),SUM(ORDER_TOTAL_QTY)  \r\n"
				+ "  FROM ORDERS";
		try {
		       con=DbUtil.getConnection();
			   ps=con.prepareStatement(sql);
			  
			   rs=ps.executeQuery();
			   if(rs.next()) {
		         sales= " 1.누적 매출 총액 ="+rs.getInt(1)+" , 2.누적 주문 완료 건수 ="+rs.getInt(2)+",  3.누적 주문 수량= "+rs.getInt(3);		   
				   
			   }
			   
			   
		}finally {
			
			DbUtil.dbClose(con, ps, rs);
			
		}
		
	
		return sales;
	}
		
		
		
	/* 
	 *   해당하는 날에 대한 상품별 판매 수량 
	 * */ 


	public List<String> selectSalesRateBydate(String date) throws SQLException {
		// TODO Auto-generated method stub
	
		List<String> list = new ArrayList<String>(); 
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;	 
		String sql =" SELECT ROW_NUMBER() OVER( ORDER BY SUM(OD_QTY) DESC ) AS RANKS ,P_CODE ,SUM(OD_QTY)\r\n"
				+ "                    FROM ORDERS JOIN ORDER_DETAIL\r\n"
				+ "				    USING(ORDER_NO)\r\n"
				+ "				    WHERE TO_CHAR (ORDER_DATE ,'YYYY-MM-DD')= ?\r\n"
				+ "				    GROUP BY P_CODE";
		
		String result=null;
		try {
		       con=DbUtil.getConnection();
			   ps=con.prepareStatement(sql);
			   ps.setString(1, date);
			   rs=ps.executeQuery();
			   while(rs.next()) {
		         result= rs.getInt(1)+": 상품코드="+rs.getString(2)+", 주문 수량 합계="+rs.getInt(3);		   
				 list.add(result);  
		         
			   }
			   
			   
		}finally {
			
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		
		return list;
	}

	
	/* 
	 *   해당하는 날에 대한 상품별 판매 순위 상품 5위
	 * */ 

	public List<Product> selectSalesRankBydate(String date) throws SQLException {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>(); 
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;	 		
	   String sql="SELECT * \r\n"
	   		+ "  FROM PRODUCT\r\n"
	   		+ "  WHERE P_CODE =ANY(\r\n"
	   		+ "  SELECT P_CODE\r\n"
	   		+ "  FROM(\r\n"
	   		+ "  SELECT ROW_NUMBER() OVER (ORDER BY SUM(OD_QTY) DESC) AS RANKINGS , P_CODE\r\n"
	   		+ "  FROM ORDER_DETAIL\r\n"
	   		+ "  WHERE ORDER_NO  =ANY(SELECT ORDER_NO FROM ORDERS WHERE TO_CHAR(ORDER_DATE,'YYYY-MM-DD')=?)\r\n"
	   		+ "  GROUP BY P_CODE\r\n"
	   		+ "  ) WHERE RANKINGS<=5\r\n"
	   		+ " )";
		   
		 try {
			     con =DbUtil.getConnection();
			     ps = con.prepareStatement(sql);
			     ps.setString(1, date); 
			     rs =ps.executeQuery();   
			 while(rs.next()) {  
			 Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
			 rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"), rs.getString("CT_CODE")); 
				   
			  list.add(product);
			 
			 }    
			 
		 }finally {
			 
			 DbUtil.dbClose(con, ps, rs);
			 
		 }
		
		
		return list;
	
	
	}

	
	/* 
	 *   상품 누적 판매 량
	 * */ 
	
	
	public List<String> selectAllSalesRate() throws SQLException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>(); 
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;	 
		String sql =" SELECT ROW_NUMBER() OVER( ORDER BY SUM(OD_QTY) DESC) AS RANKINGS , P_CODE ,SUM(OD_QTY)\r\n"
				+ "   FROM ORDER_DETAIL\r\n"
				+ "   GROUP BY P_CODE  ";
		
		String result=null;
		try {
		       con=DbUtil.getConnection();
			   ps=con.prepareStatement(sql);
			   rs=ps.executeQuery(); 
			   while(rs.next()) {
		         result= rs.getInt(1)+" 상품코드="+rs.getString(2)+",  주문 수량 합계="+rs.getInt(3);		   
				 list.add(result);  
		         
			   }
			   
			   
		}finally {
			
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		
		return list;
	
	
	
	
	
	}

	
	/* 
	 *   상품 누적 판매 순위 5위
	 * */ 
	
	
	public List<Product> selectAllSalesRank() throws SQLException {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>(); 
		Connection  con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;	 		
	   String sql=" SELECT *\r\n"
	 		+ "   FROM PRODUCT\r\n"
	 		+ "   WHERE P_CODE= ANY(\r\n"
	 		+ "   SELECT P_CODE \r\n"
	 		+ "   FROM(\r\n"
	 		+ "   SELECT ROW_NUMBER() OVER( ORDER BY SUM(OD_QTY) DESC) AS RANKINGS , P_CODE ,SUM(OD_QTY)\r\n"
	 		+ "   FROM ORDER_DETAIL\r\n"
	 		+ "   GROUP BY P_CODE  \r\n"
	 		+ "   )\r\n"
	 		+ "   WHERE RANKINGS<=5\r\n"
	 		+ "   )";
		   
		 try {
			      con =DbUtil.getConnection();
			      ps = con.prepareStatement(sql);
			       rs  =ps.executeQuery();
			       
			 while(rs.next()) {  
			 Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
			 rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"), rs.getString("CT_CODE")); 
				   
			  list.add(product);
			 
			 }    
			 
		 }finally {
			 
			 DbUtil.dbClose(con, ps, rs);
			 
		 }
		
		
		return list;
	
	
	}
	
	
	
	
	
	
		
		
		
	
	

	

} 
	
	
	
	
	
	
	
	
	
	
	
		
	


