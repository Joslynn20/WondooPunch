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

	@Override
	public void insertOrder(Orders order) throws SQLException, AddException, NotFoundException {
		int result = orderDAO.insertOrder(order);
		if (result == 0) {
			throw new AddException("주문 등록에 실패했습니다.");
		}
		
	}

	/**
	 *OrderNo 시퀀스 currVal = 최대값 -- orderDAO에 추가
	 * */
	@Override
	public List <Orders> insertQuickOrder(String userId) throws SQLException, AddException, NotFoundException {
		List <Orders> list = orderDAO.selectOrdersByUserId(userId);
		List <Orders> resultList = new ArrayList<Orders>();
		
		if(list==null || list.isEmpty()) {
			throw new NotFoundException(userId+"님의 최근 주문내역이 없습니다.");
		}
		else {
			if (list.size()>=3) {
				resultList.add(list.get(list.size()-3));
				resultList.add(list.get(list.size()-2));
				resultList.add(list.get(list.size()-1));
			}
			else resultList = list;
			
		}
		return resultList;
	}

	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException, NotFoundException {
		List<Orders> list= orderDAO.selectOrdersByUserId(userId);
		if(list==null || list.size()==0)throw new SQLException(userId+"님의 주문내역이 없습니다.");
		return list;
	}

	@Override
	public List<Orders> selectAllOrders() throws SQLException, NotFoundException {
		List<Orders> list = orderDAO.selectAllOrders();
		if(list==null || list.size()==0)throw new SQLException("주문내역이 없습니다.");
		return list;
	}

}
