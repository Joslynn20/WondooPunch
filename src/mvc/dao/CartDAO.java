package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;

public interface CartDAO {

	Boolean addCart(Integer cartNo, Integer cartQty, Integer cartPrice, String productCode, String userId)
			throws SQLException;

	Integer generateCartKey() throws SQLException;

	List<Cart> getCart(String userId) throws SQLException;

	Boolean addCartCoffeeOption(Integer cartNo, Integer shot, Integer cream, Integer syrp) throws SQLException;

	Boolean addCartDesertOption(Integer cartNo, Integer hot, Integer cheese) throws SQLException;

	Boolean updateCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup, Integer cream,
			Integer hot, Integer cheese) throws SQLException;

	Boolean removeCart(Integer cartNo) throws SQLException;

	Boolean removeAllCart(String userId) throws SQLException;

}
