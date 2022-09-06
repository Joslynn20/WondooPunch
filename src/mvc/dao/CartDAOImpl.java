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
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CartDAOImpl implements CartDAO {

	/**
	 * 장바구니 추가
	 */

	public int addCart(Cart cart) throws SQLException, AddException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART VALUES (CART_NO_SEQ.NEXTVAL,?, ?, ?, ?)";
		int result = 0;
		Savepoint savepoint1 = null;
		try {
			// 옵션이 있을때 해당 테이블 등록
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			savepoint1 = con.setSavepoint("Savepoint1");

			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartQty());
			ps.setInt(2, cart.getCartPrice());
			ps.setString(3, cart.getProductCode());
			ps.setString(4, cart.getUserId());
			result = ps.executeUpdate();

			if (result < 1) { // 1보다 작을 때

				throw new AddException("장바구니에 등록할 수 없습니다.");

			}

			if (cart.getCreamQty() > 0 || cart.getShotQty() > 0 || cart.getSyrupQty() > 0) {
				sql = "INSERT INTO CART_COFFEE_OPTION VALUES (CCO_NO_SEQ.NEXTVAL, CART_NO_SEQ.CURRVAL, ?, ?, ?)";
				System.out.println(sql);
				ps = con.prepareStatement(sql);
				ps.setInt(1, cart.getShotQty());
				ps.setInt(2, cart.getCreamQty());
				ps.setInt(3, cart.getSyrupQty());
				result += ps.executeUpdate();
			}

			if (cart.getCheeseQty() > 0 || cart.getHotQty() > 0) {
				sql = "INSERT INTO CART_DESSERT_OPTION VALUES (CDO_NO_SEQ.NEXTVAL, CART_NO_SEQ.CURRVAL, ?, ?)";
				System.out.println(sql);
				ps = con.prepareStatement(sql);
				ps.setInt(1, cart.getHotQty());
				ps.setInt(2, cart.getCheeseQty());
				result += ps.executeUpdate();
			}
			System.out.println("11");

			con.commit();
		} finally {
			con.rollback(savepoint1);
			DbUtil.dbClose(con, ps);
		}

		return result;
	}

	/**
	 * 장바구니 조회
	 */

	// 장바구니 조회 기능
	@Override
	public List<Cart> getCart(String userId) throws SQLException, NotFoundException {
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
				Cart cart = new Cart(userId, rs.getInt("cartNo"), rs.getString("productCode"),
						rs.getString("productName"), rs.getInt("productPrice"), rs.getInt("cartQty"),
						rs.getInt("shotQty"), rs.getInt("creamQty"), rs.getInt("syrupQty"), rs.getInt("hotQty"),
						rs.getInt("cheeseQty"));

				// 매핑된 데이터 리스트 추가
				list.add(cart);

			}
		} catch (Exception e) {
			throw new NotFoundException("장바구니를 조회할 수 없습니다. ");

		} finally {
			DbUtil.dbClose(con, ps, rs);

		}

		return list;
	}

	/**
	 * 장바구니 수정 (상품 수량 수정 및 수정된 가격 반영 )
	 */
	@Override
	public int updateCart(Cart cart) throws SQLException {

		// 프로덕트가격 가져오기->카트가격을 구하기 위함
		// 카트업데이트

		Integer cartNo = cart.getCartNo();
		Integer cartCnt = cart.getCartQty();

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM PRODUCT WHERE P_CODE = (select p_code from cart where cart_no = ?)"; // 카트번호로 상품 가격을
																											// 가져온다.
		ResultSet rs = null;
		Savepoint savepoint1 = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			savepoint1 = con.setSavepoint("Savepoint1");

			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartNo());
			rs = ps.executeQuery();

			Integer productPrice = 0;

			while (rs.next()) {
				productPrice = rs.getInt("P_PRICE");
			}

			sql = "UPDATE CART SET CART_QTY = ?, CART_PRICE = ?  WHERE CART_NO = ?"; // 카트번호 , 수량, 가격
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartQty());
			ps.setInt(2, productPrice * cart.getCartQty());
			ps.setInt(3, cart.getCartNo());
			result = ps.executeUpdate();

			con.commit();

		} catch (Exception e) {
			System.out.println(e);
			con.rollback(savepoint1);
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return result;
	}

	/**
	 * 장바구니 개별 삭제 (카트 번호로 삭제)
	 */

	@Override
	public int removeCart(int cartNo) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE CART WHERE CART_NO = ?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			int rstCnt = ps.executeUpdate();

			if (rstCnt < 0) {
				throw new NotFoundException("해당 상품을 장바구니에서 삭제할 수 없습니다.");
			}
			if (rstCnt > 0) {

				result = rstCnt;

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
	public boolean removeAllCart(String userId) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		Boolean result = false;

		try {
			con = DbUtil.getConnection();
			sql = "delete " + "from cart_coffee_option " + "where cart_no in  " + "(" + "select cart_no " + "from cart "
					+ "where m_id = ?" + ")";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			int rstCnt = ps.executeUpdate();

			sql = "delete " + "from cart_dessert_option " + "where cart_no in  " + "(" + "select cart_no "
					+ "from cart " + "where m_id = ?" + ")";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rstCnt += ps.executeUpdate();

			sql = "DELETE CART WHERE M_ID = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rstCnt += ps.executeUpdate();

			if (rstCnt < 0)
				throw new NotFoundException("장바구니를 비울 수 없습니다.");

			if (rstCnt > 0) {
				result = true;
			}
		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}

}
