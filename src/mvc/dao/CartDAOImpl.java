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
import mvc.dto.DetailOption;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class CartDAOImpl implements CartDAO {

	ProductDAO productDAO = new ProductDAOImpl();
	OptionDAO optionDao = new OptionDAOImpl();

	/**
	 * 장바구니 담기
	 */

	public int insertCart(Cart cart) throws SQLException, AddException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {

			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			// CART INSERT
			String sql = "INSERT INTO CART VALUES (CART_NO_SEQ.NEXTVAL,?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartQty());
			ps.setInt(2, cart.getCartPrice());
			ps.setString(3, cart.getProductCode());
			ps.setString(4, cart.getUserId());
			result = ps.executeUpdate();

			if (result < 1) { // 1보다 작을 때
				throw new AddException("상품을 장바구니에 담을 수 없습니다.");
			}

			// CART OPTION INSERT
			if (cart.getList() != null && cart.getList().size() > 0) {

				for (int i = 0; i < cart.getList().size(); i++) {
					cart.getList().get(i);
					sql = "INSERT INTO CART_OPTION VALUES (COO_NO_SEQ.NEXTVAL, CART_NO_SEQ.CURRVAL, ?, ?, ?)";
					ps = con.prepareStatement(sql);
					ps.setString(1, cart.getList().get(i).getOptionCode());
					ps.setInt(2, cart.getList().get(i).getDetailOtionQty());
					ps.setInt(3, cart.getList().get(i).getDetailOptionPrice());
					result += ps.executeUpdate();
				}

			}

			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new AddException("상품을 장바구니에 담을 수 없습니다.");

		} finally {
			DbUtil.dbClose(con, ps);
		}

		return result;

	}

	/**
	 * 장바구니 조회
	 * 
	 * @throws NotFoundException
	 */

	@Override
	public List<Cart> selectCart(String userId) throws SQLException, NotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select a.cart_no " + " , (select p_name from product where p_code = a.p_code) p_name "
				+ "      , a.cart_qty "
				// + " , (select p_price from product where p_code = a.p_code) * a.cart_qty AS
				// p_price "
				+ "      , (select p_price from product where p_code = a.p_code) p_price "
				+ "      , (select p_price from product where p_code = a.p_code)* a.cart_qty+ b.co_qty * b.co_price as a.cart_price "
				+ "      , b.co_code " + "      , (select o_name from OPTIONS  where o_code = b.co_code) o_name "
				+ "      , b.co_qty " + "      , b.co_price " + "from CART a left outer join CART_OPTION b "
				+ "  on a.cart_no = b.cart_no " + "  where m_id = ?";

		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();

		try {
			con = DbUtil.getConnection(); // 연결정보
			ps = con.prepareStatement(sql); // 쿼리입력
			ps.setString(1, userId); // 쿼리파라미터 입력
			rs = ps.executeQuery();
			while (rs.next()) {

				Cart cart = new Cart(rs.getInt("cart_no"), rs.getInt("cart_qty"), rs.getInt("cart_price"),
						rs.getString("p_code"), rs.getString("m_id"));

				if (rs.getString("co_code") != null) {
					DetailOption detailOption = new DetailOption(rs.getInt("coo_no"), rs.getInt("cart_no"), rs.getString("co_code"),
							rs.getInt("co_qty"), rs.getInt("co_price"));
					
					cart.setDetailOption(detailOption);

					// 매핑된 데이터 리스트 추가
					list.add(cart);
				}
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

		// 프로덕트가격 가져오기->카트가격 변경하기 위함
		// 카트업데이트

		Integer cartNo = cart.getCartNo();
		Integer cartCnt = cart.getCartQty();

		Connection con = null;
		PreparedStatement ps = null;

		// 카트번호로 상품 가격을 가져온다.
		String sql = "SELECT * FROM PRODUCT WHERE P_CODE = (select p_code from cart where cart_no = ?)";
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
	public int deleteCartByCartNo(int cartNo) throws SQLException, NotFoundException {
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
	public int deleteCartByUserId(String userId) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE CART WHERE M_ID = ?";
		int result = 0;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();

			if (result < 0)
				throw new NotFoundException("장바구니 비우기 오류입니다.");

		} finally {

			DbUtil.dbClose(con, ps);
		}

		return result;
	}
}
