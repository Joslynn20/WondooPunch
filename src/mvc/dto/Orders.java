package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private int orderNo; // 주문정보
	private int orderTotalPrice; // 총 구매금액
	private int orderTotalQty; // 총 구매수량
	private String orderDate; // 주문일
	private String userId; // 회원 ID
	private String couponCode; // 발행쿠폰코드

	private List<OrderLine> orderLinelist = new ArrayList<OrderLine>();

	public Orders() {

	}

	public Orders(String userId) {
		this.userId = userId;
	}

	public Orders(int orderNo, int orderTotalPrice, int orderTotalQty, String orderDate, String userId,
			String couponCode) {
		super();
		this.orderNo = orderNo;
		this.orderTotalPrice = orderTotalPrice;
		this.orderTotalQty = orderTotalQty;
		this.orderDate = orderDate;
		this.userId = userId;
		this.couponCode = couponCode;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public int getOrderTotalQty() {
		return orderTotalQty;
	}

	public void setOrderTotalQty(int orderTotalQty) {
		this.orderTotalQty = orderTotalQty;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public List<OrderLine> getOrderLinelist() {
		return orderLinelist;
	}

	public void setOrderLinelist(List<OrderLine> orderLinelist) {
		this.orderLinelist = orderLinelist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("주문 [주문번호: ");
		builder.append(orderNo);
		builder.append(", 회원ID: ");
		builder.append(userId);
		builder.append(", 총 구매수량: ");
		builder.append(orderTotalQty);
		builder.append(", 총 구매금액: ");
		builder.append(orderTotalPrice);
		builder.append(", 구매일: ");
		builder.append(orderDate);
		builder.append(", 적용쿠폰코드: ");
		builder.append(couponCode);
		builder.append("]");
		return builder.toString();
	}

}
