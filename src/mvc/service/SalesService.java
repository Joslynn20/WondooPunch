package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.NotFoundException;

public interface SalesService {



	public String selectSalesBydate(String date) throws SQLException, NotFoundException;
	
	
	
	
	
	public String selectSalesAll() throws SQLException ,NotFoundException;
  
	
	public List<String> selectSalesRateBydate(String date)  throws SQLException , NotFoundException ; 


	List<String> selectSalesRankRateBydate(String date) throws SQLException,NotFoundException;

	public List<String> selectSalesRateByallDate() throws SQLException,NotFoundException;
	
	
	List<String> selectSalesRankRateByallDate() throws SQLException,NotFoundException;
  
	List <Orders> selectAllOrders() throws SQLException,NotFoundException; 

}
