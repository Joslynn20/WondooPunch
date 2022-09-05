package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CartDAO;
import mvc.dao.CartDAOImpl;
import mvc.dto.Cart;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;

public class CartServiceImpl implements CartService {

	CartDAO cartDAO = new CartDAOImpl();

	public boolean addCart(Integer cartQty, Integer cartPrice, String productCode, String userId,
			CoffeeOption coffeeOption, DesertOption desertOption) throws SQLException {
		Integer newKey = cartDAO.generateCartKey();
		cartDAO.addCart(newKey, cartQty, cartPrice, productCode, userId);
		if (coffeeOption != null) {
			cartDAO.addCartCoffeeOption(newKey, coffeeOption.getShotQty(), coffeeOption.getCreamQty(),
					coffeeOption.getSyrupQty());
		}

		return true;

	}

	public boolean addCart(Integer cartQty, Integer cartPrice, String productCode, String userId,
			DesertOption desertOption) throws SQLException {
		Integer newKey = cartDAO.generateCartKey();
		cartDAO.addCart(newKey, cartQty, cartPrice, productCode, userId);
		if (desertOption != null) {
			cartDAO.addCartDesertOption(newKey, desertOption.getHotQty(), desertOption.getCheeseQty());

		}

		return true;

	}

	public List<Cart> getCart(String userId) throws SQLException {

		// 데이터 조회
		List<Cart> cartList = cartDAO.getCart(userId);
		// 장바구니번호,상품코드,상품명,가격,개수,샷,시럽,크림,핫,치즈

		return cartList;
	}

	// 컨트롤러에서 입력은 값을 서비스에서 처리한다.
	// 서비스는 입력받은값을 디에이오에 전달한다.
	// 디에오는 입력받은값을 쿼리에 적용하여 디비에 요청한다.

	public List<Cart> updateCart(String userId, String productCode, Integer quantity) {
		// 유저아이디 , 카트번호, 개수, 샷,시럽,크림,핫,치즈

		// 샷,시럽,크림 한개라도 있을시 카트번호로 업데이트
		// 핫,치즈 한개라도 있을시 카트번호로 업데이트

		return null;
	}

	@Override
	public boolean updateCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup,
			Integer cream, Integer hot, Integer cheese) throws SQLException {

		cartDAO.updateCart(cartNo, productCode, cartQty, shot, syrup, cream, hot, cheese);

		return false;
	}

	public boolean removeCart(Integer cartNo) throws SQLException {
		cartDAO.removeCart(cartNo);
		return false;
	}

	@Override
	public boolean removeAllCart(String userId) throws SQLException {
		cartDAO.removeAllCart(userId);
		return false;
	}

	@Override
	public boolean removeCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup,
			Integer cream, Integer hot, Integer cheese) throws SQLException {

		return false;
	}
}
