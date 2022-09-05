package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.service.CouponService;
import mvc.service.CouponServiceImpl;

public class CouponController {
	public static CouponService couponservice = new CouponServiceImpl();
	public static Customer customer = new Customer();
	public static List<Coupon> list;

	/**
	 * 쿠폰목록
	 * @throws SQLException 
	 */
	public static void couponSelect() throws SQLException, NotFoundException {
		try {
			List<Coupon> list = couponservice.couponSelect();		
			System.out.println(list);
			
		} catch (NotFoundException e) {
			
		System.out.println(e.getMessage());
			

		} // catch end
	} // optionSelect end
	
	/**
	 * 쿠폰번호에 해당하는 쿠폰검색
	 * */
	public static void couponSelectByCouponCode(String couponCode) throws NotFoundException, SQLException {
		try {	
		Coupon coupon = couponservice.couponSelectByCouponCode(couponCode);
		System.out.println(coupon);
		
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}		
	}	
	

	/**
	 * 쿠폰등록
	 * @throws SQLException 
	 */
	public static void couponInsert(Coupon coupon) throws SQLException, AddException {
		try {
			couponservice.couponInsert(coupon);
			
		} catch (AddException e) {

			System.out.println(e.getMessage());

		}
	} // couponInsert end

	/**
	 * 쿠폰 삭제
	 * @throws SQLException 
	 * @throws NotFoundException 
	 */
	public static void couponDelete(String couponCode) throws SQLException, NotFoundException {
		try {
			couponservice.couponDelete(couponCode);
			
		} catch (SQLException e) {	
			System.out.println(e.getMessage());
		}
	} // couponDelete end
	
	/**
	 * 회원가입쿠폰발행
	 * @throws SQLException 
	 * */
	public static void joinCoupon(Customer customer) throws SQLException,AddException {
		try {
		couponservice.joinCoupon(customer);
		}catch(AddException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	
	

}
