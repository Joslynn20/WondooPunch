package mvc.dto;

public class Product {
          
	       private String  categoryId;
           private String  productId;
           private String  productName;   
	       private int productPrice;
	       private String productDetail;
	       private String productRegDate;
		    
	       /* 상품 등록 할때 쓸 생성자
	        * 
	        * */   
	       public Product(String categoryId, String productId, String productName, int productPrice,String productDetail) {
			
	    	this.categoryId = categoryId;
			this.productId = productId;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productDetail = productDetail;
		
		   }
	       
	       /* 
 	        * 상품 정보 조회 할때 쓸 생성자 
 	        * */  

		    public Product(String categoryId, String productId, String productName, int productQty, int productPrice,
				String productDetail, String productRegDate) {
			   this(categoryId, productId, productName,  productPrice, productDetail);
			   this.productRegDate = productRegDate;
		  
		    }      
 
          /*  상품 정보 수정 가격, 설명
           * 
           * */    
 	     
		    public  Product( int productPrice  , String productDetail ) {
		     this.productPrice= productPrice;
		     this.productDetail =productDetail; 
		    	
		    	
		     }

		
		  
		    public String getCategoryId() {
			
			   return categoryId;
		   
		   }

		 
		   
		    public void setCategoryId(String categoryId) {
			
			  this.categoryId = categoryId;
		 
		   }
		  

		  
		    public String getProductId() {
		 	 return productId;
		
		  }

		   
		    public void setProductId(String productId) {
			
		    	this.productId = productId;
		    }

	 
		    public String getProductName() {
			 
		    	return productName;
		   
		    }

		
		    public void setProductName(String productName) {
			
		    	this.productName = productName;
		    }

	
		    
		    public int getProductPrice() {
			
		    	return productPrice;
		   }

		
		    
		    public void setProductPrice(int productPrice) {
			
		    	this.productPrice = productPrice;
		   }

		
		    
		    
		    public String getProductDetail() {
			
		    	return productDetail;
		    
		    }

	
		    public void setProductDetail(String productDetail) {
			 this.productDetail = productDetail;
		    }

		
		    
		    public String getProductRegDate() {
			return productRegDate;
		}

		
		    
		    public void setProductRegDate(String productRegDate) {
			this.productRegDate = productRegDate;
		}
	       
	       
	        
		    @Override
		    public String toString() {
		    
		    	// TODO Auto-generated method stub
		    return categoryId+"|"+productId+"|"+productName+"|"+productPrice+"원|"+productDetail+"|"+productRegDate;
		    
		    
		    } 
	       
	       
	       



}
