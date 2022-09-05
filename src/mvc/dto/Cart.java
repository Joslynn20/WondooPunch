package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private String userId; // 회원 아이디
	private int cartNo; // 장바구니 번호
	private String productCode; // 상품 코드
	private int cartQty; // 장바구니 수량
	private int cartPrice; // 장바구니에 담긴 상품 가격
	private int productPrice; // 상품 가격
	private String productName; // 상품 이름

	private int shotQty; // 샷추가
	
	private int creamQty; // 휘핑크림추가
	private int syrupQty; // 시럽추가

	private int hotQty; // 데우기
	private int cheeseQty; // 크림치즈추가

	private CoffeeOption coffeeOption = new CoffeeOption();
	private DesertOption desertOption = new DesertOption();

	// 장바구니번호,상품코드,상품명,가격,개수,샷,크림,시럽,핫,치즈

	public Cart(int cartNo, String productCode, String productName, int productPrice, int cartQty, int shotQty,
			int creamQty, int syrupQty, int hotQty, int cheeseQty) {
		super();
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.cartQty = cartQty;

		this.shotQty = shotQty;
		this.creamQty = creamQty;
		this.syrupQty = syrupQty;

		this.hotQty = hotQty;
		this.cheeseQty = cheeseQty;
	}

	public Cart(String userId, int cartNo, String productCode, int cartQty, int cartPrice) {
		super();
		this.userId = userId;
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;

	}

	public Cart(String userId, int cartNo, String productCode, int cartQty, int cartPrice, int productPrice,
			String productName, CoffeeOption cfo, DesertOption deo) {
		super();
		this.userId = userId;
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productPrice = productPrice;
		this.productName = productName;
		this.coffeeOption = coffeeOption;
		this.desertOption = desertOption;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Cart(String userId, int cartNo, String productCode, int cartQty, int cartPrice, int productPrice,
			String productName) {
		super();
		this.userId = userId;
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.productPrice = productPrice;
		this.productName = productName;
	}

	public Cart() {
		Cart cart = new Cart();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public int getShotQty() {
		return shotQty;
	}

	public void setShotQty(int shotQty) {
		this.shotQty = shotQty;
	}

	public int getCreamQty() {
		return creamQty;
	}

	public void setCreamQty(int creamQty) {
		this.creamQty = creamQty;
	}

	public int getSyrupQty() {
		return syrupQty;
	}

	public void setSyrupQty(int syrupQty) {
		this.syrupQty = syrupQty;
	}

	public int getHotQty() {
		return hotQty;
	}

	public void setHotQty(int hotQty) {
		this.hotQty = hotQty;
	}

	public int getCheeseQty() {
		return cheeseQty;
	}

	public void setCheeseQty(int cheeseQty) {
		this.cheeseQty = cheeseQty;
	}

	public CoffeeOption getCoffeeOption() {
		return coffeeOption;
	}

	public void setCoffeeOption(CoffeeOption coffeeOption) {
		this.coffeeOption = coffeeOption;
	}

	public DesertOption getDesertOption() {
		return desertOption;
	}

	public void setDesertOption(DesertOption desertOption) {
		this.desertOption = desertOption;
	}


	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", cartNo=" + cartNo + ", productCode=" + productCode + ", cartQty=" + cartQty
				+ ", cartPrice=" + cartPrice + ", productPrice=" + productPrice + ", productName=" + productName
				+ ", coffeeOption=" + coffeeOption + ", desertOption=" + desertOption + "]";
	}

}
