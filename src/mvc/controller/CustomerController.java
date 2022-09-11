package mvc.controller;

import mvc.dto.Customer;
import mvc.service.CustomerService;
import mvc.service.CustomerServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class CustomerController {
	private static CustomerService customerService = new CustomerServiceImpl();

	/**
	 * 회원 가입
	 * 
	 * @param customer
	 */
	public static void insertCustomer(Customer customer) {
		try {
			customerService.insertCustomer(customer);
			EndView.printMessage("\n" + customer.getUserName() + "님의 회원가입이 완료됐습니다.");
			CouponController.insertJoinCoupon(customer.getUserId());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 회원정보수정 - 비밀번호 변경
	 * 
	 * @param userId
	 * @param Oldpw
	 * @param newPw
	 */
	public static void updateCustomerPw(String userId, String Oldpw, String newPw) {
		try {
			customerService.updateCustomerPw(userId, Oldpw, newPw);
			EndView.printMessage("\n" + userId + "님의 비밀번호가 변경되었습니다.");

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 회원정보 수정 - 전화번호 변경
	 * 
	 * @param userId
	 * @param userPw
	 * @param newPhoneNo
	 */
	public static void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) {
		try {
			customerService.updateCustomerPhoneNo(userId, userPw, newPhoneNo);
			EndView.printMessage("\n" + userId + "님의 전화번호가 변경되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 회원 탈퇴
	 * 
	 * @param userId
	 * @param userPw
	 */

	public static void deleteCustomer(String userId, String userPw) {
		try {
			customerService.deleteCustomer(userId, userPw);
			EndView.printMessage("\n" + userId + "님이 탈퇴하셨습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 마이페이지 회원정보 조회
	 * 
	 * @param userId
	 * @param userPw
	 */
	public static void selectCustomer(String userId, String userPw) {
		try {
			Customer customer = customerService.selectCustomer(userId, userPw);
			// 추가예정
			// EndView.printCustomer(customer);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 로그인 기능
	 * 
	 * @param userId
	 * @param userPw
	 */
	public static void login(String userId, String userPw) {
		try {
			Customer customer = customerService.login(userId, userPw);
			EndView.printMessage("\n\n" + customer.getUserName() + "님이 로그인하셨습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * ID 찾기
	 * 
	 * @param userName
	 * @param userPhoneNo
	 */
	public static void selectID(String userName, String userPhoneNo) {
		try {
			String userId = customerService.selectID(userName, userPhoneNo);
			EndView.printMessage("\n\n" + userName + "님의 아이디는 " + userId + "입니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 비밀번호 찾기
	 * 
	 * @param userId
	 * @param userPhoneNo
	 */

	public static void searchPw(String userId, String userPhoneNo) {
		try {
			String userPw = customerService.selectPw(userId, userPhoneNo);
			EndView.printMessage("\n\n" + userId + "님의 비밀번호는 " + userPw + "입니다.");

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
