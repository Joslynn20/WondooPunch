package mvc.dao;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.exception.DuplicatedException;

public interface CustomerDAO {

	/**
	 * 회원 가입
	 * 
	 * @param customer
	 * @return
	 * @throws SQLException
	 * @throws DuplicatedException
	 */
	int insertCustomer(Customer customer) throws SQLException, DuplicatedException;

	/**
	 * 회원정보수정 - 비밀번호 변경
	 * 
	 * @param userId
	 * @param Oldpw
	 * @param newPw
	 * @return
	 * @throws SQLException
	 */
	int updateCustomerPw(String userId, String Oldpw, String newPw) throws SQLException;

	/**
	 * 회원정보 수정 - 전화번호 변경
	 * 
	 * @param userId
	 * @param userPw
	 * @param newPhoneNo
	 * @return
	 * @throws SQLException
	 */
	int updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) throws SQLException;

	/**
	 * 회원 탈퇴
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws SQLException
	 */
	int deleteCustomer(String userId, String userPw) throws SQLException;

	/**
	 * 회원 정보 조회 - 마이페이지
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws SQLException
	 */
	Customer selectCustomer(String userId, String userPw) throws SQLException;

	/**
	 * 로그인하기
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws SQLException
	 */
	Customer login(String userId, String userPw) throws SQLException;

	/**
	 * ID 찾기
	 * 
	 * @param userName
	 * @param userPhoneNo
	 * @return
	 * @throws SQLException
	 */
	String selectID(String userName, String userPhoneNo) throws SQLException;

	/**
	 * 비밀번호 찾기
	 * 
	 * @param userId
	 * @param userPhoneNo
	 * @return
	 * @throws SQLException
	 */
	String selectPw(String userId, String userPhoneNo) throws SQLException;

}
