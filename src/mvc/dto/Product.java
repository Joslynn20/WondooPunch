package mvc.dto;

public class Product {
          
	       private String  category;
           private String  productCode;
           private String  productName;   
	       private int productPrice;
	       private String productDetail;
	       private String productRegDate;
		    
	       /* 상품 등록 할때 쓸 생성자
	        * 
	        * */   
	       public Product(String category, String productCode, String productName, int productPrice,String productDetail) {
			
	    	this.category = category;
			this.productCode = productCode;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productDetail = productDetail;
		
		   }
	       
	       /* 
 	        * 상품 정보 조회 할때 쓸 생성자 
 	        *
 	        * */  

		    public Product(String category, String productCode, String productName, int productQty, int productPrice,
				String productDetail, String productRegDate) {
			   this(category, productCode, productName,  productPrice, productDetail);
			   this.productRegDate = productRegDate;
		  
		    }      
 
          /*  상품 정보 ,수정 가격할때 쓸 생성자
           * 
           * */    
 	     
		    public  Product( int productPrice  , String productDetail ) {
		     this.productPrice= productPrice;
		     this.productDetail =productDetail; 
		    	
		    	
		     }

		
		  
		    public String getCategory() {
			
			   return category;
		   
		   }

		 
		   
		    public void setCategory(String category) {
			
			  this.category = category;
		 
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
				builder.append("Product [category=");
				builder.append(category);
				builder.append(", productCode=");
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
