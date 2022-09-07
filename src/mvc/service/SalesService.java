package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.NotFoundException;

public interface SalesService {

	/**
	 * 일별 판매 총액, 주문 완료 건수 조회
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	String selectSalesBydate(String date) throws SQLException, NotFoundException;

	/**
	 * 누적 판매총액, 주문 완료 건수 조회
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	String selectAllSales() throws SQLException, NotFoundException;

	/**
	 * 제품별 일별 판매 갯수
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<String> selectSalesRateBydate(String date) throws SQLException, NotFoundException;

	/**
	 * 일별 제품 판매순위 (5위까지)
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Product> selectSalesRankBydate(String date) throws SQLException, NotFoundException;

	/**
	 * 제품별 누적 판매 갯수
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<String> selectAllSalesRate() throws SQLException, NotFoundException;

	/**
	 * 누적 제품 판매순위(5위까지)
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Product> selectAllSalesRank() throws SQLException, NotFoundException;

}
