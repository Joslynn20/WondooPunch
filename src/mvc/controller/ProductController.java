package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;
import mvc.service.ProductService;
import mvc.service.ProductServiceImpl;

public class ProductController {
         
	   private  static ProductService productService = ProductServiceImpl.getInstance();
	
	   public  static void  productSelectAll() {
		
		  try {

			      List<Product> list  =productService.productSelectAll();
                    
			       for(  Product p  :list ) {
			    	   
			    	   System.out.println(p);
			       }
			       
			 
		 }catch (SQLException e ) {
			 
			 e.printStackTrace();
		 }catch(NotFoundException es) {
			 
			 System.out.println(es.getMessage());
			 
		 
		 
		 }
		
		
	}
	
	   public  static void  productSelectBycategoryName(String categoryName){
		    
		   try {
			   List<Product> list   = productService.productSelectBycategoryName(categoryName);
		    
			   for ( Product p : list ) {  
				   System.out.println(p);   
			   }
		   
		   }catch(SQLException e ) {
			   
			    e.printStackTrace();
		   
		   }catch( NotFoundException es) {
			   
			   System.out.println(es.getMessage());
		  
		   }   
	   
	   }
	   
	   
	   public static void  productSelectBykeyword(String keyword) {
		    try {
		      List<Product>  list   = productService.productSelectBykeyword(keyword);
	    
		       for(Product p   :list) {
		    	   
		    	   System.out.println(p);
		       }
		      
		    }catch( SQLException e ) {
		    	
		    	e.printStackTrace();
		    }catch( NotFoundException es) {
		    	
		    	System.out.println(es.getMessage());
		    }
	   
	   
	   }
	
	   public static void  productSelectByproductName(String productName){
		   
		    try {
		      Product product  = productService.productSelectByproductName(productName);
	   
		    }catch(SQLException e ) {
		    	
		    	e.printStackTrace();
		    }catch(NotFoundException es) {
		    	 
		    	System.out.println(es.getMessage()); 
		    	
		    }
	   
	   
	   }
	
	   
	   public static void  productInsert(Product product)  {
		
        try { 
		    productService.productInsert(product);
	     
        }catch(SQLException e ) {
        	 e.printStackTrace();
        	
        	
        }catch(AddException e ) {
        	
        	System.out.println(e.getMessage());
        }
	  
	
	} 
	   
	   
	   public static void productDelete(String productCode) {
		        try {
		                productService.productDelete(productCode);
	   
		        }catch(SQLException e) {
		        	
		        	e.printStackTrace();
		        }catch(NotFoundException es ) {
		        	
		        	System.out.println(es.getMessage());
		        }
	   
	   
	   
	   }
	    
	   
	  
	   public static void productUpdate( Product product) {
		   
		    try {
		    
		    	productService.productUpdate(product);
		    	
		    }catch(SQLException e  ) {
		    	
		           e.printStackTrace();	
		    }catch( ModifyException es ) {
		    	
		    	 System.out.println(es.getMessage());
		    }
		   
	   
	   
	   
	   }
	
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}

}
