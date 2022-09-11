package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Option;

public class OptionDAOImpl implements OptionDAO {

	/**
	 * 옵션 전체검색
	 */
	@Override
	public List<Option> selectAllOption() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Option> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM OPTIONS");
			rs = ps.executeQuery();

			while (rs.next()) {
				Option option = new Option(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				list.add(option);

			} // while end

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	} // optionSelect end

	/**
	 * optionCode에 대한 정보검색
	 */
	@Override
	public Option optionSelectByOptionCode(String optionCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Option option = null;

		String sql = "SELECT * FROM OPTIONS WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, optionCode);

			rs = ps.executeQuery();
			if (rs.next()) {
				option = new Option(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));

			} // if end
		} finally {
			DbUtil.dbClose(con, ps, rs);
		} // finally end
		return option;
	}

	/**
	 * 상품코드에 대한 옵션정보 검색
	 */
	@Override
	public List<Option> selectOptionByProductCode(String productCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Option> list = new ArrayList<Option>();

		String sql = "SELECT * FROM OPTIONS WHERE P_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productCode);

			rs = ps.executeQuery();

			while (rs.next()) {

				Option option = new Option(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));

				list.add(option);

			} // if end
		} finally {
			DbUtil.dbClose(con, ps, rs);
		} // finally end
		return list;

	}

	/**
	 * 옵션추가
	 */

	@Override
	public int insertOption(Option option) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO OPTIONS(O_CODE, O_NAME, O_PRICE, P_CODE) VALUES(?,?,?,?)"; // 옵션추가

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, option.getOptionCode()); // 옵션코드
			ps.setString(2, option.getOptionName()); // 옵션이름
			ps.setInt(3, option.getOptionPrice()); // 옵션가격
			ps.setString(4, option.getProductCode()); // 상품코드

			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps);
		} // finally end
		return result;
	} // optionInsert end

	/**
	 * 옵션 수정
	 */
	@Override
	public int updateOption(Option option) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "UPDATE OPTIONS SET O_CODE = ?, O_NAME = ? O_PRICE = ? WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, option.getOptionCode());// 옵션코드
			ps.setString(2, option.getOptionName()); // 옵션이름
			ps.setInt(3, option.getOptionPrice()); // 옵션가격
			ps.setString(4, option.getOptionCode());// 옵션코드

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(con, ps);
		} // finally end
		return result;

	} // optionUpdate end

	/**
	 * 옵션삭제
	 */
	@Override
	public int deleteOption(String optionCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "DELETE FROM OPTIONS WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, optionCode); // 옵션코드
			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	} // optionDelete

} // OptionDAOImpl end
