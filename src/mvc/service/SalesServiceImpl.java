package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.OrderDAO;
import mvc.dao.OrderDAOImpl;
import mvc.dao.SalesDAO;
import mvc.dao.SalesDAOImpl;
import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.NotFoundException;

public class SalesServiceImpl implements SalesService {
   
	 private SalesDAO salesDAO = new SalesDAOImpl(); 
	 private  OrderDAO orderDAO = new OrderDAOImpl();
	 
	public SalesServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 *  해당날에  총판매액 , 주문  완료건수 , 주문 상품 총량
	 *  
	 * */
	@Override
	public String selectSalesBydate(String date) throws SQLException , NotFoundException {
		// TODO Auto-generated method stub
		
		     String result= salesDAO.selectSalesBydate(date);         
		  if(result==null) throw new NotFoundException(date+"에 해당하는 매출이 존재 하지 않습니다");
		   return result;       
		 
	}
	
	/*
	 *  누적  총판매액 , 주문  완료건수 , 주문 상품 총량
	 *  
	 * */

	@Override
	public String selectAllSales() throws SQLException ,NotFoundException {
		// TODO Auto-generated method stub
		
		 String result= salesDAO.selectAllSales();         
		  if(result==null) throw new NotFoundException("누적 매출정보가 존재 하지 않습니다");
		   return result;       
		 
		
		
		
	}

	
	/*
	 *  해당날에 대한  상품별 판매 수량
	 *  
	 * */
	@Override
	public List<String> selectSalesRateBydate(String date) throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		
 		     List<String> list   =salesDAO.selectSalesRateBydate(date);
 		     if(list.isEmpty()|| list==null )
 		    throw new NotFoundException(date+"에 해당하는 판매 상품 정보가 존재 하지 않습니다");
 		     	 
 		    return list;	 
 		     
 		     
	
	}

	/*
	 *  해당날에 대한  상품 판매 순위 1-5위
	 *  
	 * */
	
	@Override
	public List<Product> selectSalesRankBydate(String date) throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		List<Product> list   =salesDAO.selectSalesRankBydate(date);
	     if(list.isEmpty()|| list==null )
	    throw new NotFoundException(date+"에 해당하는 판매 정보가 존재 하지 않습니다");
	     	 
	    return list;	 
		
		
	}
	
	/*
	 *    상품별 누적 판매량
	 *  
	 * */

	@Override
	public List<String> selectAllSalesRate()throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		
		 
		 List<String>   list =salesDAO.selectAllSalesRate();
		 if(list.isEmpty()|| list==null )
			  throw new NotFoundException(" 누적 판매 상품 정보가 존재 하지 않습니다");
			     	 
			    return list;	
		
	}

	/*
	 *    누적 판매순위  1-5위 상품
 	 *  
	 * */

	@Override
	public List<Product> selectAllSalesRank() throws SQLException,NotFoundException {
		// TODO Auto-generated method stub

		List<Product>   list =salesDAO.selectAllSalesRank();
		 if(list.isEmpty()|| list==null )
			  throw new NotFoundException(" 누적 판매 상품저가 존재 하지 않습니다");
			     	 
			    return list;	
		
	
	}

	


}
