package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;

public interface ProductDAO {

	/**
	 * 전체 상품 조회
	 * 
	 * @return List<Product>
	 * @throws SQLException
	 */
	List<Product> selectAllProduct() throws SQLException;

	/**
	 * 카테고리 별 상품 조회
	 * 
	 * @param categoryName
	 * @return List<Product>
	 * @throws SQLException
	 */
	List<Product> selectProductByCategoryName(String categoryName) throws SQLException;

	/**
	 * 키워드로 상품 검색
	 * 
	 * @param keyword
	 * @return List<Product>
	 * @throws SQLException
	 */
	List<Product> selectProductByKeyword(String keyword) throws SQLException;

	/**
	 * 상품명으로 상품 검색
	 * 
	 * @param productName
	 * @return Product
	 * @throws SQLException
	 */
	Product selectProductByProductName(String productName) throws SQLException;

	/**
	 * 상품 코드로 상품 검색
	 * 
	 * @param productCode
	 * @return Product
	 * @throws SQLException
	 */
	Product selectProductByProductCode(String productCode) throws SQLException;

	/**
	 * 상품 등록
	 * 
	 * @param product
	 * @return int
	 * @throws SQLException
	 */
	int insertProduct(Product product) throws SQLException;

	/**
	 * 상품 삭제
	 * 
	 * @param productCode
	 * @return
	 * @throws SQLException
	 */
	int deleteProduct(String productCode) throws SQLException;

	/**
	 * 상품 수정
	 * 
	 * @param product
	 * @return int
	 * @throws SQLException
	 */
	int updateProduct(Product product) throws SQLException;

}
