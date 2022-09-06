package mvc.controller;

import java.util.List;

import mvc.dto.Orders;
import mvc.service.OrderService;
import mvc.service.OrderServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class OrderController{
	private static OrderService orderService = new OrderServiceImpl();
	
	/**
	 * 주문하기
	 * */
	public static void insertOrder(Orders order) {
		try {
			orderService.insertOrder(order);
			EndView.printMessage("주문에 성공했습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	/**
	 * 퀵오더
	 * */
	public static void QuickOrder(String userId) {
		try {
			List <Orders> list = orderService.QuickOrder(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 주문 내역 보기 - 고객
	 * */
	public void selectOrdersByUserId(String userId) {
		try {
			orderService.selectOrdersByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 주문 내역 보기 - 관리자
	 * */
	public void selectAllOrders() {
		try {
			orderService.selectAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
