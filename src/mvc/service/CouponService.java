package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CouponDAO;
import mvc.dao.CouponDAOImpl;
import mvc.dto.Coupon;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CouponService {
	CouponDAO coupondao = new CouponDAOImpl();

	/**
	 * 쿠폰목록
	 * 
	 * @throws NotFoundException
	 */
	public List<Coupon> couponSelect() throws SQLException, NotFoundException {
		List<Coupon> list = coupondao.couponSelect();
		if (list.size() == 0)
			throw new NotFoundException("쿠폰목록 보기 실패했습니다");
		return list;
	} // couponSelect end

	/**
	 * 쿠폰등록
	 * 
	 * @throws AddException
	 */
	public void couponInsert(Coupon coupon) throws SQLException, AddException {
		if (coupondao.couponInsert(coupon) == 0) {
			throw new AddException("쿠폰등록 실패했습니다");
		}
	}// couponCode end

	/**
	 * 쿠폰 삭제
	 */
	public void couponDelete(String couponCode) throws SQLException {
		int result = coupondao.couponDelete(couponCode);
		if (result == 0)
			throw new SQLException("삭제되지 않았습니다");
	}

} // CouponService end
