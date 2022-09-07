package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OrderService {

	/**
	 * 주문하기
	 * 
	 * @param order
	 * @throws SQLException
	 * @throws AddException
	 * @throws NotFoundException
	 */
	void insertOrder(Orders order) throws SQLException, AddException, NotFoundException;

	/**
	 * 주문 내역 보기 - 고객
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */

	List<Orders> selectOrdersByUserId(String userId) throws SQLException, NotFoundException;

	/**
	 * 퀵오더
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws AddException
	 * @throws NotFoundException
	 */
	List<Orders> selectQuickOrder(String userId) throws SQLException, AddException, NotFoundException;

	/**
	 * 주문 내역 보기 - 관리자
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Orders> selectAllOrders() throws SQLException, NotFoundException;

}
