package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OrderDAO {
	
	/**
	 * 주문하기
	 * 
	 * */
	int insertOrder(Orders order) throws SQLException, AddException, NotFoundException;
	
	/**
	 * 주문 내역 보기 - 고객
	 * */
	List <Orders> selectOrdersByUserId(String userId) throws SQLException;
	
	
	/**
	 * 퀵오더 주문 내역 3건 조회
	 * */
	List<Orders> QuickOrder(String userId) throws SQLException;
	
	/**
	 * 전체 주문 내역 보기 - 관리자
	 * */
	List <Orders> selectAllOrders() throws SQLException;
	
	

}
