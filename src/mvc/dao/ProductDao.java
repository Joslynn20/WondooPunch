package mvc.dao;

import java.util.List;

import mvc.dto.Product;

public interface ProductDao {
   
	
	/*
	 * 전제 상품을 조회 하는 기능 
	 * */
	
    List<Product> poductSellectAll();
    
    /*
	 * 카테고리 별 상품을 조회 하는 기능 
	 * */
    List<Product> searchByProductCategoryId(String categoryId );
    
	 
    /*  
     * 상품의 키워드로 검색하는 기믕 
     * */
    
    
     List<Product> searchByProductkeyword(String keyword);
    	 
    	      
     
	/* 
	 * 상품을 등록하는 기능 
	 * */
	  
	public  int productInsert(Product  product);

    
	/* 
	 * 상품을 삭제하는 기능 
	 * */
	
    public int productDelete(Product  product);  





}
