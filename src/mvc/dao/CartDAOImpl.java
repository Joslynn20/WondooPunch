package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.dbutil.DbUtil;
import mvc.dto.Cart;
import mvc.dto.Product;

public class CartDAOImpl implements CartDAO {

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
		String sql = "INSERT INTO CART VALUES (?,?, ?, ?, ?)";
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
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {

				Cart cart = new Cart(rs.getInt("cartNo"), rs.getString("productCode"), rs.getString("productName"),
						rs.getInt("productPrice"), rs.getInt("cartQty"), rs.getInt("shotQty"), rs.getInt("creamQty"),
						rs.getInt("syrupQty"), rs.getInt("hotQty"), rs.getInt("cheeseQty"));

				list.add(cart);

			}

		} finally {
			DbUtil.dbClose(con, ps, rs);

		}

		return list;
	}

}
