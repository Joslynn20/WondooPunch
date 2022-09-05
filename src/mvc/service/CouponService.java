package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CouponDAO;
import mvc.dao.CouponDAOImpl;
import mvc.dto.Coupon;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CouponService {
	CouponDAO coupondao = new CouponDAOImpl();

	/**
	 * 쿠폰목록
	 * 
	 * @throws NotFoundException
	 */
	List<Coupon> couponSelect() throws SQLException, NotFoundException;
	
	/**
	 * 쿠폰등록
	 * 
	 * @throws AddException
	 */
	void couponInsert(Coupon coupon) throws SQLException, AddException ;
	/**
	 * 쿠폰 삭제
	 */
	void couponDelete(String couponCode) throws SQLException;

} // CouponService end
