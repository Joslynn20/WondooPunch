package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CouponDAO {
	/**
	 * 쿠폰목록
	 */
	List<Coupon> couponSelect() throws SQLException, NotFoundException;
	
	/**
	 * 쿠폰번호에 대한 정보검색
	 */
	Coupon couponSelectByCouponCode(String couponCode) throws SQLException;

	/**
	 * 쿠폰등록
	 */
	int couponInsert(Coupon coupon) throws SQLException, AddException;

	/**
	 * 쿠폰 삭제
	 */
	int couponDelete(String couponCode) throws SQLException;

	/**
	 * 가입쿠폰발행
	 * 
	 * @throws SQLException
	 */
	int joinCoupon(Customer customer) throws SQLException, AddException;

}
