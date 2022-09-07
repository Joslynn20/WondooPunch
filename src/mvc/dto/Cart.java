package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private int cartNo; // 장바구니 번호
	private int cartQty; // 장바구니 수량
	private int cartPrice; // 장바구니에 담긴 상품 가격
	private String productCode; // 상품 코드
	private String userId; // 회원ID
	
	List<DetailOption> list = new ArrayList<DetailOption>(); // 장바구니 상세 옵션
	
	public Cart() {
		
	}

	public Cart(int cartNo, int cartQty, int cartPrice, String productCode, String userId) {
		super();
		this.cartNo = cartNo;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productCode = productCode;
		this.userId = userId;
	}

	//getter, setter
	int getCartNo() {
		return cartNo;
	}

	void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	int getCartQty() {
		return cartQty;
	}

	void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}

	int getCartPrice() {
		return cartPrice;
	}

	void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	String getProductCode() {
		return productCode;
	}

	void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	String getUserId() {
		return userId;
	}

	void setUserId(String userId) {
		this.userId = userId;
	}

	List<DetailOption> getList() {
		return list;
	}

	void setList(List<DetailOption> list) {
		this.list = list;
	}
	
	
}
