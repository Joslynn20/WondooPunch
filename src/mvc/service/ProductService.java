package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public interface ProductService {

	/**
	 * 전체 상품 조회
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Product> selectAllProduct() throws SQLException, NotFoundException;

	/**
	 * 카테고리별 상품조회
	 * 
	 * @param categoryName
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Product> selectProductByCategoryName(String categoryName) throws SQLException, NotFoundException;

	/**
	 * 키워드로 상품 검색
	 * 
	 * @param keyword
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Product> selectProductByKeyword(String keyword) throws SQLException, NotFoundException;

	/**
	 * 상품명으로 상품 검색
	 * 
	 * @param productName
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	Product selectProductByProductName(String productName) throws SQLException, NotFoundException;

	/**
	 * 상품 코드로 상품 검색
	 * 
	 * @param productCode
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	Product selectProductByProductCode(String productCode) throws SQLException, NotFoundException;

	/**
	 * 상품 등록
	 * 
	 * @param product
	 * @throws SQLException
	 * @throws AddException
	 */
	void insertProduct(Product product) throws SQLException, AddException;

	/**
	 * 상품 삭제
	 * 
	 * @param productCode
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void deleteProduct(String productCode) throws SQLException, NotFoundException;

	/**
	 * 상품 수정
	 * 
	 * @param product
	 * @throws SQLException
	 * @throws ModifyException
	 */
	void updateProduct(Product product) throws SQLException, ModifyException;

}
