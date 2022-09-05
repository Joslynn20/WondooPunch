package mvc.controller;

import java.util.List;

import mvc.dto.Coupon;
import mvc.service.CouponService;
import mvc.service.CouponServiceImpl;

public class CouponController {
	public static CouponService couponservice = new CouponServiceImpl();

	/**
	 * 쿠폰목록
	 */
	public static void couponSelect() {
		try {
			List<Coupon> list = couponservice.couponSelect();

			System.out.println(list);

		} catch (Exception e) {
			System.out.println("실패");

		} // catch end
	} // optionSelect end

	/**
	 * 쿠폰등록
	 */
	public static void couponInsert(Coupon coupon) {
		try {
			couponservice.couponInsert(coupon);
			System.out.println("등록성공");

		} catch (Exception e) {

			System.out.println("등록실패");

		}
	} // couponInsert end

	/**
	 * 쿠폰 삭제
	 */
	public static void couponDelete(String couponCode) {
		try {
			couponservice.couponDelete(couponCode);
			System.out.println("삭제 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제실패");

		}
	} // couponDelete end
	
	/**
	 * 쿠폰 발행
	 * */
	public static void issuedCoupon() {
		
	}

}
