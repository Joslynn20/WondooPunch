package mvc.controller;

import mvc.dto.Customer;

public interface CustomerController {

	/**
	 * 회원 가입
	 */
	void insertCustomer(Customer customer);

	/**
	 * 회원정보수정 - 비밀번호 변경
	 */
	void updateCustomerPw(String userId, String Oldpw, String newPw);

	/**
	 * 회원정보 수정 - 전화번호 변경
	 */
	void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo);

	/**
	 * 회원 탈퇴
	 */
	void delete(String userId, String userPw);

	/**
	 * 마이페이지 회원정보 조회
	 */
	void searchCustomer(String userId, String userPw);

	/**
	 * 로그인 하기
	 */
	void login(String userId, String userPw);

	/**
	 * ID 찾기
	 */
	void searchID(String userName, String userPhoneNo);

	/**
	 * 비밀번호 찾기
	 */
	void searchPw(String userId, String userPhoneNo);

}
