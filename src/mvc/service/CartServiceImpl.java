package mvc.service;
import java.sql.SQLException;
import java.util.List;

import mvc.dao.CartDAO;
import mvc.dao.CartDAOImpl;
import mvc.dto.Cart;
import mvc.dto.CoffeeOption;

public class CartServiceImpl implements CartService {
	
	CartDAO cartDAO = new CartDAOImpl();
	

public boolean addCart(Integer cartQty, Integer cartPrice, String productCode, String userId, CoffeeOption coffeeOption) throws SQLException {
		Integer newKey = cartDAO.generateCartKey();
		cartDAO.addCart(newKey, cartQty, cartPrice, productCode, userId);
		if (coffeeOption != null) {
			cartDAO.addCartCoffeeOption(newKey, coffeeOption.getShotQty(), coffeeOption.getCreamQty(), coffeeOption.getSyrupQty());
		}
		
		return true;
		


	        
	    }

	    public List<Cart> getCart(String userId) throws SQLException {

	        // 데이터 조회
	        List<Cart> cartList = cartDAO.getCart(userId);
	        // 장바구니번호,상품코드,상품명,가격,개수,샷,시럽,크림,핫,치즈
	        
	        return cartList;
	    }

	    public List<Cart> updateCart(String userId, String productCode, Integer quantity) {
			//유저아이디 , 카트번호, 개수, 샷,시럽,크림,핫,치즈
			
			// 샷,시럽,크림 한개라도 있을시 카트번호로 업데이트 
			// 핫,치즈 한개라도 있을시 카트번호로 업데이트 
			
	        
	        return null;
	    }

	    public List<Cart> removeCart(String userId, String productCode) {

	        // 데이터 상품 삭제, 결과 리턴
	        return null;
	    }

	    public List<Cart> removeAllCart(String userId) {

	        // 데이터 전체삭제 결과 리턴
	        return null;
	    }
	}

