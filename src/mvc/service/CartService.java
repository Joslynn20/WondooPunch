package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.dto.CoffeeOption;

public interface CartService {
	public boolean addCart(Integer cartQty, Integer cartPrice, String productCode, String userId, CoffeeOption coffeeOption) throws SQLException;
	
	
    public List<Cart> getCart(String userId) throws SQLException;
    
    public List<Cart> updateCart(String userId, String productCode, Integer quantity);
    
    
    
}
