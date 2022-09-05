package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Coupon;
import mvc.exception.NotFoundException;

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
				Coupon coupon = new Coupon(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
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
		String sql = "INSERT INTO COUPON VALUES(?,?,?,ADD_MONTHS(SYSDATE,+1),SYSDATE)";// 쿠폰추가
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, coupon.getCouponCode()); // 쿠폰코드
			ps.setString(2, coupon.getCouponName()); // 쿠폰이름
			ps.setInt(3, coupon.getCouponDC()); // 쿠폰할인율
			
			
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
		
	/**
	 * 가입쿠폰발행
	 * @throws SQLException 
	 * */
	public int joinCoupon(Coupon coupon) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO issuedCoupon VALUES(?,?,?,M_REGDATE ,add_date(M_REGDATE ,+1), 15 )";// 추후 쿼리 수정 // 발행일,유효기간,할인율
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, coupon.getIcCode()); // 발행쿠폰코드
			ps.setString(2, coupon.getMemberId()); // 회원ID
			ps.setString(3, coupon.getCouponCode()); // 쿠폰코드			
			
			result = ps.executeUpdate();
			if(result == 0) System.out.println(result + "등록되지않았습니다");
			else System.out.println(result + "등록되었습니다");		
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	/**
	 * 생일쿠폰발행
	 * */
	public int BirthCoupon(Coupon coupon) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO issuedCoupon VALUES(?,?,?,M_BIRTH ,add_date(M_BIRTH ,+1), 15 )";// 추후 쿼리 수정 // 발행일,유효기간,할인율
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, coupon.getIcCode()); // 발행쿠폰코드
			ps.setString(2, coupon.getMemberId()); // 회원ID
			ps.setString(3, coupon.getCouponCode()); // 쿠폰코드			
			
			result = ps.executeUpdate();
			if(result == 0) System.out.println(result + "등록되지않았습니다");
			else System.out.println(result + "등록되었습니다");		
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	/**
	 *  쿠폰 적용한 결제내역 보여주기
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * */
	public int getTotalOptionPrice() throws SQLException, NotFoundException {
		this.couponDC(null);		
		
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();		
		int totalPrice = orderDAOImpl.getTotalPrice(null);
		
		int totalOptionPrice = totalPrice() * (couponDC()/100);
		return totalOptionPrice;
			
	
		}

	/**
	 * 쿠폰코드 검색후 할인율 갖고오기
	 * */
	public Coupon couponDC(String couponCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Coupon couponDC = null;
		String sql = "SELECT * FROM COUPON WHERE CP_CODE = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, couponCode);
			
			rs = ps.executeQuery();
			if (rs.next()) {
			CouponcouponDC = new Coupon(rs.getInt(1));
			}
	}finally {
		DbUtil.dbClose(con, ps, rs);
	}
		return couponDC;
	}
} // class
