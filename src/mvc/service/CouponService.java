package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CouponService {

	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @return List<Coupon>
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Coupon> selectAllCoupon() throws SQLException, NotFoundException;

	/**
	 * 고객 - 전체 발행쿠폰 목록 조회
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Coupon> selectCouponByUserId(String userId) throws SQLException, NotFoundException;

	/**
	 *  쿠폰 코드에 대한 쿠폰 정보 검색
	 * @param couponCode
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	Coupon selectCouponByCouponCode(String couponCode) throws SQLException, NotFoundException;

	/**
	 *  쿠폰등록
	 * @param coupon
	 * @throws SQLException
	 * @throws AddException
	 */
	void insertCoupon(Coupon coupon) throws SQLException, AddException;

	/**
	 * 쿠폰 삭제
	 * 
	 * @param couponCode
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void deletecoupon(String couponCode) throws SQLException, NotFoundException;

	/**
	 * 가입쿠폰 발행
	 * 
	 * @param userId
	 * @throws SQLException
	 * @throws AddException
	 */
	void insertJoinCoupon(String userId) throws SQLException, AddException;

} // CouponService end
