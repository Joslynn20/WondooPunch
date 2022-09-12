package mvc.dbutil;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

	/*
	 * 로드:연결하려는 dbms를 선택한다 db라이브러리 필요하다 딱한번만 합면돤더
	 * 
	 * 
	 **/

	private static Properties profile = new Properties();

	static {

		try {

			profile.load(new FileInputStream("resources/dbInfo.properties"));

			Class.forName(profile.getProperty("driverName"));

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static Properties getProfile() {

		return profile;

	}

	/*
	 * 연결
	 * 
	 */

	public static Connection getConnection() throws SQLException {

		Connection con = DriverManager.getConnection(profile.getProperty("url"), profile.getProperty("userName"),
				profile.getProperty("userPass"));

		return con;

	}

	/*
	 * 닫기---사용한 객체 닫기-SELECT인경우
	 * 
	 */
	public static void dbClose(Connection con, Statement st, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
			dbClose(con, st);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 닫기 - 사용한객체 닫기 DML OR DDL인경우
	 * 
	 */

	public static void dbClose(Connection con, Statement st) {

		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			System.out.println("***메인 시작*****");

			con = DbUtil.getConnection();

			System.out.println("\ncon:" + con);

			ps = con.prepareStatement("select * from TEST");
			rs = ps.executeQuery();

			System.out.println("3조 조원 목록");

			while (rs.next()) {

				System.out.println(rs.getInt("기수") + "기 |" + rs.getString("이름"));

				/*
				 * 성공시 나오는 값 248|김보경 248|허재봉 248|채란 248|임홍제 248|정두영 248|안효경
				 */

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			dbClose(con, null, null);
		}

	}
}
