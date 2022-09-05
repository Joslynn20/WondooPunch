package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CouponDAO;
import mvc.dao.CouponDAOImpl;
import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CouponServiceImpl implements CouponService {
	CouponDAO coupondao = new CouponDAOImpl();
	
	/**
	 * 쿠폰목록
	 * 
	 * @throws NotFoundException
	 */
	public List<Coupon> couponSelect() throws SQLException, NotFoundException {
		List<Coupon> list = coupondao.couponSelect();
		if(list.isEmpty()||list.size()==0)
			throw new NotFoundException();
		return list;
	} // couponSelect end
	
	/**
	 * 쿠폰번호에 해당하는 옵션검색
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public Coupon couponSelectByCouponCode(String couponCode) throws NotFoundException, SQLException {
		Coupon coupon = coupondao.couponSelectByCouponCode(couponCode);
		if (coupon == null)
			throw new NotFoundException();
		return coupon;

	}

	/**
	 * 쿠폰등록
	 * 
	 * @throws AddException
	 */
	public void couponInsert(Coupon coupon) throws SQLException, AddException {
		int result = coupondao.couponInsert(coupon);
		if(result ==0)
			throw new AddException();		
		
	}// couponCode end

	/**
	 * 쿠폰 삭제
	 * @throws NotFoundException 
	 */
	public void couponDelete(String couponCode) throws SQLException, NotFoundException {
		int result = coupondao.couponDelete(couponCode);
		if(result ==0)
			throw new NotFoundException();	
		
	}
	/**
	 * 회원가입쿠폰
	 * */
	@Override
	public void joinCoupon(Customer customer) throws SQLException, AddException {
		int result = coupondao.joinCoupon(customer);
		if(result ==0)
			throw new AddException();	
		
	}	
	
	
}
