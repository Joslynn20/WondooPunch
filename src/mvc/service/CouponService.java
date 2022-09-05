package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CouponService {
	

	/**
	 * 쿠폰목록
	 * 
	 * @throws NotFoundException
	 */
	List<Coupon> couponSelect() throws SQLException, NotFoundException;
	
	/**
	 * 쿠폰번호에 해당하는 옵션검색
	 * */
	Coupon couponSelectByCouponCode(String couponCode) throws NotFoundException, SQLException;
	
	
	/**
	 * 쿠폰등록
	 * 
	 * @throws AddException
	 */
	void couponInsert(Coupon coupon) throws SQLException, AddException ;
	/**
	 * 쿠폰 삭제
	 * @throws NotFoundException 
	 */
	void couponDelete(String couponCode) throws SQLException, NotFoundException;
		
	/**
	 * 가입쿠폰발행
	 * */
	void joinCoupon(Customer customer) throws SQLException, AddException;

	
	
} // CouponService end
