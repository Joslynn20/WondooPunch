package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	
	private int orderNo; //pk
	private int orderTotalPrice;
	private int orderTotalQty;
	private String orderDate;
	private String userId; // fk
	private String couponCode; // fk
	
	private List<OrderLine> orderLinelist = new ArrayList<OrderLine>();
	
	public Orders() {
		
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

}
