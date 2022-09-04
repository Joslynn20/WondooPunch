package mvc.dto;

public class Product {
          
	      
           private String  productCode; //pk 상품코드
           private String  productName; //상품 이름  
	       private int productPrice;   //상품가격
	       private String productDetail;//상품 설명 
	       private String productRegDate;//상품등록일
	       private String  categoryCode;//카케고리 코드
	       
	       /* 상품 등록 할때 쓸 생성자
	        * 
	        * */   
	       public Product(String productCode, String productName, int productPrice,String productDetail, String  categoryCode) {
	
			this.productCode = productCode;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productDetail = productDetail;
			this.categoryCode =categoryCode;
		
		   }
	       
	       /* 
 	        * 상품 정보 조회 할때 쓸 생성자 
 	        *
 	        * */  

	        public Product(String productCode, String productName, int productPrice,String productDetail, String productRegDate,String categoryCode ) {
	    		
			 this(productCode, productName, productPrice,productDetail, categoryCode );	
	    	 this.productRegDate=productRegDate; 
	    	   
			
			}
	       
	       
	       
 
          /*  상품 정보 ,수정 가격할때 쓸 생성자
           * 
           * */    
 	     
		    public Product(String productCode  ,int productPrice  , String productDetail ) {
		     
		     this.productCode=productCode;
		     this.productPrice= productPrice;
		     this.productDetail =productDetail; 
		    	
		    	
		     }

		
		  
		    public String getCategoryCode() {
			
			   return categoryCode;
		   
		   }

		 
		   
		    public void setCategoryCode(String categoryCode) {
			
			  this.categoryCode = categoryCode;
		 
		   }
		  

		  
		    public String getProductCode() {
		 	 return productCode;
		
		  }

		   
		    public void setProductCode(String productCode) {
			
		    	this.productCode = productCode;
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
				StringBuilder builder = new StringBuilder();
				builder.append("Product [productCode=");
				builder.append(productCode);
				builder.append(", productName=");
				builder.append(productName);
				builder.append(", productPrice=");
				builder.append(productPrice);
				builder.append(", productDetail=");
				builder.append(productDetail);
				builder.append(", productRegDate=");
				builder.append(productRegDate);
				builder.append("]");
				return builder.toString();
			}

		
		  
			
			 
	       
	        
	
	       
	       
	       



}
