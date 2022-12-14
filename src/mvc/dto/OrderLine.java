package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderLine {

	private int orderLineNo; // 주문상세번호
	private int orderQty; // 구매수량
	private int orderPrice; // 금액
	private int orderNo; // 주문번호
	private String productCode; // 상품코드

	List<DetailOption> list = new ArrayList<DetailOption>(); // 주문상세옵션 리스트

	public OrderLine() {

	}
	
	public OrderLine(String productCode, int orderQty) {
		this.orderQty = orderQty;
		this.productCode = productCode;
	}

	public OrderLine(int orderLineNo, int orderQty, int orderPrice, int orderNo, String productCode) {
		super();
		this.orderLineNo = orderLineNo;
		this.orderQty = orderQty;
		this.orderPrice = orderPrice;
		this.orderNo = orderNo;
		this.productCode = productCode;
	}

	public OrderLine(int orderLineNo, int orderQty, int orderPrice, int orderNo, String productCode,
			List<DetailOption> list) {
		this(orderLineNo, orderQty, orderPrice, orderNo, productCode);
		this.list = list;
	}

	public int getOrderLineNo() {
		return orderLineNo;
	}

	public void setOrderLineNo(int orderLineNo) {
		this.orderLineNo = orderLineNo;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<DetailOption> getList() {
		return list;
	}

	public void setList(List<DetailOption> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("주문상세 [주문상세번호: ");
		builder.append(orderLineNo);
		builder.append(", 주문번호: ");
		builder.append(orderNo);
		builder.append(", 상품 코드: ");
		builder.append(productCode);
		builder.append(", 수량: ");
		builder.append(orderQty);
		builder.append(", 가격: ");
		builder.append(orderPrice);
		builder.append("]");
		return builder.toString();
	}

}
