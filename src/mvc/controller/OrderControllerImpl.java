package mvc.controller;

import java.sql.SQLException;

import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.service.OrderService;
import mvc.service.OrderServiceImpl;

public class OrderControllerImpl implements OrderController{
	OrderService orderService = new OrderServiceImpl();
	
	@Override
	public void insertOrder(Orders order) {
		try {
			orderService.insertOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void QuickOrder(String userId) {
		try {
			orderService.QuickOrder(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void selectOrdersByUserId(String userId) {
		try {
			orderService.selectOrdersByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void selectAllOrders() {
		try {
			orderService.selectAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
