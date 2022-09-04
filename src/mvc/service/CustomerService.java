package mvc.service;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.DuplicatedException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public interface CustomerService {
	
	/**
	 * 회원 가입
	 * */
	void insertCustomer(Customer customer) throws AddException, DuplicatedException, SQLException;
	
	/**
	 * 회원정보수정 - 비밀번호 변경
	 * */
	void updateCustomerPw(String userId, String Oldpw, String newPw) throws ModifyException, SQLException;
	
	/**
	 * 회원정보 수정 - 전화번호 변경
	 * */
	
	void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) throws ModifyException, SQLException;
	
	
	/**
	 * 회원 탈퇴
	 * */
	void delete(String userId, String userPw) throws NotFoundException, SQLException;
	
	/**
	 * 회원 정보 조회 - 마이페이지
	 * */
	Customer searchCustomer(String userId, String userPw) throws NotFoundException, SQLException;
		
	/**
	 * 로그인하기
	 * */
	Customer login(String userId, String userPw) throws NotFoundException, SQLException;
	
	/**
	 * ID 찾기
	 * */
	String searchID(String userName, String userPhoneNo) throws NotFoundException, SQLException;
	
	
	/**
	 * 비밀번호 찾기
	 * */
	String searchPw(String userId, String userPhoneNo) throws NotFoundException, SQLException;
	

}
