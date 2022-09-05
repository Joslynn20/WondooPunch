package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.OrderDAO;
import mvc.dao.OrderDAOImpl;
import mvc.dao.SalesDAO;
import mvc.dao.SalesDAOImpl;
import mvc.dto.Orders;
import mvc.exception.NotFoundException;

public class SalesServiceImpl implements SalesService {
   
	 private SalesDAO salesDAO = new SalesDAOImpl(); 
	 private  OrderDAO orderDAO = new OrderDAOImpl();
	 
	public SalesServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String selectSalesBydate(String date) throws SQLException , NotFoundException {
		// TODO Auto-generated method stub
		
		      String result= salesDAO.selectSalesBydate(date);         
		  if(result==null) throw new NotFoundException(date+"에 해당하는 매출이 존재 하지 않습니다");
		   return result;       
		 
	}

	@Override
	public String selectSalesAll() throws SQLException ,NotFoundException {
		// TODO Auto-generated method stub
		
		 String result= salesDAO.selectSalesAll();         
		  if(result==null) throw new NotFoundException(" 매출정보가 존재 하지 않습니다");
		   return result;       
		 
		
		
		
	}

	@Override
	public List<String> selectSalesRateBydate(String date) throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		
 		     List<String> list   =salesDAO.selectSalesRateBydate(date);
 		     if(list.isEmpty()|| list==null )
 		    throw new NotFoundException(date+"에 해당하는 판매 상품 정보가 존재 하지 않습니다");
 		     	 
 		    return list;	 
 		     
 		     
	
	}

	@Override
	public List<String> selectSalesRankRateBydate(String date) throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		List<String> list   =salesDAO.selectSalesRateBydate(date);
	     if(list.isEmpty()|| list==null )
	    throw new NotFoundException(date+"에 해당하는 판매 정보가 존재 하지 않습니다");
	     	 
	    return list;	 
		
		
	}

	@Override
	public List<String> selectSalesRateByallDate() throws SQLException,NotFoundException {
		// TODO Auto-generated method stub
		
		 
		 List<String>   list =salesDAO.selectSalesRankRateByallDate();
		 if(list.isEmpty()|| list==null )
			  throw new NotFoundException(" 누적 판매 상품 정보가 존재 하지 않습니다");
			     	 
			    return list;	
		
	}

	@Override
	public List<String> selectSalesRankRateByallDate() throws SQLException,NotFoundException {
		// TODO Auto-generated method stub

		List<String>   list =salesDAO.selectSalesRankRateByallDate();
		 if(list.isEmpty()|| list==null )
			  throw new NotFoundException(" 누적 판매 상품저가 존재 하지 않습니다");
			     	 
			    return list;	
		
	
	}

	@Override
	public List<Orders> selectAllOrders() throws SQLException ,NotFoundException {
		// TODO Auto-generated method stub
		         List<Orders> list=    orderDAO.selectAllOrders();
		         if(list.isEmpty()|| list==null )
					  throw new NotFoundException(" 누적 판매 상품저가 존재 하지 않습니다");
					     	 
					    return list;
		   
		
	  }

}
