package mvc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.dao.CartDAO;
import mvc.dao.CartDAOImpl;
import mvc.dto.Cart;
import mvc.dto.DetailOption;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CartServiceImpl implements CartService {

	CartDAO cartDAO = new CartDAOImpl();
	OptionService optionService = new OptionServiceImpl();

	/**
	 * 장바구니 담기
	 */
	@Override
	public void insertCart(Cart cart, List<DetailOption> list) throws AddException, SQLException, NotFoundException {
		// TODO Auto-generated method stub
		int result = cartDAO.insertCart(cart, list);
		if (result == 0) {
			throw new AddException("장바구니 등록에 실패했습니다.");
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
	 * 장바구니 업데이트 카트 번호로 업데이트 한다.
	 * 
	 */
	@Override
	public void updateCart(Cart cart) throws SQLException {
		cartDAO.updateCart(cart);
	}

	public void deleteCartByCartNo(int cartNo) throws SQLException, NotFoundException {
		cartDAO.deleteCartByCartNo(cartNo);
		int result = cartDAO.deleteCartByCartNo(cartNo);

	}

	@Override
	public void deleteCartByUserId(String userId) throws SQLException, NotFoundException {
		cartDAO.deleteCartByUserId(userId);
	}
}

// 컨트롤러에서 입력은 값을 서비스에서 처리한다.
// 서비스는 입력받은값을 디에이오에 전달한다.
// 디에오는 입력받은값을 쿼리에 적용하여 디비에 요청한다.
