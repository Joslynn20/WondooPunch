package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.NotFoundException;
import mvc.service.SalesService;
import mvc.service.SalesServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class SalesController {

	  private static SalesService salesService  =new SalesServiceImpl( ); 
	 
	  
	  /*
	   * 해당하는 날짜에  판매 총액, 누적 주문 수량, 전체 주문 상품 갯수  
	   * @param date
	   * */
	  
	  public  static void selectSalesBydate(String date) {
		 
	   try {
		 String result  =salesService.selectSalesBydate(date); 
		 EndView.printMessage(result);
	   }catch(Exception e){
		   FailView.errorMessage(e.getMessage());
	   }
 
      }
	   
	  
	  /*
	   * 누적  판매 총액, 누적 주문 수량, 전체 주문 상품 갯수  
	   * @param 
	   * */	 
	  
	  public static void selectAllSales() {
		   
    	 try {
	   	  String result  =salesService.selectAllSales();
	   	EndView.printMessage(result);
		  }catch(Exception e) {	   			 
			FailView.errorMessage(e.getMessage());
		  }
	  
	  }
	  
	  
	  /*
	   * 해당 날짜에 상품별 판매 수향 
	   * @param date
	   * */
	  
	   public static void  selectSalesRateBydate(String date) {
		    
		   try{ 
			  List<String> list   =salesService.selectSalesRateBydate(date);
			  EndView.printSalesRate(list);
		   }catch(Exception e){
			   FailView.errorMessage(e.getMessage());
		   }
		   
	   
	   }
	   
	    /*
		 *   해당 날짜에 상품별 판매 순위 5위 까지
		 * @param date
		 * */
	   
	   public static void   selectSalesRankBydate(String date) {
		    
		  try{
			  List<Product> list   =salesService.selectSalesRankBydate(date);
			  EndView.printSalesRank(list);
		   }catch(Exception e){
			   FailView.errorMessage(e.getMessage());
		   }
	  }
		  
		  
	    /*
		 *   누적 상품별 판매 
		 * @param 
		 * */
	   
	   public static void selectAllSalesRate() {
		  
		  try{
			  List<String> list   =salesService.selectAllSalesRate();
			  EndView.printSalesRate(list);
			  
		   }catch(Exception e){
			   FailView.errorMessage(e.getMessage());
		   }
		  
		  
	  }
	   
	    /*
		 *   누적 상품별 판매  순위 5위
		 * @param 
		 * */
	   
	  public static void  selectAllSalesRank() {
		  
		  try{  
			   List<Product> list   =salesService.selectAllSalesRank();
			   EndView.printSalesRank(list);
		   }catch(Exception e){
			   FailView.errorMessage(e.getMessage());
		   }
		 	  
	  }
	  
	  
	
	  
	  
	  
	  
	  
	  
	  
	  
	 
	 
	 
	

}
