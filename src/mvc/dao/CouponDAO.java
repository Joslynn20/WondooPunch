package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
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
	List<Coupon> selectCouponByUserId(String userId) throws SQLException;

	/**
	 * 쿠폰 코드에 대한 쿠폰 정보 검색
	 * 
	 * @param couponCode
	 * @return
	 * @throws SQLException
	 */
	Coupon selectCouponByCouponCode(String couponCode) throws SQLException;

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
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws AddException
	 */
	int insertJoinCoupon(String userId) throws SQLException;

}
