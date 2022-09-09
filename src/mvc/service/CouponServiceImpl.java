package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CouponDAO;
import mvc.dao.CouponDAOImpl;
import mvc.dto.Coupon;
import mvc.dto.IssuedCoupon;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CouponServiceImpl implements CouponService {
	CouponDAO coupondao = new CouponDAOImpl();

	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @throws NotFoundException
	 */
	public List<Coupon> selectAllCoupon() throws SQLException, NotFoundException {
		List<Coupon> list = coupondao.selectAllCoupon();
		if (list.isEmpty() || list.size() == 0)
			throw new NotFoundException();
		return list;
	} // couponSelect end

	/**
	 * 고객 - 전체 발행쿠폰 목록 조회
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<IssuedCoupon> selectCouponByUserId(String userId) throws NotFoundException, SQLException {
		List<IssuedCoupon> list = coupondao.selectCouponByUserId(userId);
		if (list.isEmpty() || list.size() == 0)
			throw new NotFoundException();
		return list;
	}

	/**
	 * 고객 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 */
	public Coupon selectCouponByCouponCode(String couponCode) throws NotFoundException, SQLException {
		Coupon coupon = coupondao.selectCouponByCouponCode(couponCode);
		if (coupon == null)
			throw new NotFoundException("쿠폰이 없습니다");
		return coupon;
	}

	/**
	 * 관리자메뉴 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 */
	public Coupon selectCouponByCouponCodeByAdmin(String couponCode) throws NotFoundException, SQLException {
		Coupon coupon = coupondao.selectCouponByCouponCodeByAdmin(couponCode);
		if (coupon == null)
			throw new NotFoundException("쿠폰목록이 없습니다");
		return coupon;
	}

	/**
	 * 쿠폰등록
	 * 
	 * @throws AddException
	 */
	public void insertCoupon(Coupon coupon) throws SQLException, AddException {
		int result = coupondao.insertCoupon(coupon);
		if (result == 0)
			throw new AddException();

	}// couponCode end

	/**
	 * 쿠폰 삭제
	 * 
	 * @throws NotFoundException
	 */
	@Override
	public void deletecoupon(String couponCode) throws SQLException, NotFoundException {
		int result = coupondao.deleteCoupon(couponCode);
		if (result == 0)
			throw new NotFoundException();

	}

	/**
	 * 가입쿠폰발행
	 */
	@Override
	public void insertJoinCoupon(String userId) throws SQLException, AddException {
		int result = coupondao.insertJoinCoupon(userId);
		if (result == 0)
			throw new AddException();

	}
}
