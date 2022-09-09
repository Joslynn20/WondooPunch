package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Cart;
import mvc.dto.DetailOption;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface CartService {

	/**
	 *  장바구니 등록
	 * @param cart
	 * @throws SQLException
	 * @throws AddException
	 * @throws NotFoundException
	 */
	 void insertCart(Cart cart, List<DetailOption> list) throws AddException, SQLException, NotFoundException;

	/**
	 * 장바구니 조회
	 * @param userId
	 * @return List<Cart> 장바구니 리스트
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Cart> selectCart(String userId) throws SQLException, NotFoundException;

	/**
	 *  장바구니 수정
	 * @param cartNo
	 * @param cartQty
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void updateCart(Cart cart) throws SQLException, NotFoundException;

	/**
	 *  장바구니 개별 삭제
	 * @param cartNo
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void deleteCartByCartNo(int cartNo) throws SQLException, NotFoundException;

	/**
	 * 장바구니 전체삭제
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void deleteCartByUserId(String userId) throws SQLException, NotFoundException;

}
