package mvc.dto;

public class Cart {

	private String userId; // 회원 아이디 
	private int cartNo; // 장바구니 번호 
	private String productCode; // 상품 코드  
	private int cartQty; //장바구니 수량  
	private int cartPrice;

	public Cart(String userId, int cartNo, String productCode, int cartQty, int cartPrice) {
		super();
		this.userId = userId;
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CartDTO [userId=");
		builder.append(userId);
		builder.append(", cartNo=");
		builder.append(cartNo);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", cartQty=");
		builder.append(cartQty);
		builder.append(", cartPrice=");
		builder.append(cartPrice);
		builder.append("]");
		return builder.toString();
	}
	

}
