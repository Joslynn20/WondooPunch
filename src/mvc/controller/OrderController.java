package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OrderController {
	
	/**
	 * 주문하기
	 * */
	void insertOrder(Orders order);
	
	
	/**
	 * 퀵오더
	 * */
	void QuickOrder(String userId);
	
	/**
	 * 주문 내역 보기 - 고객
	 * */
	void selectOrdersByUserId(String userId);
	
	/**
	 * 주문 내역 보기 - 관리자
	 * */
	void selectAllOrders();
		

}
