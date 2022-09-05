package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.exception.DuplicatedException;

public interface CustomerDAO {
	
	/**
	 * 회원 가입
	 * */
	int insertCustomer(Customer customer) throws SQLException, DuplicatedException;
	
	/**
	 * 회원정보수정 - 비밀번호 변경
	 * */
	int updateCustomerPw(String userId, String Oldpw, String newPw) throws SQLException;
	
	/**
	 * 회원정보 수정 - 전화번호 변경
	 * */
	int updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) throws SQLException;
	
	/**
	 * 회원 탈퇴
	 * */
	int deleteCustomer(String userId, String userPw) throws SQLException;
	
	/**
	 * 회원 정보 조회 - 마이페이지
	 * */
	Customer searchCustomer(String userId, String userPw) throws SQLException;
		
	/**
	 * 로그인하기
	 * */
	Customer login(String userId, String userPw) throws SQLException;
	
	/**
	 * ID 찾기
	 * */
	String searchID(String userName, String userPhoneNo) throws SQLException;
	
	
	/**
	 * 비밀번호 찾기
	 * */
	String searchPw(String userId, String userPhoneNo) throws SQLException;

}
