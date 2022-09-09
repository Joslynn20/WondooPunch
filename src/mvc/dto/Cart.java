package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private int cartNo; // 장바구니 번호
	private int cartQty; // 장바구니 수량
	private int cartPrice; // 장바구니에 담긴 상품 가격
	private String productCode; // 상품 코드
	private String userId; // 회원ID
	private String productName;
	private int productPrice;

	List<DetailOption> list = new ArrayList<DetailOption>(); // 장바구니 상세 옵션
	DetailOption detailOption = new DetailOption();

	public Cart() {

	}

	// 장바구니 담기
	public Cart(int cartNo, int cartQty, int cartPrice, String productCode, String userId, List<DetailOption> list) {
		this.cartNo = cartNo;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productCode = productCode;
		this.userId = userId;
		this.list = list;

	}

	// 장바구니 조회
		public Cart(int cartNo, String productName, int cartQty, int productPrice, int cartPrice) {
		this.cartNo = cartNo;
		this.productName = productName;
		this.cartQty = cartQty;
		this.productPrice = productPrice;
		this.cartPrice = cartPrice;
	}
			
		
	

	// getter, setter
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public DetailOption getDetailOption() {
		return detailOption;
	}

	public void setDetailOption(DetailOption detailOption) {
		this.detailOption = detailOption;
	}
}
