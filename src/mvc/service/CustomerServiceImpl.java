package mvc.service;

import java.sql.SQLException;

import mvc.dao.CustomerDAO;
import mvc.dao.CustomerDAOImpl;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.DuplicatedException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO customerDao = new CustomerDAOImpl();

	/**
	 * 회원 가입
	 */
	@Override
	public void insertCustomer(Customer customer) throws AddException, DuplicatedException, SQLException {
		int result = customerDao.insertCustomer(customer);
		if (result == 0)
			throw new AddException("입력하신 회원 정보는 등록하실 수 없습니다.");
	}

	/**
	 * 회원정보수정 - 비밀번호 변경
	 */
	@Override
	public void updateCustomerPw(String userId, String Oldpw, String newPw) throws ModifyException, SQLException {
		int result = customerDao.updateCustomerPw(userId, Oldpw, newPw);
		if (result == 0)
			throw new ModifyException("비밀번호 입력 오류로 비밀번호 변경에 실패했습니다.");

	}

	/**
	 * 회원정보 수정 - 전화번호 변경
	 */
	@Override
	public void updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo)
			throws ModifyException, SQLException {
		int result = customerDao.updateCustomerPhoneNo(userId, userPw, newPhoneNo);
		if (result == 0)
			throw new ModifyException("비밀번호 입력 오류로 전화번호 변경에 실패했습니다.");
	}

	/**
	 * 회원 탈퇴
	 */
	@Override
	public void deleteCustomer(String userId, String userPw) throws NotFoundException, SQLException {
		int result = customerDao.deleteCustomer(userId, userPw);
		if (result == 0)
			throw new NotFoundException("비밀번호 입력 오류로 회원탈퇴에 실패했습니다.");

	}

	/**
	 * 마이페이지 회원정보 조회
	 */
	@Override
	public Customer selectCustomer(String userId, String userPw) throws NotFoundException, SQLException {
		Customer customer = customerDao.selectCustomer(userId, userPw);
		if (customer == null)
			throw new NotFoundException("비밀번호 입력 오류로 회원정보 조회에 실패했습니다.");
		return customer;
	}

	/**
	 * 로그인 하기
	 */
	@Override
	public Customer login(String userId, String userPw) throws NotFoundException, SQLException {
		Customer customer = customerDao.login(userId, userPw);
		if (customer == null)
			throw new NotFoundException("입력하신 사용자 이름 또는 비밀번호가 올바르지 않습니다.");
		return customer;
	}

	/**
	 * ID 찾기
	 */
	@Override
	public String selectID(String userName, String userPhoneNo) throws NotFoundException, SQLException {
		String userId = customerDao.selectID(userName, userPhoneNo);
		if (userId == null) {
			throw new NotFoundException("입력하신 회원 정보에 해당하는 회원 ID가 존재하지 않습니다.");
		}
		return userId;
	}

	/**
	 * 비밀번호 찾기
	 */
	@Override
	public String selectPw(String userId, String userPhoneNo) throws NotFoundException, SQLException {
		String userPw = customerDao.selectPw(userId, userPhoneNo);
		if (userPw == null) {
			throw new NotFoundException("입력하신 회원 정보가 존재하지 않습니다.");
		}
		return userPw;
	}

}
