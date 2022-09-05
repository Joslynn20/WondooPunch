package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.NotFoundException;
import mvc.service.SalesService;
import mvc.service.SalesServiceImpl;
import mvc.view.FailView;

public class SalesController {

	 private static SalesService salesService  =new SalesServiceImpl( ); 
	 
	  public  static void selectSalesBydate(String date) {
		  try {
		 String result  =salesService.selectSalesBydate(date);
		 
		  
	  }catch(SQLException e){
		  
		  
		  e.printStackTrace();
		  
	  }catch(NotFoundException es ) {
		  
		  FailView.errorMessage(es.getMessage());
		  
	  }
 
  
   }
	   
	   public static void selectSalesAll() {
		   
		   try {
		  String result  =salesService.selectSalesAll();
		   }catch(SQLException e) {
			   
			   e.printStackTrace();
		   }catch(NotFoundException es) {
			   FailView.errorMessage(es.getMessage());
			   
		   }
		   
		   
		   
	   }
	  
	  
	  
	   public static void   selectSalesRateBydate(String date) {
		    
		   try{
			   
		    
			  
			   List<String> list   =salesService.selectSalesRateBydate(date);
		   }catch(SQLException e ) {
			   e.printStackTrace();
			   
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		   }
		   
	   }
	   
	  public static void   selectSalesRankRateBydate(String date) {
		  
		  
		  try{
			   
			    
			  
			   List<String> list   =salesService.selectSalesRankRateBydate(date);
		   }catch(SQLException e ) {
			   e.printStackTrace();
			   
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		   }
		   
	   }
		  
		  
		  
	  public static void selectSalesRateByallDate() {
		  
		  
		  try{
			   
			    
			  
			   List<String> list   =salesService.selectSalesRateByallDate();
		   }catch(SQLException e ) {
			   e.printStackTrace();
			   
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		   }
		  
		  
	  }
	   
	  
	  
	  
	  public static void  selectSalesRankRateByallDate() {
		  
		  
		  try{
			   
			    
			  
			   List<String> list   =salesService.selectSalesRankRateByallDate();
		   }catch(SQLException e ) {
			   e.printStackTrace();
			   
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		   }
		  
		  
		  
		  
	  }
	  
	  
	 public static void   selectAllOrders() {
		 
		 

		  try{
			   
			  
			   List<Orders> list   =salesService.selectAllOrders();
		   }catch(SQLException e ) {
			   e.printStackTrace();
			   
			   
		   }catch(NotFoundException es) {
			   
			   FailView.errorMessage(es.getMessage());
		   }
		 
		 
		 
	 }
	  
	  
	  
	  
	  
	  
	  
	  
	 
	 
	 
	

}
