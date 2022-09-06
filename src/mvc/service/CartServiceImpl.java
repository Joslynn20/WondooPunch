package mvc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.dao.CartDAO;
import mvc.dao.CartDAOImpl;
import mvc.dto.Cart;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CartServiceImpl implements CartService {

	CartDAO cartDAO = new CartDAOImpl();
	OptionService optionService = new OptionServiceImpl();

	@Override
	public void addCart(Cart cart) throws SQLException, AddException, NotFoundException {

		int result = cartDAO.addCart(cart);
		if (result == 0) {
			throw new AddException("장바구니 등록에 실패했습니다.");
		}

	}

	/**
	 * 장바구니 조회
	 */
	public List<Cart> getCart(String userId) throws SQLException, NotFoundException {
		// 데이터 조회
		// 장바구니번호,상품코드,상품명,가격,개수,샷,시럽,크림,핫,치즈

		List<Cart> cartList = cartDAO.getCart(userId);

		// 옵션 가격을 가져오고 상품에 옵션을 추가한 가격을 장바구니 가격으로 한다.
		Map<String, Integer> optionMap = new HashMap<>();
		List<Option> options = optionService.optionSelect();
		for (Option option : options) {
			optionMap.put(option.getOptionCode(), option.getOptionPrice());
		}

		for (Cart cart : cartList) {
			int cartPrice = cart.getProductPrice();
			if (cart.getShotQty() > 0) {
				cartPrice += optionMap.get("B01") * cart.getShotQty();
			}
			if (cart.getCreamQty() > 0) {
				cartPrice += optionMap.get("B02") * cart.getCreamQty();
			}
			if (cart.getSyrupQty() > 0) {
				cartPrice += optionMap.get("B03") * cart.getSyrupQty();
			}
			if (cart.getHotQty() > 0) {
				cartPrice += optionMap.get("D01") * cart.getHotQty();
			}
			if (cart.getCheeseQty() > 0) {
				cartPrice += optionMap.get("D02") * cart.getCheeseQty();
			}
			cart.setCartPrice(cartPrice);
		}

		if (cartList == null || cartList.isEmpty()) {
			throw new NotFoundException(userId + "님의 장바구니에 담긴 상품이 없습니다 . .");
		}

		return cartList;

	}

	/*	*//**
			 * 장바구니 수정 전 리스트
			 *//*
				 * public List<Cart> updateCart(String userId, String productCode, Integer
				 * quantity) {
				 * 
				 * return null; }
				 */

	/**
	 * 장바구니 업데이트 카트 번호로 업데이트 한다.
	 * 
	 */
	@Override
	public int updateCart(Cart cart) throws SQLException {
		return cartDAO.updateCart(cart);
	}

	public int removeCart(int cartNo) throws SQLException, NotFoundException {
		cartDAO.removeCart(cartNo);
		int result = cartDAO.removeCart(cartNo);
		return result;

	}

	@Override
	public boolean removeAllCart(String userId) throws SQLException, NotFoundException {
		cartDAO.removeAllCart(userId);
		return false;
	}
}

// 컨트롤러에서 입력은 값을 서비스에서 처리한다.
// 서비스는 입력받은값을 디에이오에 전달한다.
// 디에오는 입력받은값을 쿼리에 적용하여 디비에 요청한다.
