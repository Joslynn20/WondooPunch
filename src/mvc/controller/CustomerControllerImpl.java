package mvc.controller;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.DuplicatedException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;
import mvc.service.CustomerService;
import mvc.service.CustomerServiceImpl;

public class CustomerControllerImpl implements CustomerController {
	private CustomerService customerService = new CustomerServiceImpl();

	/**
	 * 회원 가입
	 */
	@Override
	public void insertCustomer(Customer customer) {
		try {
			customerService.insertCustomer(customer);
			/*
			 * 성공메시지 출력하기
			 */
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	/**
	 * 회원정보수정 - 비밀번호 변경
	 */
	@Override
	public void updateCustomerPw(String userId, String Oldpw, String newPw) {
		try {
			customerService.updateCustomerPw(userId, Oldpw, newPw);
			/*
			 * 성공메시지 출력하기
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 회원정보 수정 - 전화번호 변경
	 */
	@Override
	public void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) {
		try {
			customerService.updateCustomerPhoneNo(userId, userPw, newPhoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 회원 탈퇴
	 */
	@Override
	public void delete(String userId, String userPw) {
		try {
			customerService.delete(userId, userPw);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 마이페이지 회원정보 조회
	 */
	@Override
	public void searchCustomer(String userId, String userPw) {
		try {
			customerService.searchCustomer(userId, userPw);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 로그인 하기
	 */
	@Override
	public void login(String userId, String userPw) {
		try {
			customerService.login(userId, userPw);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ID 찾기
	 */
	@Override
	public void searchID(String userName, String userPhoneNo) {
		try {
			customerService.searchID(userName, userPhoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 비밀번호 찾기
	 */
	@Override
	public void searchPw(String userId, String userPhoneNo) {
		try {
			customerService.searchPw(userId, userPhoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
