package mvc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dao.OrderDAO;
import mvc.dao.OrderDAOImpl;
import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class OrderServiceImpl implements OrderService {
	OrderDAO orderDAO = new OrderDAOImpl();

	/**
	 * 주문하기
	 * */
	@Override
	public void insertOrder(Orders order) throws SQLException, AddException, NotFoundException {
		int result = orderDAO.insertOrder(order);
		if (result == 0) {
			throw new AddException("주문 등록에 실패했습니다.");
		}
		
	}

	/**
	 * 퀵오더
	 * */
	@Override
	public List <Orders> QuickOrder(String userId) throws SQLException, AddException, NotFoundException {
		List <Orders> list = orderDAO.QuickOrder(userId);
		
		if(list==null || list.isEmpty()) {
			throw new NotFoundException(userId+"님의 최근 주문내역이 없습니다.");
		}
		return list;
	}

	/**
	 * 주문 내역 보기 - 고객
	 * */
	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException, NotFoundException {
		List<Orders> list= orderDAO.selectOrdersByUserId(userId);
		if(list==null || list.isEmpty())throw new SQLException(userId+"님의 주문내역이 없습니다.");
		return list;
	}

	/**
	 * 주문 내역 보기 - 관리자
	 * */
	@Override
	public List<Orders> selectAllOrders() throws SQLException, NotFoundException {
		List<Orders> list = orderDAO.selectAllOrders();
		if(list==null || list.isEmpty())throw new SQLException("주문내역이 없습니다.");
		return list;
	}

}
