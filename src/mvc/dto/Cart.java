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
	DetailOption detailOption;

	public Cart() {

	}

	public Cart(int cartNo, int cartQty, int cartPrice, String productCode, String userId, List<DetailOption> list) {
		super();
		this.cartNo = cartNo;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productCode = productCode;
		this.userId = userId;
		this.list = list;
	}

	public Cart(int cartNo, int cartQty, int cartPrice, String productCode, String userId) {
		this.cartNo = cartNo;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productCode = productCode;
		this.userId = userId;
	}

	public Cart(int cartNo, int cartQty, int cartPrice, String productCode, String userId, List<DetailOption> list,
			DetailOption detailOption) {
		this(cartNo, cartQty, cartPrice, productCode, userId, list);
		this.detailOption = detailOption;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getCartQty() {
		return cartQty;
	}

	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<DetailOption> getList() {
		return list;
	}

	public void setList(List<DetailOption> list) {
		this.list = list;
	}

	public DetailOption getDetailOption() {
		return detailOption;
	}

	public void setDetailOption(DetailOption detailOption) {
		this.detailOption = detailOption;
	}

}
