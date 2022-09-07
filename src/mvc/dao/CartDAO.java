package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CartDAO {

	/**
	 *  장바구니 추가 기능
	 * @param cart
	 * @return int 
	 * @throws SQLException
	 * @throws AddException
	 * @throws NotFoundException
	 */
	int insertCart(Cart cart) throws SQLException, AddException, NotFoundException;

	/**
	 *  장바구니 조회
	 * @param userId
	 * @return List<Cart> 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Cart> selectCart(String userId) throws SQLException, NotFoundException;
	
	/**
	 *  장바구니 업데이트
	 * @param cart
	 * @return int
	 * @throws SQLException
	 */
	int updateCart(int cartNo, int cartQty) throws SQLException;

	/**
	 *  카트번호로 장바구니 삭제
	 * @param cartNo
	 * @return int
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	int deleteCartByCartNo(int cartNo) throws SQLException, NotFoundException;

	/**
	 *  유저 아이디로 장바구니 전체 삭제
	 * @param userId
	 * @return int
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	int deleteCartByUserId(String userId) throws SQLException, NotFoundException;

}
