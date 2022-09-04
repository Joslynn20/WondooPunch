package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Coupon;

public class CouponDAOImpl implements CouponDAO {

	/**
	 * 쿠폰목록
	 * */
	
	@Override
	public List<Coupon> couponSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Coupon> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM COUPON ORDER BY CP_CODE");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Coupon coupon = new Coupon(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5) );
					list.add(coupon);
			}// while end
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return list;
	}// couponSelect end

	/**
	 * 쿠폰등록
	 * */
	@Override
	public int couponInsert(Coupon coupon) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO COUPON VALUES(?,?,?,?,?)";// 쿠폰추가
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, coupon.getCouponCode()); // 쿠폰코드
			ps.setString(2, coupon.getCouponName()); // 쿠폰이름
			ps.setInt(3, coupon.getCouponDC()); // 쿠폰할인율
			ps.setString(4, coupon.getCouponExpDate());//쿠폰유효기간
			ps.setString(5, coupon.getCouponRegDate());//쿠폰등록일
			
			result = ps.executeUpdate();
			if(result == 0) System.out.println(result + "등록되지않았습니다");
			else System.out.println(result + "등록되었습니다");		
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}// couponInsert end	

	/**
	 * 쿠폰 삭제
	 * */
/*	
	@Override
	public int couponDelete(String couponCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "DELETE FROM COUPON WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, couponCode); //옵션코드
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	} // couponDelete end	
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
			
			ps.setString(1, couponCode); //옵션코드
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	
	
	
	
}
