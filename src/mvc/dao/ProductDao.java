package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;


public interface ProductDAO {
   
	/*     - 사용한 테이블
	  
	     1) V_PRODUCT : 상품을 SELECT(검색) 할때 쓰는 테이블  
	       
	        스키마  P_CODE | P_NAME | P_PRICE  | P_DETAIL | CT_NAME | P_REG_DATE
	         
	        CREATE OR REPLACE VIEW  V_PRODUCT  
            AS
            SELECT P_CODE,P_NAME,P_PRICE,P_DETAIL,CT_NAME,P_REG_DATE 
            FROM PRODUCT  JOIN CATEGORY 
            USING (CT_CODE) 
	         
	        주위!! productSelectByproductCode는 관리자가 상품을 삭제 혹은 등록 
	              할때 사용할 것이기 때문에  PRODUCT 테이블 을 사용합니다 
	               
	       
	      2) PRODUCT : 상품을 INSERT ,UPDATE ,DELETE  할때 쓰는 테이블
	      
	        스키마  P_CODE | P_NAME | P_PRICE | P_DETAIL | P_REG_DATE | CT_CODE                        
	                    
	      
	      - 명명 규칙
	       
	       1) 검색 
	         
	         DTO     검색       매게 변수 
	       Product Select  By  (앞글자 소문자임)  
	      
	       2)CRUD 
	      
	         DTO         (등록  , 수정  , 삭제  )
	        Product     Insert ,Update, Delete          
	  */

	
	
	/*
	 *  기능   :전제 상품을 조회 한다
	 *  TABLE :  V_PRODUCT
	 *  sql 문 : "select * from V_PRODUCT"
	 * 
	 *   @return  List<Product>  
	 *   성공할경우 list
	 *   실패할경우 list.size()==0 or list.isEmpty() true     
	 * */
	
    List<Product> productSelectAll() throws SQLException ;
    
    
    /*
	 *    기능 : 카테고리 별 상품을 조회 하는 기능 
	 *    TABLE :  V_PRODUCT
	 *    sql 문 : "select * from V_PRODUCT WHERE LOWER(CT_NAME) = LOWER(?)"
	 *    
	 *    
	 *    @return  List<Product> 
	 *    성공할경우 list
	 *    실패할경우 list.size()==0 or list.isEmpty() true  
	 * 
	 * */
    List<Product> productSelectBycategoryName (String categoryName )throws SQLException;
    
    
	 
    /*  
     *   기능: 상품의 productDetail에 있는 것을 상품의 키워드로 검색하는 기능   
     *   TABLE :  V_PRODUCT
	 *   sql 문 : "select * from  V_PRODUCT where P_DETAIL LIKE ?"
     *      
     *   
     *      
     *   @return 
     *    성공할경우: list  
     *    실패할경우 :list.size()==0 or list.isEmpty() true 
     * */
    
     List<Product> productSelectBykeyword(String keyword) throws SQLException ;
    	 
    
     /*
      *  기능:  상픔의 이름으로 겸색한다  
      *  TABLE :  V_PRODUCT
      *  sql 문 : "select * FROM V_PRODUCT where P_NAME= ?";	 
      *   
      *   @return 
      *   1) 없을 경우 null
      *  
      *   2) 존재 할 경우 Product
      *   
      * */
     
     Product productSelectByproductName(String productName) throws SQLException;
     
     
     /*
      *  기능:  상픔의 코드로 상품을 검색한다   
      *  TABLE :  PRODUCT
      *  sql 문 : "select  * FROM PRODUCT where P_CODE=UPPER(?)";	
      *  
      *  @return 
      *   1) 없을 경우 null;
      *   2) 존재 할 경우 Product
      *   
      * */
     
     Product productSelectByproductCode (String productCode) throws SQLException ;
     
     
    
	/* 
	 *  기능:  상품을 등록하는 기능  
	 *  TABLE :  PRODUCT
	 *  @Params Product
	 *  @return  int
	 *  sql= "insert into  PRODUCT VALUES(?,?,?,?,SYSDATE,?)"
	 *  등록 성공시 1
	 *  등록 실패시 0
	 * */
	  
	public  int productInsert(Product  product) throws SQLException  ;

    
	/* 
	 *  상품을 삭제하는 기능 
	 *  TABLE :  PRODUCT
	 *  @Params  String 
	 *  @return  int
	 *  sql="delete from PRODUCT where P_CODE=UPPER(?)"
	 *  삭제 성공시 1
	 *  삭제 실패시 0
	 * */
	
    public int productDelete(String  productCode) throws SQLException;  

  
    
    /* 
	 * 상품의 가격과 설명을 수정 하는 기능 
	 *  TABLE :  PRODUCT
	 *  @Params Product 
	 *  @return  int
	 *  sql=update PRODUCT set P_PRICE= ? ,P_DETAIL= ? where P_CODE=UPPER(?);
	 *  수정 성공시 1
	 *  수정 실패시 0
	 * */
      
    public int productUpdate( Product product ) throws SQLException  ; 
     
    
    
    
    
    
    



}
