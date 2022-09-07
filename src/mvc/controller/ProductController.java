package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;
import mvc.service.ProductService;
import mvc.service.ProductServiceImpl;
import mvc.view.FailView;

public class ProductController {
         
	   private static  ProductService productService =new ProductServiceImpl();
	
	   public  static void selectAllProduct() {
		
		  try {

			 List<Product> list  =productService.selectAllProduct();
                    
			      
			      
			 
		 }catch (SQLException e ) {
			 
			 e.printStackTrace();
		 }catch(NotFoundException es) {
			 
			 FailView.errorMessage(es.getMessage());
			 
		 
		 
		 }
		
		
	}
	
	   public  static void  selectProductByCategoryName(String categoryName){
		    
		   try {
			   List<Product> list   = productService.selectProductByCategoryName(categoryName);
		    
			  
		   
		   }catch(SQLException e ) {
			   
			    e.printStackTrace();
		   
		   }catch( NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		  
		   }   
	   
	   }
	   
	   
	   public static void  selectProductByKeyword(String keyword) {
		    try {
		      List<Product>  list   = productService.selectProductByKeyword(keyword);
	    
		      
		    }catch( SQLException e ) {
		    	
		    	e.printStackTrace();
		    }catch( NotFoundException es) {
		    	
		    	FailView.errorMessage(es.getMessage());
		    }
	   
	   
	   }
	
	   public static void  selectProductByProductName(String productName){
		   
		    try {
		      Product product  = productService.selectProductByProductName(productName);
	   
		    }catch(SQLException e ) {
		    	
		    	e.printStackTrace();
		    }catch(NotFoundException es) {
		    	 
		    	FailView.errorMessage(es.getMessage()); 
		    	
		    }
	   
	   
	   }
	
	   
	   public static void insertProduct(Product product)  {
		
        try { 
		    productService.insertProduct(product);
	     
        }catch(SQLException e ) {
        	 e.printStackTrace();
        	
        	
        }catch(AddException es ) {
        	
        	FailView.errorMessage(es.getMessage());
        }
	  
	
	} 
	   
	   
	   public static void deleteProduct(String productCode) {
		        try {
		                productService.deleteProduct(productCode);
	   
		        }catch(SQLException e) {
		        	
		        	e.printStackTrace();
		        }catch(NotFoundException es ) {
		        	
		        	FailView.errorMessage(es.getMessage());
		        }
	   
	   
	   
	   }
	    
	   
	  
	   public static void updateProduct( Product product) {
		   
		    try {
		    
		    	productService.updateProduct(product);
		    	
		    }catch(SQLException e  ) {
		    	
		           e.printStackTrace();	
		    }catch( ModifyException es ) {
		    	
		    	FailView.errorMessage(es.getMessage());
		    }
		   
	   
	   
	   
	   }
	
	   
	   
	   
	   public static void selectProductByproductCode(String productCode) {
		   
		   
		   try {
			    
			    
			   Product product  =productService.selectProductByProductCode(productCode);
			    
			   
		   }catch(SQLException e) {
			   
			   e.printStackTrace();
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
			   
			   
		   }
		   
		   
		   
		   
	   }
	   
	   
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}

}
