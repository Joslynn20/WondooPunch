package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;

public interface SalesDAO {

	/**
	 * 일별 판매 총액, 주문 완료 건수 조회
	 * 
	 * @param date
	 * @return String
	 * @throws SQLException
	 */
	String selectSalesBydate(String date) throws SQLException;

	/**
	 * 누적 판매총액, 주문 완료 건수 조회
	 * 
	 * @return String
	 * @throws SQLException
	 */
	String selectAllSales() throws SQLException;

	/**
	 * 제품별 일별 판매 갯수
	 * 
	 * @param date
	 * @return List<String>
	 * @throws SQLException
	 */
	List<String> selectSalesRateBydate(String date) throws SQLException;

	/**
	 * 일별 제품 판매순위 (5위까지)
	 * 
	 * @param date
	 * @return List<Product>
	 * @throws SQLException
	 */
	List<Product> selectSalesRankBydate(String date) throws SQLException;

	/**
	 * 제품별 누적 판매 갯수
	 * 
	 * @return List<String>
	 * @throws SQLException
	 */
	List<String> selectAllSalesRate() throws SQLException;

	/**
	 * 누적 제품 판매순위(5위까지)
	 * 
	 * @return List<Product>
	 * @throws SQLException
	 */
	List<Product> selectAllSalesRank() throws SQLException;

}
