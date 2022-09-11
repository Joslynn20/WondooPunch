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
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Product;
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
			ps.setInt(2, getCartPrice(cart));
			ps.setString(3, cart.getProductCode());
			ps.setString(4, cart.getUserId());
			result = ps.executeUpdate();

			if (result < 1) { // 1보다 작을 때
				con.rollback();
				throw new AddException("상품을 장바구니에 담을 수 없습니다.");
			}
			int[] optionResult = insertCartOption(con, cart);
			for (int i1 : optionResult) { // 1이 아니면 장바구니 옵션 등록 실패 == 전체 rollback
				if (i1 != 1) {
					con.rollback();
					throw new AddException("장바구니 옵션 등록 실패입니다.");
				}
			}
		} finally {
			con.commit();
			DbUtil.dbClose(con, ps);
		}

		return result;

	}

	/**
	 * 장바구니 가격 구하기
	 * 
	 * @param productCode
	 * @return 장바구니 가격
	 * @throws SQLException
	 */
	private int getCartPrice(Cart cart) throws SQLException {

		int cartPrice = 0; // 장바구니에 담긴 상품 가격

		String productCode = cart.getProductCode();
		Product product = productDAO.selectProductByProductCode(productCode);
		if (product == null)
			throw new SQLException("잘못된 상품 코드입니다.");
		int productPrice = cart.getCartQty() * product.getProductPrice();

		int optionTotalPrice = 0; // 옵션 총 가격
		for (DetailOption detailOption : cart.getList()) { // 주문 상세 옵션 리스트
			List<Option> optionList = optionDao.selectOptionByProductCode(productCode);
			if (optionList == null || optionList.size() == 0)
				throw new SQLException("잘못된 상품 코드입니다.");

			for (Option option : optionList) {
				if (detailOption.getOptionCode().equals(option.getOptionCode())) { // 주문상세옵션코드와 옵션테이블의 옵션코드 비교

					int optionPrice = option.getOptionPrice() * detailOption.getDetailOtionQty() * cart.getCartQty(); // 옵션가격*옵션
																														// 수량*제품
																														// 수량

					detailOption.setDetailOptionPrice(optionPrice); // 주문상세옵션에 옵션 가격 저장
					optionTotalPrice += optionPrice;
				}
			}
		}

		cartPrice = productPrice + optionTotalPrice; // 주문상세 구매 가격

		return cartPrice;

	}

	/**
	 * 장바구니 상세 옵션 등록
	 */
	private int[] insertCartOption(Connection con, Cart cart) throws SQLException {
		PreparedStatement ps = null;
		String sql = "insert into cart_option values (COO_NO_SEQ.NEXTVAL, CART_NO_SEQ.CURRVAL, ?, ?, ?)";
		int result[] = null;

		try {
			ps = con.prepareStatement(sql);
			for (DetailOption detailOption : cart.getList()) {

				ps.setString(1, detailOption.getOptionCode()); // 옵션 코드
				ps.setInt(2, detailOption.getDetailOtionQty()); // 옵션 수량
				ps.setInt(3, detailOption.getDetailOptionPrice()); // 옵션 금액

				ps.addBatch();
				ps.clearParameters();
			}
			result = ps.executeBatch();

		} finally {
			DbUtil.dbClose(null, ps);
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
		String sql = "select * from cart where m_id = ?";

		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();

		try {
			con = DbUtil.getConnection(); // 연결정보
			ps = con.prepareStatement(sql); // 쿼리입력
			ps.setString(1, userId); // 쿼리파라미터 입력
			rs = ps.executeQuery();

			while (rs.next()) {

				Cart cart = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				// 매핑된 데이터 리스트 추가

				cart.setList(selectCartOption(con, cart)); // 카트에 옵션 정보 넣기
				list.add(cart);
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);

		}

		return list;
	}

	/**
	 * 장바구니 옵션 가져오기
	 * 
	 * @param con
	 * @param orderLine
	 * @return
	 * @throws SQLException
	 */
	private List<DetailOption> selectCartOption(Connection con, Cart cart) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DetailOption> detailOption = new ArrayList<DetailOption>();
		String sql = "select * from cart_option where CART_NO = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartNo());
			rs = ps.executeQuery();

			while (rs.next()) {
				DetailOption option = new DetailOption(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5));
				detailOption.add(option);
			}

		} finally {
			DbUtil.dbClose(null, ps, rs);
		}

		return detailOption;
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
		// 카트번호로 장바구니 가격을 가져온다.
		String sql = "SELECT CART_QTY ,CART_PRICE  FROM CART WHERE CART_PRICE = (select CART_PRICE from cart where cart_no = ?)";
		ResultSet rs = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartNo());
			rs = ps.executeQuery();

			int remaincartPrice = 0;
			int remaincartQty = 0;

			while (rs.next()) {
				remaincartPrice = rs.getInt("CART_PRICE");
				remaincartQty = rs.getInt("CART_QTY");
			}
			int onePrice = (int) (remaincartPrice / remaincartQty);

			sql = "UPDATE CART SET CART_QTY = ?, CART_PRICE = ?  WHERE CART_NO = ?"; // 카트번호 , 수량, 가격
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartQty());
			ps.setInt(2, onePrice * cart.getCartQty());
			ps.setInt(3, cart.getCartNo());
			result = ps.executeUpdate();

			con.commit();

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
			result = ps.executeUpdate();

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
