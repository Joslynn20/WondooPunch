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

	
	
	
	
	
	
	
	//완료
	
  public List<String> selectSalesRateBydate(String date) throws SQLException{
		 
		List<String> list = new ArrayList<String>(); 
		
		Connection con =null;
	    PreparedStatement ps =null;
	    ResultSet rs =null;
	    
		String sql ="SELECT P_CODE,P_NAME ,SUM(OD_QTY) TOTAL_QTY\r\n"
		  		+ "FROM  VIEW_SALES_RANKINGS\r\n"
		  		+ "WHERE  C_ORDER_DATE =? \r\n"
		  		+ "GROUP BY C_ORDER_DATE,P_CODE,P_NAME";
		
		    try {	    
		    	 con=DbUtil.getConnection();
		    	 ps =con.prepareStatement(sql);
		         ps.setString(1, date);	
		         rs =ps.executeQuery();
		    
		         
		    	 while(rs.next()) {
		    	
		    	    
		 	     String result	=  rs.getString("P_CODE")+"|"+rs.getString("P_NAME")+"|\t"
		 	    		+ ""+rs.getInt("TOTAL_QTY");
		 		  
		 	    
		 	     list.add(result);
		 		         
		    	 
		    	 
		    	 }
		    	 
		    	  	
		    }finally {
		    	DbUtil.dbClose(con, ps, rs);	
		    }
			
			return list;
		
		
	}
	
	
	

  private List<String> getProductCodeRankBydate(String date) throws SQLException{
		
		List<String> list = new ArrayList<String>(); 
		Connection con =null;
	    PreparedStatement ps =null;
	    ResultSet rs =null;	
		String sql ="SELECT P_CODE\r\n"
				+ "FROM (\r\n"
				+ "SELECT P_CODE, SUM(OD_QTY) TOTAL_QTY\r\n"
				+ "FROM  VIEW_SALES_RANKINGS\r\n"
				+ "WHERE  C_ORDER_DATE = ? \r\n"
				+ "GROUP BY C_ORDER_DATE,P_CODE,P_NAME\r\n"
				+ "ORDER BY  TOTAL_QTY DESC    \r\n"
				+ ")  WHERE ROWNUM<=5";
		
		
		   try {	    
		    	 con=DbUtil.getConnection();
		    	 ps =con.prepareStatement(sql);
		    	 ps.setString(1, date);
		         rs =ps.executeQuery();		         
		    	 while(rs.next()) {
		 	     String result	=  rs.getString("P_CODE");
		 	     list.add(result);
		 		         
		    	 }
		    	 
		    	  	
		    }finally {
		    	DbUtil.dbClose(con, ps, rs);	
		    }
			
			return list;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
	
	

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
