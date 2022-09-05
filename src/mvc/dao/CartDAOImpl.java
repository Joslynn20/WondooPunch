package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Cart;

public class CartDAOImpl implements CartDAO {

	/**
	 * insert 카페옵션 디저트 옵션을 받아오기 위해서. insert 인서트를 하고 result
	 */
	@Override
	public Integer generateCartKey() throws SQLException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT CART_NO_SEQ.NEXTVAL AS CART_NO FROM DUAL";
		Integer cartNo = 0;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cartNo = rs.getInt("CART_NO");

			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return cartNo;

	}

	/**
	 * 장바구니 추가
	 */
	@Override
	public Boolean addCart(Integer cartNo, Integer cartQty, Integer cartPrice, String productCode, String userId)
			throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART VALUES (?,?, ?, ?, ?)"; // 추후 수정 
		Boolean result = false;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			ps.setInt(2, cartQty);
			ps.setInt(3, cartPrice);
			ps.setString(4, productCode);
			ps.setString(5, userId);
			int updateCnt = ps.executeUpdate();

			if (updateCnt > 0) {

				result = true;

			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

	@Override
	public Boolean addCartCoffeeOption(Integer cartNo, Integer shot, Integer cream, Integer syrp) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART_COFFEE_OPTION VALUES (CCO_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		Boolean result = false;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			ps.setInt(2, shot);
			ps.setInt(3, cream);
			ps.setInt(4, syrp);
			int updateCnt = ps.executeUpdate();

			if (updateCnt > 0) {

				result = true;

			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

	@Override
	public Boolean addCartDesertOption(Integer cartNo, Integer hot, Integer cheese) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART_DESERT_OPTION VALUES (CCO_NO_SEQ.NEXTVAL, ?, ?)";
		Boolean result = false;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			ps.setInt(2, hot);
			ps.setInt(3, cheese);
			int updateCnt = ps.executeUpdate();

			if (updateCnt > 0) {

				result = true;

			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

	/**
	 * 장바구니 조회
	 * */
	@Override
	public List<Cart> getCart(String userId) throws SQLException {
		// 장바구니번호,상품코드,상품명,가격,개수,샷,시럽,크림,핫,치즈

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select a.CART_NO as cartNo " + "       ,  p.P_CODE as productCode"
				+ "      , p.P_NAME as productName " + "     , p.P_PRICE as productPrice "
				+ "     , a.CART_QTY as cartQty " + "    , b.CCO_SHOT as shotQty " + "   , b.CCO_CREAM as creamQty "
				+ "   , b.CCO_SYRUP as syrupQty " + "   , c.CDO_HOT as hotQty " + "   , c.CDO_CHEESE as cheeseQty "
				+ "  from cart a left outer join cart_coffee_option b " + "  on a.cart_no = b.cart_no "
				+ "  left outer join cart_dessert_option c " + "  on a.cart_no = c.cart_no " + "  join product p "
				+ "  on a.p_code = p.p_code " + "  where m_id = ?";
		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();
		try {
			con = DbUtil.getConnection(); // 연결정보
			ps = con.prepareStatement(sql); // 쿼리입력
			ps.setString(1, userId); // 쿼리파라미터 입력
			rs = ps.executeQuery();

			while (rs.next()) {
				// 카트매핑
				Cart cart = new Cart(rs.getInt("cartNo"), rs.getString("productCode"), rs.getString("productName"),
						rs.getInt("productPrice"), rs.getInt("cartQty"), rs.getInt("shotQty"), rs.getInt("creamQty"),
						rs.getInt("syrupQty"), rs.getInt("hotQty"), rs.getInt("cheeseQty"));
				// 매핑된 데이터 리스트 추가
				list.add(cart);

			}

		} finally {
			DbUtil.dbClose(con, ps, rs);

		}

		return list;
	}
	/**
	 * 장바구니 수정 
	 * */
	@Override
	public Boolean updateCart(Integer cartNo, String productCode, Integer cartQty, Integer shot, Integer syrup,
			Integer cream, Integer hot, Integer cheese) throws SQLException {

		// 프로덕트가격 가져오기->카트가격을 구하기 위함
		// 카트업데이트

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM PRODUCT WHERE P_CODE = ?";
		ResultSet rs = null;
		Savepoint savepoint1 = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			savepoint1 = con.setSavepoint("Savepoint1");

			ps = con.prepareStatement(sql);
			ps.setString(1, productCode);
			rs = ps.executeQuery();

			Integer productPrice = 0;

			while (rs.next()) {
				productPrice = rs.getInt("P_PRICE");
			}

			sql = "UPDATE CART SET CART_QTY = ?, CART_PRICE = ? WHERE CART_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartQty);
			ps.setInt(2, cartQty * productPrice);
			ps.setInt(3, cartNo);
			ps.executeUpdate();

			sql = "UPDATE CART_COFFEE_OPTION SET CCO_SHOT = ?, CCO_CREAM = ?, CCO_SYRUP = ? WHERE CART_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, shot == null ? 0 : shot);
			ps.setInt(2, cream == null ? 0 : cream);
			ps.setInt(3, syrup == null ? 0 : syrup);
			ps.setInt(4, cartNo);
			ps.executeUpdate();

			sql = "UPDATE CART_DESSERT_OPTION SET CDO_HOT = ?, CDO_CHEESE = ? WHERE CART_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, hot == null ? 0 : hot);
			ps.setInt(2, cheese == null ? 0 : cheese);
			ps.setInt(3, cartNo);
			ps.executeUpdate();

			con.commit();

		} catch (Exception e) {
			System.out.println(e);
			con.rollback(savepoint1);
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return true;
	}

	/**
	 * 장바구니 개별 삭제
	 */

	@Override
	public Boolean removeCart(Integer cartNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE CART WHERE CART_NO = ?";
		Boolean result = false;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			int rstCnt = ps.executeUpdate();

			if (rstCnt > 0) {

				result = true;

			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

	/**
	 * 장바구니 전체 삭제
	 **/

	@Override
	public Boolean removeAllCart(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE CART WHERE M_ID = ?";
		Boolean result = false;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			int rstCnt = ps.executeUpdate();

			if (rstCnt > 0) {

				result = true;

			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

}
