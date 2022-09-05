package mvc.dao;

import java.sql.SQLException;
import java.util.List;

public interface SalesDAO {

	
	
	

    /*                      
     * 
     *                                  SELECT  SUM(OTP) S_PRICE, SUM(OTQ) S_QTY
                                        FROM (   
                                        SELECT ORDER_TOTAL_PRICE AS OTP ,TO_CHAR(ORDER_DATE,'YYYY-MM-DD') C_ODER_DATE ,ORDER_TOTAL_QTY AS OTQ  
                                        FROM ORDERS
                                        ) 
                                        WHERE  C_ODER_DATE= ?
                                        GROUP BY C_ODER_DATE 
     *  
     *  
     * 
     *  
     * 
     * 
     * 
     *   
     *   
     * */ 
       
	   public String selectSalesBydate(String date) throws SQLException ;
	 	  
	  	  
       /*
        *
        *    
        *    
        *     SELECT   SUM(ORDER_TOTAL_PRICE) S_PRICE   , SUM(ORDER_TOTAL_QTY) S_QTY
              FROM ORDERS 
        *    
        *     
        *    
        *    
        *    
        *    
        *    
        *    
        * */
	   
	   
	   
	   public String selectSalesAll() throws SQLException ;
    	   
	   
	   /* 
	    * 
	    * 랭크가 아니라 모든 것 DATE
	      SELECT  P_NAME, C_ORDER_DATE ,TOTAL_SALES   
	      FROM(
	      SELECT  P_NAME, C_ORDER_DATE ,SUM(OD_QTY) TOTAL_SALES   
	      FROM V_SALES_RATE
	      GROUP BY C_ORDER_DATE,P_NAME 
	      ORDER BY TOTAL_SALES DESC
	      ) 
	       WHERE C_ORDER_DATE= ?    
	   
	   *
	   */
    
	     
	   	   
       public List<String> selectSalesRateBydate(String date) throws SQLException ;
	  
        
        
      /*   
        5위 
         
       SELECT ROWNUM AS RANKINGS ,P_NAME, C_ORDER_DATE ,TOTAL_SALES   
       FROM(
       SELECT  P_NAME, C_ORDER_DATE ,SUM(OD_QTY) TOTAL_SALES   
       FROM V_SALES_RATE
       GROUP BY C_ORDER_DATE,P_NAME 
       ORDER BY TOTAL_SALES DESC
       ) 
       WHERE ROWNUM <=5 AND C_ORDER_DATE= ?        
       
       
        */
  
             
       public List<String> selectSalesRankRateBydate(String date) throws SQLException ;
 
       
          
        
      /*
       *           
       *            무재한 누적
       *            SELECT P_NAME, TOTAL_SALES   
                    FROM(
                    SELECT  P_NAME,SUM(OD_QTY) TOTAL_SALES   
                    FROM V_SALES_RATE
                    GROUP BY P_NAME 
                    ORDER BY TOTAL_SALES DESC
                    )
       * 
        * 
       * 
       * 
       * 
       */ 
       
       public List<String> selectSalesRateByallDate() throws SQLException ;
       
       
       
       /*
                                        SELECT ROWNUM AS RANKINGS ,P_NAME, TOTAL_SALES   
                                        FROM(
                                        SELECT  P_NAME,SUM(OD_QTY) TOTAL_SALES   
                                        FROM V_SALES_RATE
                                        GROUP BY P_NAME 
                                        ORDER BY TOTAL_SALES DESC
                                         ) 
                                       WHERE ROWNUM <=5 
           
         */
          
         
        
       
        
       public List<String> selectSalesRankRateByallDate() throws SQLException ;
 
 



}
