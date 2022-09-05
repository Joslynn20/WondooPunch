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
	     String sql  = "select * from PRODUCT";
	     ResultSet rs =null;
	    
	    try {	    
	    	 con=DbUtil.getConnection();
	    	 ps =con.prepareStatement(sql);
	    	 rs =ps.executeQuery(sql);
	    	
	    	 while(rs.next()) {
	 		  Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
	 		  rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"),rs.getString("CT_CODE")); 	   
	 		  list.add(product); 
	    	 }
	    	 
	    	  	
	    }finally {
	    	
	    	DbUtil.dbClose(con, ps, rs);	
	    }
		
		return list;
	 }

	
	
	 private  String getCategoryCode( Connection con,String categoryName ) throws SQLException{
			
		
		    PreparedStatement ps =null;
		    String sql  = "select  CT_CODE from CATEGORY where UPPER(CT_NAME) =?";
		    ResultSet rs =null;
		    String categoryCode=null;
		      System.out.println(categoryName.toUpperCase()); 
		    try {
		      
		      ps= con.prepareCall(sql);      
		      ps.setString(1, categoryName.toUpperCase()); 
		      rs =ps.executeQuery(); 
		    	
		      if(rs.next())        
		    	categoryCode =rs.getString("CT_CODE");
		      
		    
		    }finally {
		    	
		    	
		    	DbUtil.dbClose(null, ps, rs);
		    	
		    }
		 
		return categoryCode;  
		 
		
	 }
	
	 
	    ////버전1 정석 버전
		 public List<Product> productSelectBycategoryName(String categoryName)throws SQLException {
				// TODO Auto-generated method stub
				
				Connection con =null;
			    PreparedStatement ps =null;
			    String sql  = "select * from PRODUCT where LOWER(CT_CODE) =LOWER(?)";
			    ResultSet rs =null;
			    List<Product> list = new ArrayList<Product>(); 
			    
			    try {	    
			    	
			    	  con=DbUtil.getConnection();
			    	  String caregoryCode =this.getCategoryCode(con,categoryName);
			    	  
			    	  if(caregoryCode!=null) {
			    		    
			    		  
						  ps=con.prepareStatement(sql);
			    	      ps.setString(1,caregoryCode);   	 
			    		  rs = ps.executeQuery();
			    		   
			    		 while(rs.next()) {
				    		 
			    		 	 Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
			    		 	 rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"),rs.getString("CT_CODE")); 	   
			    		 	 list.add(product);
			    		 		         	 	 
			    		     } 	 
			    	 
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
		  String sql = "select * from PRODUCT where P_DETAIL like ?";
		 try { 
			   con=DbUtil.getConnection();
			   ps= con.prepareStatement(sql);
			   ps.setString(1,"%"+keyword+"%");   
			   rs =ps.executeQuery();
			   
		       while(rs.next()) {   
		    
		        Product product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
			    rs.getInt("P_PRICE"),rs.getString("P_DETAIL"), rs.getString("P_REG_DATE"),rs.getString("CT_CODE"));
		    	   
		        list.add(product);
		        }
			   	
		  
		     }finally {
			
			 DbUtil.dbClose(con, ps, rs);  
		 }  
		 
		 return list;
		
	 }
 
	
	 //수정했어요  !!
	@Override
	public Product productSelectByproductName(String productName) throws SQLException {
		// TODO Auto-generated method stub
		  
		  Product product=null;
		 
		  PreparedStatement ps=null;
		  ResultSet rs =null; 
		  Connection con =null;
		  String sql="select * from PRODUCT where P_NAME= ?";	
		   
		  try {			    
			    con=DbUtil.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1,productName);
				rs=ps.executeQuery(); 
				     
				   if(rs.next()) {       
				      product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
				 	  rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"), rs.getString("CT_CODE"));
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
		  String sql="select * from PRODUCT where P_CODE=UPPER(?)";	
		  Product product=null;
		  
		  try {
				  con=DbUtil.getConnection();
				  ps=con.prepareStatement(sql);
				  ps.setString(1,productCode);
				     
				  rs=ps.executeQuery(); 
				      
				  if(rs.next()) {  
				    product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
				 	rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"), rs.getString("CT_CODE")); 
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
		  String sql = "insert into  PRODUCT VALUES(UPPER(?),?,?,?,SYSDATE,?)";
		  int result=0;
	      try { 
	    	  
	    	  
		        con=DbUtil.getConnection();
	    	    ps =con.prepareStatement(sql);
	    	    ps.setString(1, product.getProductCode());
	    	    ps.setString(2, product.getProductName());
	    	    ps.setInt(3, product.getProductPrice());
	    	    ps.setString(4,product.getProductDetail());
	    	    ps.setString(5, product.getCategoryCode());
	    	    
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
		  String sql = "delete from PRODUCT where P_CODE=UPPER(?)";
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
		  String sql = "update PRODUCT set P_PRICE= ? ,P_DETAIL= ? where P_CODE=UPPER(?)";
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



	@Override
	public Product productSelectByproductCodeOrproductName(String productCodeOrproductName) throws SQLException {
		// TODO Auto-generated method stub
		
          Product product=null;;	     

		  PreparedStatement ps =null;
		  ResultSet rs =null; 
		  Connection con =null;
		  String sql = "select * from PRODUCT where P_CODE=? or P_NAME=?";
		 try { 
			   con=DbUtil.getConnection();
			   ps= con.prepareStatement(sql);
			   ps.setString(1,productCodeOrproductName);   
			   ps.setString(2,productCodeOrproductName);   
			   rs=ps.executeQuery();
			   
			     
				if(rs.next()) {  
				  product=new Product(rs.getString("P_CODE"), rs.getString("P_NAME"), 
				  rs.getInt("P_PRICE"),rs.getString("P_DETAIL"),rs.getString("P_REG_DATE"), rs.getString("CT_CODE")); 
				 }    	   
				     
			   
			   
		        
		  
		     }finally {
			
			 DbUtil.dbClose(con, ps, rs);  
		 }  
		 
		 return product;
		

	
	
	}

  




   }//클래스 끝 
