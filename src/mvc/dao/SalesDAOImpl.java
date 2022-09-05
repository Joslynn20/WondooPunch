package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;

public class SalesDAOImpl implements SalesDAO {

	public SalesDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String selectSalesBydate(String date) throws SQLException {
		// TODO Auto-generated method stub
	 
		
		 Connection con =null;
	     PreparedStatement ps =null;
	     String sql="SELECT  SUM(OTP) S_PRICE, SUM(OTQ) S_QTY FROM "
	     		+ "( SELECT ORDER_TOTAL_PRICE AS OTP ,TO_CHAR(ORDER_DATE,'YYYY-MM-DD')"
	     		+ " C_ODER_DATE ,ORDER_TOTAL_QTY AS OTQ   FROM ORDERS )  "
	     		+ "WHERE  C_ODER_DATE= ?  GROUP BY C_ODER_DATE ";
		      	
	     ResultSet rs =null;
	     String sales=null;
	    try {	    
	    	 con=DbUtil.getConnection();
	    	 ps =con.prepareStatement(sql);    
	    	 ps.setString(1,date); 
	    	 rs = ps.executeQuery(); 
	    	 if(rs.next()) {
	    		 
	    	    sales= date+"에 대힌 총 판매액:"+rs.getInt(1)+"총 판매수량:"+rs.getInt(2)+"입니다";         
	    		 
	    		 
	    		 
	    	 }
	    	  	
	    }finally {
	    	
	    	DbUtil.dbClose(con, ps, rs);	
	    }
		
	  return sales;	 
	 }
		
		
		
	
	@Override
	public String selectSalesAll() throws SQLException{ 
	  
	 Connection con =null;
      PreparedStatement ps =null;
      String sql="SELECT   SUM(ORDER_TOTAL_PRICE) S_PRICE   , SUM(ORDER_TOTAL_QTY) S_QTY"
    		    +"FROM ORDERS";         
      ResultSet rs =null;
      String sales=null;
   try {	    
   	  con=DbUtil.getConnection();
   	  ps =con.prepareStatement(sql);    
   	  rs = ps.executeQuery(); 
   	 if(rs.next()) {
   		 
   	    sales= " 누적 판매액:"+rs.getInt(1)+"총 판매수량:"+rs.getInt(2)+"입니다";         
   		 
   		 
   	 }
   	  	
   }finally {
   	
   	DbUtil.dbClose(con, ps, rs);	
   }
	
  return sales;	 
  } 
		// TODO Auto-generated method stub
		
	
	
	
	
	
	
	
	
	

	@Override
	public List<String> selectSalesRateBydate(String date) throws SQLException {
		// TODO Auto-generated method stub
		 
		 List<String> list = new ArrayList<String>();
		  Connection con =null;
	      PreparedStatement ps =null;
	      String sql ="SELECT  P_NAME,TOTAL_SALES"
	    		       +"FROM(SELECT  P_NAME, C_ORDER_DATE ,SUM(OD_QTY) TOTAL_SALES"  
	    		       +"FROM V_SALES_RATE"
	    		       + "GROUP BY C_ORDER_DATE,P_NAME"
	    		       + "ORDER BY TOTAL_SALES DESC"
	    		       + ")"
	    		       + "WHERE C_ORDER_DATE= ?";
	      ResultSet rs =null;
	      
	   try {	    
	   	  con=DbUtil.getConnection();
	   	  ps =con.prepareStatement(sql);
	   	  ps.setString(1, date);
	   	  rs = ps.executeQuery(); 
	   	  
	    	 while(rs.next()) {
	   		  
	   	        String result ="상품명:"+rs.getString("P_NAME")+" 판매량:"+rs.getInt("TOTAL_SALES");            
	   		    list.add(result);  
	   		 
	   	 }
	   	  	
	   }finally {
	   	
	   	DbUtil.dbClose(con, ps, rs);	
	   }
		
	 
	  return list;
	
	
	 } 
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<String> selectSalesRankRateBydate(String date) throws SQLException{
		
		
		
		
		List<String> list = new ArrayList<String>();
		  Connection con =null;
	      PreparedStatement ps =null;
	      String sql ="SELECT ROWNUM AS RANKINGS ,P_NAME, C_ORDER_DATE ,TOTAL_SALES"
	    		      +"FROM("
	    		      +"SELECT  P_NAME, C_ORDER_DATE ,SUM(OD_QTY) TOTAL_SALES"
	    		      +"FROM V_SALES_RATE"
	    		      +"GROUP BY C_ORDER_DATE,P_NAME"
	    		      +"ORDER BY TOTAL_SALES DESC"
	    		      +")"
	    		      +"WHERE ROWNUM <=5 AND C_ORDER_DATE= ?";
	    		      			    		      		
	      ResultSet rs =null;
	      
	   try {	    
	   	  con=DbUtil.getConnection();
	   	  ps =con.prepareStatement(sql);
	   	  ps.setString(1, date);
	   	  rs = ps.executeQuery(); 
	   	  
	    	 while(rs.next()) {
	   		  
	    		 String result = "순위:"+rs.getInt("RANKINGS")+"상품명:"+rs.getString("P_NAME")+" 판매량:"+rs.getInt("TOTAL_SALES");            
	   		    list.add(result);  
	   		 
	   	 }
	   	  	
	   }finally {
	   	
	   	DbUtil.dbClose(con, ps, rs);	
	   }
		
	 
	  return list;
	
	
	 } 
	
		// TODO Auto-generated method stub
		
	
	

	@Override
	public List<String> selectSalesRateByallDate() throws SQLException{
		
		List<String> list = new ArrayList<String>();
	    Connection con =null;
        PreparedStatement ps =null;
      String sql ="SELECT P_NAME, TOTAL_SALES"
    		      +"FROM("
    		      +"SELECT  P_NAME,SUM(OD_QTY) TOTAL_SALES"
    		      +"FROM V_SALES_RATE"
    		      +"GROUP BY P_NAME"
    		      +"ORDER BY TOTAL_SALES DESC"
    		      +")";
    		      			    		      		
      ResultSet rs =null;
      
    try {	    
   	   con=DbUtil.getConnection();
   	   ps =con.prepareStatement(sql);
   	   rs = ps.executeQuery(); 
   	  
    	 while(rs.next()) {
   		  
   	        String result ="상품명:"+rs.getString("P_NAME")+" 판매량:"+rs.getInt("TOTAL_SALES");            
   		    list.add(result);  
   		 
   	 }
   	  	
   }finally {
   	
   	DbUtil.dbClose(con, ps, rs);	
   }
	
 
  return list;


 } 
		
	
		// TODO Auto-generated method stub
		
		
		
		
		
		

	@Override
	public List<String> selectSalesRankRateByallDate() throws SQLException{
	     
		  List<String> list = new ArrayList<String>();
          Connection con =null;
          PreparedStatement ps =null;
         String sql ="SELECT ROWNUM AS RANKINGS ,P_NAME, TOTAL_SALES"
		      +"FROM("
		      +"SELECT  P_NAME,SUM(OD_QTY) TOTAL_SALES"
		      +"FROM V_SALES_RATE"
		      +"GROUP BY P_NAME"
		      +"ORDER BY TOTAL_SALES DESC"
		      +")"
		      +"WHERE ROWNUM <=5 ";
		      			    		      		
             ResultSet rs =null;
  
try {	    
	   con=DbUtil.getConnection();
	   ps =con.prepareStatement(sql);
	   rs = ps.executeQuery(); 
	  
	 while(rs.next()) {
		  
	        String result = "순위:"+rs.getInt("RANKINGS")+"상품명:"+rs.getString("P_NAME")+" 판매량:"+rs.getInt("TOTAL_SALES");            
		    list.add(result);  
		 
	 }
	  	
      }finally {
	
	DbUtil.dbClose(con, ps, rs);	
           }


        return list;


} 
		// TODO Auto-generated method stub
		
	

}
