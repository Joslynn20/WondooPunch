package mvc.controller;

import java.util.List;

import mvc.dto.Orders;
import mvc.service.OrderService;
import mvc.service.OrderServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class OrderController {
	private static OrderService orderService = new OrderServiceImpl();

	/**
	 * 주문하기
	 * 
	 * @param order
	 */
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
	 * 
	 * @param userId
	 */
	public static void QuickOrder(String userId) {
		try {
			List<Orders> list = orderService.selectQuickOrder(userId);
			// EndView.printOrder(list);
			// 주문하기 다시 호출
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 주문 내역 보기 - 고객
	 * 
	 * @param userId
	 */
	public void selectOrdersByUserId(String userId) {
		try {
			List<Orders> list = orderService.selectOrdersByUserId(userId);
			// EndView.printOrder(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 주문 내역 보기 - 관리자
	 */
	public void selectAllOrders() {
		try {
			List<Orders> list = orderService.selectAllOrders();
			// EndView.printOrder(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}
