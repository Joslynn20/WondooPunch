package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.exception.AddException;

public class CouponDAOImpl implements CouponDAO {

	/**
	 * 쿠폰목록
	 */

	@Override
	public List<Coupon> couponSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Coupon> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM COUPON");
			rs = ps.executeQuery();

			while (rs.next()) {
				Coupon coupon = new Coupon(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				list.add(coupon);

			} // while end

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}// couponSelect end
	
	/**
	 * 쿠폰번호에 대한 정보검색
	 */
	@Override
	public Coupon couponSelectByCouponCode(String couponCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Coupon coupon = null;
		String sql = "SELECT * FROM COUPON WHERE CP_CODE = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, couponCode);
		
			rs = ps.executeQuery();
			if (rs.next()) {
			coupon = new Coupon(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
		} // if end
		} finally {
			DbUtil.dbClose(con, ps, rs);
		} // finally end
		return coupon;
	}


	/**
	 * 쿠폰등록
	 */
	@Override
	public int couponInsert(Coupon coupon) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO COUPON VALUES(?,?,?,SYSDATE)";// 쿠폰추가

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, coupon.getCouponCode()); // 쿠폰코드
			ps.setString(2, coupon.getCouponName()); // 쿠폰이름
			ps.setInt(3, coupon.getCouponDC()); // 쿠폰할인율

			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}// couponInsert end

	/**
	 * 쿠폰 삭제
	 */

	@Override
	public int couponDelete(String couponCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "DELETE FROM COUPON WHERE CP_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, couponCode); // 옵션코드
			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	} // couponDelete end

	/**
	 * 가입쿠폰발행
	 * 
	 * @throws SQLException
	 */
	@Override
	public int joinCoupon(Customer customer) throws SQLException, AddException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		int count = 1;
		String sql = "INSERT INTO issuedcoupon (? , ? ,?, sysdate , ADD_month(sysdate, +1), 15)";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "IC0" + count++);// 발행쿠폰코드
			ps.setString(2, customer.getUserId());
			ps.setString(3, "CP01");// 쿠폰코드

			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	/**
	 * 쿠폰코드 검색후 할인율 갖고오기
	 */
	public int couponDC(String couponCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int couponDC = 0;
		String sql = "SELECT CP_DC FROM issuedcoupon WHERE IC_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, couponCode);

			rs = ps.executeQuery();
			if (rs.next()) {

				couponDC = rs.getInt(1);

			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return couponDC;
	}

} // class
