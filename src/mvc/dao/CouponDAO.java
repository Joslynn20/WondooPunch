package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.IssuedCoupon;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CouponDAO {
	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @return List<Coupon>
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Coupon> selectAllCoupon() throws SQLException;

	/**
	 * 고객 - 전체 발행쿠폰 목록 조회
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<IssuedCoupon> selectCouponByUserId(String userId) throws SQLException;

	/**
	 * 관리자메뉴 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 * 
	 * @param couponCode
	 * @return
	 * @throws SQLException
	 */
	List<Coupon> selectCouponByCouponCode(String couponCode) throws SQLException;

	/**
	 * 고객 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 */
	List<IssuedCoupon> selectCouponByCouponCodeByGuest(String userId) throws SQLException;

	/**
	 * 쿠폰등록
	 * 
	 * @param coupon
	 * @return
	 * @throws SQLException
	 * @throws AddException
	 */
	int insertCoupon(Coupon coupon) throws SQLException;

	/**
	 * 쿠폰 삭제
	 * 
	 * @param couponCode
	 * @return
	 * @throws SQLException
	 */
	int deleteCoupon(String couponCode) throws SQLException;

	/**
	 * 가입쿠폰발행
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws AddException
	 */
	int insertJoinCoupon(String userId) throws SQLException;
	
	/** 쿠폰코드 검색후 할인율 갖고오기
	 * 
	 * @return
	 * @throws SQLException 
	 */	 
	
	int couponDC(String couponCode) throws SQLException ;
}
