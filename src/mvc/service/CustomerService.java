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
	 * 
	 * @param customer
	 * @throws AddException
	 * @throws DuplicatedException
	 * @throws SQLException
	 */
	void insertCustomer(Customer customer) throws AddException, DuplicatedException, SQLException;

	/**
	 * 회원정보수정 - 비밀번호 변경
	 * 
	 * @param userId
	 * @param Oldpw
	 * @param newPw
	 * @throws ModifyException
	 * @throws SQLException
	 */
	void updateCustomerPw(String userId, String Oldpw, String newPw) throws ModifyException, SQLException;

	/**
	 * 회원정보 수정 - 전화번호 변경
	 * 
	 * @param userId
	 * @param userPw
	 * @param newPhoneNo
	 * @throws ModifyException
	 * @throws SQLException
	 */
	void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) throws ModifyException, SQLException;

	/**
	 * 회원 탈퇴
	 * 
	 * @param userId
	 * @param userPw
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	void deleteCustomer(String userId, String userPw) throws NotFoundException, SQLException;

	/**
	 * 회원 정보 조회 - 마이페이지
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	Customer selectCustomer(String userId, String userPw) throws NotFoundException, SQLException;

	/**
	 * 로그인하기
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	Customer login(String userId, String userPw) throws NotFoundException, SQLException;

	/**
	 * ID 찾기
	 * 
	 * @param userName
	 * @param userPhoneNo
	 * @return
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	String selectID(String userName, String userPhoneNo) throws NotFoundException, SQLException;

	/**
	 *  비밀번호 찾기
	 * @param userId
	 * @param userPhoneNo
	 * @return
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	String selectPw(String userId, String userPhoneNo) throws NotFoundException, SQLException;

}
