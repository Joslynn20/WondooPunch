package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Product;

public class ProductDAOImpl implements ProductDAO {

	public ProductDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Product> productSelectAll() throws SQLException {
		// TODO Auto-generated method stub
		 
		List<Product> list = new ArrayList<Product>(); 
		
		Connection con =null;
	    PreparedStatement ps =null;
	    String sql  = "select * from V_PRODUCT";
	    ResultSet rs =null;
	    
	    
	    try {	    
	    	 con=DbUtil.getConnection();
	    	 ps =con.prepareStatement(sql);
	    	 rs =ps.executeQuery(sql);
	    	
	    	 
	    	 while(rs.next()) {
	    		 
	 		  Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
	 		  rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("CT_NAME"),rs.getString("P_REG_DATE")); 	   
	 		  list.add(product);
	 		         
	    	 
	    	 
	    	 }
	    	 
	    	  	
	    }finally {
	    	DbUtil.dbClose(con, ps, rs);	
	    }
		
		return list;
	}

	
	
	@Override
	public List<Product> productSelectBycategoryName(String categoryName)throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con =null;
	    PreparedStatement ps =null;
	    String sql  = "select * from V_PRODUCT WHERE LOWER(CT_NAME) =LOWER(?)";
	    ResultSet rs =null;
	    List<Product> list = new ArrayList<Product>(); 
	    try {	    
	    	 con=DbUtil.getConnection();
	    	 ps =con.prepareStatement(sql);
	    	 ps.executeQuery(sql);
	    	 
	    	 
	    	 while(rs.next()) {
	    		 
		 	 Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
		 	 rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("CT_NAME"),rs.getString("P_REG_DATE")); 	   
		 	 list.add(product);
		 		         	 	 
		     } 	 
	    	  	
	    }finally {
	    	DbUtil.dbClose(con, ps, rs);	
	    
	    }
		
	    return list;
			
	}

	
	
	
	@Override
	public List<Product> productSelectBykeyword(String keyword) throws SQLException  {
		// TODO Auto-generated method stub
	
		  List<Product> list =new ArrayList<Product>(); 
        
		  PreparedStatement ps =null;
		  ResultSet rs =null; 
		  Connection con =null;
		  String sql = "SELECT * FROM V_PRODUCT WHERE P_DETAIL LIKE ?";
		 try { 
			   con=DbUtil.getConnection();
			   ps= con.prepareStatement(sql);
			   ps.setString(1,"%"+keyword+"%");   
			   rs =ps.executeQuery();
			   
		       while(rs.next()) {   
		    
		        Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
			    rs.getInt("P_PRICE"),rs.getString("P_DETAIL"), rs.getString("CT_NAME"),rs.getString("P_REG_DATE"));
		    	   
		        list.add(product);
		        }
			   	
		  
		     }finally {
			
			 DbUtil.dbClose(con, ps, rs);  
		 }  
		 
		 return list;
		
	 }

	@Override
	public Product productSelectByproductName(String productName) throws SQLException {
		// TODO Auto-generated method stub
		  
		  Product product=null;
		 
		  PreparedStatement ps=null;
		  ResultSet rs =null; 
		  Connection con =null;
		  String sql="SELECT * FROM PRODUCT WHERE P_NAME= ?";	
		   
		  try {			    
			    con=DbUtil.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1,productName);
				rs=ps.executeQuery(); 
				     
				   if(rs.next()) {       
				      product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
				 	  rs.getInt("P_PRICE"),rs.getString("P_DETAIL"), rs.getString("CT_CODE"),rs.getString("P_REG_DATE"));
				    }    	   
				     
			  }finally {
				  
				   DbUtil.dbClose(con, ps, rs);
				  
			  }
			 
		
	   return product;	
		
	}

	@Override
	public Product productSelectByproductCode(String productCode) throws SQLException {
		// TODO Auto-generated method stub
	
		  PreparedStatement ps=null;
		  ResultSet rs =null; 
		  Connection con =null;
		  String sql="SELECT * FROM PRODUCT WHERE P_CODE=UPPER(?)";	
		  Product product=null;
		  
		  try {
				  con=DbUtil.getConnection();
				  ps=con.prepareStatement(sql);
				  ps.setString(1,productCode);
				     
				  rs=ps.executeQuery(); 
				      
				  if(rs.next()) {  
				    product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
				 	rs.getInt("P_PRICE"),rs.getString("P_DETAIL"), rs.getString("CT_CODE"),rs.getString("P_REG_DATE")); 
				   }    	   
				     
				    
				     
			  }finally {
				  
		    	   DbUtil.dbClose(con, ps, rs);
				  
			  }
			 
	         return product;		
 	 
	  }//함수 끝
 
	
	
	@Override
	public int productInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		

		  Connection con =null;
		  PreparedStatement ps =null;
		  String sql = "INSERT INTO  PRODUCT VALUES(?,?,?,?,SYSDATE,?)";
		  int result=0;
	      try { 
		        con=DbUtil.getConnection();
	    	    ps =con.prepareStatement(sql);
	    	    ps.setString(1, product.getProductCode());
	    	    ps.setString(2, product.getProductName());
	    	    ps.setInt(3, product.getProductPrice());
	    	    ps.setString(4,product.getProductDetail());
	    	    String categoryCode=null;
	    	    
	    	    String categoryName =product.getCategoryCode();
	    	    
	    	    if(categoryName.equals("dessert")) {
	    	    	categoryCode="d";
	    	    }else if(categoryName.equals("beverage")) {
	    	    	categoryCode="b";
                  
	    	    }
	    	     ps.setString(5, categoryCode);
	    	    
	    	     result= ps.executeUpdate();
	    	    
		
	      }finally {
	    	  
	    	  DbUtil.dbClose(con, ps);
	      }
		
	  return result;
				
	}//함수 끝 

	
	
	@Override
	public int productDelete(String productCode) throws SQLException {
		// TODO Auto-generated method stub
		
		  Connection con =null;
		  PreparedStatement ps =null;
		  String sql = "DELETE FROM PRODUCT WHERE P_CODE=UPPER(?)";
		  int result=0;
	      try { 
		        con=DbUtil.getConnection();
	    	    ps =con.prepareStatement(sql);
	    	    ps.setString(1, productCode); 
	    	    result= ps.executeUpdate();
	    	    
		
	      }finally {
	    	  
	    	  DbUtil.dbClose(con, ps);
	      }
		
	     return result;
		
		
	}//함수 끝 

	
	
	@Override
	public int productUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		
		  Connection con =null;
		  PreparedStatement ps =null;
		  String sql = "UPDATE PRODUCT SET P_PRICE= ? ,P_DETAIL= ? WHERE P_CODE=UPPER(?)";
		  int result=0;
	      try { 
		        con=DbUtil.getConnection();
	    	    ps =con.prepareStatement(sql);
	    	    ps.setInt(1, product.getProductPrice());
	    	    ps.setString(2, product.getProductDetail());
	    	    ps.setString(3, product.getProductCode());
	    	    result= ps.executeUpdate();
	    	    
		        
	      }finally {
	    	  
	    	  DbUtil.dbClose(con, ps);
	      }
		
	      return result;
			
	
	 }//함수 끝








   }//클래스 끝 
