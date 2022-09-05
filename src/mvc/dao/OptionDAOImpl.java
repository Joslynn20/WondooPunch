package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Option;
import mvc.exception.AddException;


public class OptionDAOImpl implements OptionDAO {

	/**
	 * 옵션 전체검색
	 */
	@Override
	public List<Option> optionSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Option> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM OPTIONS ORDER BY O_CODE");
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
	 * 옵션 등록
	 */

	@Override
	public int optionInsert(Option option) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO OPTIONS(O_CODE, O_NAME, O_PRICE, CT_CODE) VALUES(?,?,?,?)"; // 옵션추가

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, option.getOptionCode()); //옵션코드
			ps.setString(2, option.getOptionName()); // 옵션이름
			ps.setInt(3, option.getOptionPrice()); // 옵션가격
			ps.setString(4, option.getOptionCategory()); // 옵션카테고리

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
	public int optionUpdate(Option option) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "UPDATE OPTIONS SET O_NAME = ?, O_PRICE = ?, CT_CODE = ? WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, option.getOptionName()); // 옵션이름
			ps.setInt(2, option.getOptionPrice()); // 옵션가격
			ps.setString(3, option.getOptionCategory()); // 카테고리
			ps.setString(4, option.getOptionCode());// 옵션코드
			

			result = ps.executeUpdate();
			if (result == 0)
				System.out.println(result + " 수정되지 않았습니다");
			else
				System.out.println(result + " 수정되었습니다");
		} finally {
			DbUtil.dbClose(con, ps);
		} // finally end
		return result;

	} // optionUpdate end

	/**
	 * 옵션삭제
	 */
	@Override
	public int optionDelete(String optionCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "DELETE FROM OPTIONS WHERE O_CODE = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, optionCode); //옵션코드
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	} // optionDelete
	
	/**
	 * 고객 주문에서 커피옵션추가
	 * */
	public int orderCoffeeOption(CoffeeOption coffeeoption) throws SQLException,AddException{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO OPTIONS(?,?,?)"; // 옵션추가

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,coffeeoption.getShotQty());
			ps.setInt(2, coffeeoption.getCreamQty());
			ps.setInt(3, coffeeoption.getSyrupQty());

			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		} // finally end
		return result;
	} // orderCoffeeOption end
	
	/**
	 * 고객 주문에서 디저트옵션추가
	 * */	
	public int orderDesertOption(DesertOption desertoption) throws SQLException,AddException{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO OPTIONS(?,?)"; // 옵션추가

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,desertoption.getHotQty());
			ps.setInt(2, desertoption.getCheeseQty());
			

			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps);
		} // finally end
		return result;
	}// orderDesertOption end
	
	
	
	
} // OptionDAOImpl end
