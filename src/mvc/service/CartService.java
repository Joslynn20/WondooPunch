package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;

public interface CartService {
	// 장바구니 등록 디저트 추가필요
	public boolean addCart(Integer cartQty, Integer cartPrice, String productCode, String userId,
			CoffeeOption coffeeOption, DesertOption desertOption) throws SQLException;

	// 장바구니 조회 
	public List<Cart> getCart(String userId) throws SQLException;

	// 장바구니 수정
	public boolean updateCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup,
			Integer cream, Integer hot, Integer cheese) throws SQLException;

	// 장바구니 삭제
	public boolean removeCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup,
			Integer cream, Integer hot, Integer cheese) throws SQLException;

	// 장바구니 전체삭제
	public boolean removeAllCart(String userId) throws SQLException;

}
