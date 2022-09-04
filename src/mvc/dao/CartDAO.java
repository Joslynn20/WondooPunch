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

}
