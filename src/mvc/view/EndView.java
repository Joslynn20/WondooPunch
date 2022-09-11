package mvc.view;

import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.dto.IssuedCoupon;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.dto.Product;

public class EndView {
	/**
	 * 성공메시지 출력
	 */

	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * 고객 정보 조회
	 */
	public static void printCustomer(Customer customer) {
		System.out.println("\n------------" + customer.getUserName() + "님의 회원정보------------");
		System.out.println(customer);
	}

	/**
	 * 주문내역 조회
	 */
	public static void printOrder(List<Orders> orderList) {
		System.out.println("--------총 " + orderList.size() + "건의 주문 내역--------\n");
		for (Orders order : orderList) {
			System.out.println(order + "\n");
			for (OrderLine orderLine : order.getOrderLinelist()) {
				System.out.println("  ▶ " + orderLine);
			}
			System.out.println();
		}

	}
	
	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @param list
	 */
	public static void printSelectAllCoupon(List<Coupon> list) {
		System.out.println("---쿠폰목록 리스트---");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("----쿠폰 총: " + list.size() + "장----");

	}

	/**
	 * 관리자메뉴 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 * 
	 * @param coupon2
	 */
	public static void printSelectCouponByCouponCodeByAdmin(Coupon coupon) {
		System.out.println("---쿠폰목록 리스트---");
		
			System.out.println(coupon);
		
	}

	/**
	 * 관리자메뉴 - 쿠폰등록 안내
	 */

	public static void printInSert() {
		System.out.println("등록됐습니다");
	}

	/**
	 * 관리자메뉴 - 쿠폰삭제 안내
	 */
	public static void printDelete() {
		System.out.println("삭제됐습니다");
	}

	/**
	 * 고객 - 쿠폰조회
	 * 
	 * @param list
	 */

	public static void printSelectCouponByUserId(List<IssuedCoupon> list) {
		System.out.println("---쿠폰목록 리스트---");
		for (IssuedCoupon issuedcoupon : list) {
			System.out.println(issuedcoupon);
		}
		System.out.println("----쿠폰 총: " + list.size() + "장----");

	}

	/**
	 * 관리자 - 옵션전체검색
	 * 
	 * @param list
	 */

	public static void printSelectAllOption(List<Option> list) {
		System.out.println("---옵션목록 리스트---");
		for (Option option : list) {
			System.out.println(option);
		}
	}

	/**
	 * 관리자 - 옵션코드에 대한 옵션정보검색
	 * 
	 * @param option2
	 */

	public static void printOptionSelectByOptionCode(Option option) {
		System.out.println("---옵션목록 리스트---");
		
			System.out.println(option);
		}
	

	/**
	 * 상품코드에 대한 옵션정보검색
	 * 
	 * @param list
	 */

	public static void printSelectOptionByProductCode(List<Option> list) {
		System.out.println("---옵션목록 리스트---");
		for (Option option : list) {
			System.out.println(option);
		}
	}
	
	
	/**
	 * 고객-상품검색 관련 출력
	 * */
	public static void printProductList(List<Product> list ) {
		
		  System.out.println( "******************상품의 갯수:"+list.size()+"************************"); 
	      System.out.println("카테고리       상품코드       상품이름        상품가격      상품설명");
	        for(Product  p : list ) {
	         String categoryName = null; 	
	        if(p.getCategoryCode().equals("B")) {  	
	        	categoryName="음료";
	        }else {
	        	categoryName="디저트";
	        }	  
	          System.out.printf("%5s|%6s|%-11s\t|%-6d|%-15S",categoryName,p.getProductCode(),p.getProductName(),p.getProductPrice(),p.getProductDetail()); 
	    	  System.out.println(); 
	     }
		
	}
	
	
	/**
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

}
