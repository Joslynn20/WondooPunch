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

	int getOrderLineNo() {
		return orderLineNo;
	}

	void setOrderLineNo(int orderLineNo) {
		this.orderLineNo = orderLineNo;
	}

	int getOrderQty() {
		return orderQty;
	}

	void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	int getOrderPrice() {
		return orderPrice;
	}

	void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	int getOrderNo() {
		return orderNo;
	}

	void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	String getProductCode() {
		return productCode;
	}

	void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	List<DetailOption> getList() {
		return list;
	}

	void setList(List<DetailOption> list) {
		this.list = list;
	}

}
