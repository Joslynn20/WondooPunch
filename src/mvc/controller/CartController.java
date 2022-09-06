package mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import mvc.dto.Cart;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.service.CartService;
import mvc.service.CartServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;

public class CartController {

	private static CartService cartService = new CartServiceImpl();

	// 추가 하기
	public static void addCart(Cart cart) {
		try {
			//
			cartService.addCart(cart);
			EndView.printMessage("장바구니에 담기에 성공했습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	// 조회하기

	public static void getCart(String userId) {

		try {
			int sum = 0;
			List<Cart> list = cartService.getCart(userId);
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

	public static void removeCart(int cartNo) {

		try {
			cartService.removeCart(cartNo);
			EndView.printMessage(cartNo + "해당 상품이 장바구니에서 삭제되었습니다.");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}
	// 전체 삭제하기

	public static void removeAllCart(String userId) {
		try {
			cartService.removeAllCart(userId);
			EndView.printMessage(userId + " 장바구니가 비었습니다.");

		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}/**
	 * 장바구니 추가 뷰 단 참고 
	 *//*
		 * Cart addCartRequest = new Cart(); addCartRequest.setUserId(userId); //필수
		 * addCartRequest.setProductCode(productCode); //필수
		 * addCartRequest.setCartQty(cartQty);//필수
		 * 
		 * addCartRequest.setShotQty(shotQty); addCartRequest.setCreamQty(creamQty);
		 * addCartRequest.setSyrupQty(syrupQty);
		 * 
		 * addCartRequest.setHotQty(hotQty); addCartRequest.setCheeseQty(cheeseQty);
		 * 
		 * cartController.addCart(addCartRequest);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * public static void printCart(String userId, String userPw, String
		 * categoryName, String productName, String keyword) throws SQLException,
		 * Throwable {
		 * 
		 * 
		 * 
		 * Scanner sc = new Scanner(System.in); System.out.
		 * println("1.장바구니 조회하기  |  2. 장바구니 수정하기   |   3. 장바구니 비우기  |  9. 되돌아가기");
		 * System.out.print("입력 > "); int menu = Integer.parseInt(sc.nextLine()); switch
		 * (menu) { case 1: // 리스트 출력 == 장바구니 조회 cartController.getCart(userId);
		 * 
		 * 
		 * System.out.println("1. 결제하기    |    2. 되돌아가기"); System.out.print("입력 > ");
		 * int no = Integer.parseInt(sc.nextLine()); switch (no) { case 1:
		 * MenuView.printPay(userId); break; case 2: MenuView.printAllOrders(userId,
		 * userPw, categoryName, productName, keyword); break; } break; case 2: // 2번기능
		 * // 리스트 출력 cartController.getCart(userId);
		 * System.out.print("수정할 장바구니 카트코드 입력 > "); String cartNo = sc.nextLine();
		 * 
		 * 
		 * System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No)"); String answer =
		 * sc.nextLine();
		 * 
		 * if (answer.equals("Yes")) { System.out.println("1. 수량 수정하기    |   2. 삭제하기");
		 * System.out.print("입력 > "); int choice = Integer.parseInt(sc.nextLine());
		 * 
		 * if (choice == 1) { System.out.print("수정 수량 > "); int cartQty =
		 * Integer.parseInt(sc.nextLine());
		 * 
		 * // 장바구니 카트 번호로 수정 update Cart updateCartRequest = new Cart();
		 * updateCartRequest.setCartNo(Integer.valueOf(cartNo));
		 * updateCartRequest.setCartQty(cartQty);
		 * cartController.updateCart(updateCartRequest); } else if (choice == 2) {
		 * 
		 * // 장바구니 개별 삭제하기 cartController.removeCart(Integer.valueOf(cartNo));
		 * 
		 * } }
		 * 
		 * break; case 3: //장바구니 비우기 cartController.removeAllCart(userId); } }
		 */
