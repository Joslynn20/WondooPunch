package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CartDAO {

	// 장바구니 추가 기능
	int addCart(Cart cart) throws SQLException, AddException, NotFoundException;

	// 장바구니 조회
	List<Cart> getCart(String userId) throws SQLException, NotFoundException;

	// 장바구니 업데이트
	int updateCart(Cart cart) throws SQLException;

	// 카트번호로 장바구니 삭제
	int removeCart(int cartNo) throws SQLException, NotFoundException;

	// 유저 아이디로 장바구니 전체 삭제
	boolean removeAllCart(String userId) throws SQLException, NotFoundException;

}
