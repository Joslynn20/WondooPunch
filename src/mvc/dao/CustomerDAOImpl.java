package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.dbutil.DbUtil;
import mvc.dto.Customer;
import mvc.exception.DuplicatedException;

public class CustomerDAOImpl implements CustomerDAO {

	/**
	 * ID 중복 체크 
	 * true면 중복, false면 중복X
	 * */
	private boolean IDDuplicateCheck(Connection con, Customer customer) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "select * from member where m_id =?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, customer.getUserId());
			
			rs = ps.executeQuery();
			
			if(rs.next()) result = true;	
			
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		return result;
	}
	
	/**
	 * 회원 가입
	 * @throws DuplicatedException 
	 * */
	@Override
	public int insertCustomer(Customer customer) throws SQLException, DuplicatedException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into member values( ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			// ID 중복 체크
			boolean check = IDDuplicateCheck(con, customer);
			if(check) throw new DuplicatedException("입력하신 ID는 중복된 ID입니다.");
			
			ps = con.prepareStatement(sql);
			ps.setString(1, customer.getUserId());
			ps.setString(2, customer.getUserPw());
			ps.setString(3, customer.getUserName());
			ps.setString(4, customer.getUserBirth());
			ps.setString(5, customer.getUserPhoneNo());
			
			result = ps.executeUpdate();
			
			con.commit();
			
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}

	/**
	 * 회원정보수정 - 비밀번호 변경
	 * */
	@Override
	public int updateCustomerPw(String userId, String Oldpw, String newPw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update member set m_password = ? where m_id = ? and m_password = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, newPw);
			ps.setString(2, userId);
			ps.setString(3, Oldpw);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	/**
	 * 회원정보 수정 - 전화번호 변경
	 * */
	@Override
	public int updateCustomerPhoneNo(String userId, String userPw, String newPhoneNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update member set m_phone = ? where m_id = ? and m_password = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, newPhoneNo);
			ps.setString(2, userId);
			ps.setString(3, userPw);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
		
	}
	
	
	/**
	 * 회원 탈퇴
	 * */
	@Override
	public int deleteCustomer(String userId, String userPw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete member where m_id = ? and m_password = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPw);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
		
	}

	/**
	 * 마이페이지 회원정보 조회
	 * */
	@Override
	public Customer searchCustomer(String userId, String userPw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		String sql = "select * from member where m_id=? and m_password=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPw);
			
			rs = ps.executeQuery();
			if (rs.next()){
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6));

			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		return customer;
	}

	/**
	 * 로그인 하기
	 * */
	@Override
	public Customer login(String userId, String userPw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		String sql = "select * from member where m_id=? and m_password=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPw);
			
			rs = ps.executeQuery();
			
			if (rs.next()){
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6));
			}
			//세션에 추가
					
					
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}		
		
		return customer;
	}

	/**
	 * ID 찾기
	 * */
	@Override
	public String searchID(String userName, String userPhoneNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userId = null;
		String sql = "select m_id from member where m_name = ? and m_phone = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPhoneNo);
			
			rs = ps.executeQuery();
			if(rs.next()) userId = rs.getString(1);
			
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		return userId;
	}

	/**
	 * 비밀번호 찾기
	 * */
	@Override
	public String searchPw(String userId, String userPhoneNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userPw = null;
		String sql = "select m_password from member where m_id = ? and m_phone = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPhoneNo);
			
			rs = ps.executeQuery();
			if(rs.next()) userPw = rs.getString(1);
			
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		return userPw;
	}

	

}
