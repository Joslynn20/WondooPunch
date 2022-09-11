package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.exception.NotFoundException;
import mvc.service.CartService;
import mvc.service.CartServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class CartController {

	private static CartService cartService = new CartServiceImpl();

	/**
	 * 장바구니 담기
	 * 
	 * @param cart
	 */

	public static void insertCart(Cart cart) {

		try {
			cartService.insertCart(cart);

			EndView.printMessage("상품을 장바구니에 성공적으로 담았습니다.");

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 장바구니 조회하기
	 * 
	 * @param userId
	 */

	public static void selectCart(String userId) {

		try {
			List<Cart> list = cartService.selectCart(userId);
			EndView.printCartList(list);
		} catch (Exception e) {

			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니 번호로 수량 수정하기
	 * 
	 * @param cartNo
	 * @param cartQty
	 */

	public static void updateCart(Cart cart) {

		try {
			cartService.updateCart(cart);

			EndView.printMessage("장바구니가 성공적으로  수정되었습니다. ");

		} catch (SQLException | NotFoundException e) {

			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 장바구니 번호로 해당 상품 삭제하기
	 * 
	 * @param cartNo
	 */

	public static void deleteCartByCartNo(int cartNo) {

		try {
			cartService.deleteCartByCartNo(cartNo);

			EndView.printMessage(cartNo + "번에 해당하는 상품이 장바구니에서 성공적으로 삭제되었습니다.");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * userID로 장바구니 전체 삭제하기
	 * 
	 * @param userId
	 */

	public static void deleteCartByUserId(String userId) {
		try {
			cartService.deleteCartByUserId(userId);

			EndView.printMessage(userId + "님의 장바구니가 비우기를 성공했습니다.");

		} catch (SQLException | NotFoundException e) {

			FailView.errorMessage(e.getMessage());
		}

	}

}
