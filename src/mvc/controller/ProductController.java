package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;
import mvc.service.ProductService;
import mvc.service.ProductServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class ProductController {
         
	   private static  ProductService productService =new ProductServiceImpl();
	 
	   /**
		 * 모든 상품 검색 
		 *  
		 * @param 
		 *  
		 */
	     
	   public  static void selectAllProduct() {		
		  
		 try {
			 List<Product> list  =productService.selectAllProduct();   
			 EndView.printProductList(list);

		 }catch (Exception e ) {		 
			 FailView.errorMessage(e.getMessage());
		 }
	   
	   }
	
	    
	   /**
		 * 카케고리 에 따른 상품을 조회
		 * 
		 * @param categoryName 
		 */
	    
	   public  static void  selectProductByCategoryName(String categoryName){
		    
		   try {
			   List<Product> list   = productService.selectProductByCategoryName(categoryName);
			   EndView.printProductList(list);
		      
		   }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	    
	   }
	  
	   
	   /**
		 *  키워드로 상품을 조회
		 * 
		 * @param  keyword
		 */
	   
	   public static void  selectProductByKeyword(String keyword) {
		    try {
		      List<Product>  list   = productService.selectProductByKeyword(keyword);
		      EndView.printProductList(list);
		      
		    }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	   
	   }
	
	   
	   
	   /**
		 *  상픔의 이름으로 상품을 조회
		 * 
		 * @param  productName
		 */
	   
	   public static void  selectProductByProductName(String productName){
		   
		    try {
		      Product product  = productService.selectProductByProductName(productName);
		      EndView.printProduct(product);
	   
		     }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	   
	    }
	 
	   
	   /**
		 *  상픔을 등록
		 * 
		 * @param  product
		 */
	   
	   public static void insertProduct(Product product)  {
		
          try { 
		    productService.insertProduct(product); 
		    EndView.printMessage(product.getProductCode()+"등록이 완료되었습니다.");
          }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	  
          
	    } 
	      
	   
	   /**
		 *  상픔을 삭제
		 * 
		 * @param  productName
		 */
	   
	   public static void deleteProduct(String productCode) {
		        
		    try {      
		        productService.deleteProduct(productCode);
		        EndView.printMessage(productCode+"에 대한 삭제가 완료되었습니다.");
		     }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	   
   
	   }
	    
	   
	   
	    /**
		 *  상픔을 삭제
		 * 
		 * @param  productName
		 */
	   public static void updateProduct( Product product) {
		   
		    try {
		    
		    	productService.updateProduct(product);
		    	 EndView.printMessage(product.getProductCode()+"에 대한 수정이 완료되었습니다.");
		    	
		    }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
	   
	   
	   
	   }
	
	   /**
		 *  상픔을 코드로 검색
		 * 
		 * @param  productName
		 */
	   
	   
	   public static void selectProductByproductCode(String productCode) {
		   
		  try {    
			 Product product  =productService.selectProductByProductCode(productCode);   
			 EndView.printProduct(product);
		   }catch (Exception e ) {		 
				 FailView.errorMessage(e.getMessage());
		  }
		      
	   }
	   
	   
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}

}
