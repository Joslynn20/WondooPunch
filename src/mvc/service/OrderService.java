package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OrderService {
	
	/**
	 * 주문하기
	 * */
	public void insertOrder(Orders order) throws SQLException, AddException, NotFoundException;
	
	
	/**
	 * 퀵오더
	 * */
	public List<Orders> insertQuickOrder(String userId) throws SQLException, AddException, NotFoundException;
	
	/**
	 * 주문 내역 보기 - 고객
	 * */
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException, NotFoundException;
	
	/**
	 * 주문 내역 보기 - 관리자
	 * */
	public List<Orders> selectAllOrders() throws SQLException, NotFoundException;
		
	

}
