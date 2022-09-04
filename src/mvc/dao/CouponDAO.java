package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Coupon;

public interface CouponDAO {
	/**
	 * 쿠폰목록
	 * */
	List<Coupon> couponSelect() throws SQLException;
	/**
	 * 쿠폰등록
	 * */
	int couponInsert(Coupon coupon) throws SQLException;
	/**
	 * 쿠폰 삭제
	 * */
	int couponDelete(String couponCode) throws SQLException;
}
