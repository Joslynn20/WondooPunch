package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CartDAO;
import mvc.dao.CartDAOImpl;
import mvc.dto.Cart;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CartServiceImpl implements CartService {

	CartDAO cartDAO = new CartDAOImpl();

	@Override
	public void insertCart(Cart cart) throws SQLException, AddException, NotFoundException {

		int result = cartDAO.insertCart(cart);
		if (result == 0) {
			throw new AddException("상품을 장바구니에 담을 수 없습니다.");
		}

	}

	/**
	 * 장바구니 조회
	 */

	public List<Cart> selectCart(String userId) throws SQLException, NotFoundException {

		List<Cart> cartList = cartDAO.selectCart(userId);

		if (cartList == null || cartList.isEmpty()) {

			throw new NotFoundException(userId + "님의 장바구니에 담긴 상품이 없습니다 . .");
		}

		return cartList;

	}

	/**
	 * 장바구니 업데이트 카트 번호로 업데이트
	 * 
	 */
	@Override
	public void updateCart(Cart cart) throws SQLException {
		cartDAO.updateCart(cart);

	}

	public void deleteCartByCartNo(int cartNo) throws SQLException, NotFoundException {
		cartDAO.deleteCartByCartNo(cartNo);

	}

	@Override
	public void deleteCartByUserId(String userId) throws SQLException, NotFoundException {
		cartDAO.deleteCartByUserId(userId);

	}

}
