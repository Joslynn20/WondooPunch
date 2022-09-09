package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.dto.DetailOption;
import mvc.exception.NotFoundException;
import mvc.service.CartService;
import mvc.service.CartServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class CartController {

	private static CartService cartService = new CartServiceImpl();

	// 추가 하기
	public static void insertCart(Cart cart, List<DetailOption> list){
		try {
			cartService.insertCart(cart, list);
			EndView.printMessage("장바구니에 담기에 성공했습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	// 조회하기

	public static void selectCart(String userId) {

		try {
			int sum = 0;
			List<Cart> list = cartService.selectCart(userId);
			for (Cart c : list) {
				System.out.println(c);
				sum = sum + c.getCartPrice();
			
			}
			
			System.out.println("총합 : " + sum);
			EndView.printMessage("장바구니 조회에 성공했습니다. ");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	// 수정하기

	public static void updateCart(Cart cart) {

		try {
			cartService.updateCart(cart);
			EndView.printMessage("장바구니 수정되었습니다. ");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	// 개별 삭제하기

	public static void deleteCartByCartNo(int cartNo) {

		try {
			cartService.deleteCartByCartNo(cartNo);
			EndView.printMessage(cartNo + "해당 상품이 장바구니에서 삭제되었습니다.");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}
	// 전체 삭제하기

	public static void deleteCartByUserId(String userId) {
		try {
			cartService.deleteCartByUserId(userId);
			EndView.printMessage(userId + " 장바구니가 비었습니다.");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}