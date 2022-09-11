package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.dto.IssuedCoupon;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.service.CouponService;
import mvc.service.CouponServiceImpl;
import mvc.view.EndView;
import mvc.view.FailView;

public class CouponController {
	public static CouponService couponservice = new CouponServiceImpl();
	public static Customer customer = new Customer();
	public static List<Coupon> list;

	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @throws SQLException
	 */
	public static void selectAllCoupon() throws SQLException, NotFoundException {
		try {
			List<Coupon> list = couponservice.selectAllCoupon();
			EndView.printCouponList(list);

		} catch (NotFoundException e) {

			FailView.errorMessage(e.getMessage());

		} // catch end
	} // optionSelect end

	/**
	 * 고객 - 전체 발행쿠폰 목록 조회
	 */
	public static void selectCouponByUserId(String userId) throws SQLException, NotFoundException {
		try {
			List<IssuedCoupon> list = couponservice.selectCouponByUserId(userId);
			EndView.printIssuedCouponList(list);

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 고객메뉴 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 */
	public static void selectCouponByCouponCode(String couponCode) throws NotFoundException, SQLException {
		try {
			Coupon coupon = couponservice.selectCouponByCouponCode(couponCode);
			System.out.println(coupon); // 메뉴단에서 수정 출력

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 관리자 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 */
	public static void selectCouponByCouponCodeByAdmin(String couponCode) throws SQLException, NotFoundException {
		try {
			Coupon coupon = couponservice.selectCouponByCouponCodeByAdmin(couponCode);

			EndView.printCouponListByCode(coupon);

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 쿠폰등록
	 * 
	 * @throws SQLException
	 */
	public static void insertCoupon(Coupon coupon) throws SQLException, AddException {
		try {
			couponservice.insertCoupon(coupon);
			System.out.println("쿠폰등록됐습니다");

		} catch (AddException e) {

			FailView.errorMessage(e.getMessage());
		}
	} // couponInsert end

	/**
	 * 쿠폰 삭제
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public static void deleteCoupon(String couponCode) throws SQLException, NotFoundException {
		try {
			couponservice.deletecoupon(couponCode);
			System.out.println("쿠폰삭제됐습니다");

		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	} // couponDelete end

	/**
	 * 가입쿠폰발행
	 * 
	 * @throws SQLException
	 */
	public static void insertJoinCoupon(String userId) throws SQLException, AddException {
		try {
			couponservice.insertJoinCoupon(userId);
			System.out.println("쿠폰발행됐습니다");
		} catch (AddException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
