package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CartService {

	// 장바구니 등록

	void addCart(Cart cart) throws SQLException, AddException, NotFoundException;

	// 장바구니 조회
	List<Cart> getCart(String userId) throws SQLException, NotFoundException;

	// 장바구니 수정
	int updateCart(Cart cart) throws SQLException, NotFoundException;

	// 장바구니 개별 삭제 장바구니 번호려
	public int removeCart(int cartNo) throws SQLException, NotFoundException;

	// 장바구니 전체삭제
	boolean removeAllCart(String userId) throws SQLException, NotFoundException;

}
