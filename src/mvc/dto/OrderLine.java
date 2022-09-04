package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderLine {
	
	  private int orderLineNo; // pk
	  private int orderQty;
	  private int orderPrice;
	  private int orderNo; // fk
	  private String productCode;
	  private String categoryCode; //fk
	  
	  private DesertOption dessertOption = new DesertOption();
	  private CoffeeOption coffeOption = new CoffeeOption();
	
	public OrderLine() {
		
	}
	
	
	public OrderLine(int orderLineNo, int orderQty, int orderPrice, int orderNo, String productCode, String categoryCode) {
		super();
		this.orderLineNo = orderLineNo;
		this.orderQty = orderQty;
		this.orderPrice = orderPrice;
		this.orderNo = orderNo;
		this.productCode = productCode;
		this.categoryCode = categoryCode;
	}

	public int getOrderLineNo() {
		return orderLineNo;
	}
	public void setOrderLineNo(int orderLineNo) {
		this.orderLineNo = orderLineNo;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	

	public DesertOption getDessertOption() {
		return dessertOption;
	}


	public void setDessertOption(DesertOption dessertOption) {
		this.dessertOption = dessertOption;
	}


	public CoffeeOption getCoffeOption() {
		return coffeOption;
	}


	public void setCoffeOption(CoffeeOption coffeOption) {
		this.coffeOption = coffeOption;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderLine [orderLineNo=");
		builder.append(orderLineNo);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", orderQty=");
		builder.append(orderQty);
		builder.append(", orderPrice=");
		builder.append(orderPrice);
		builder.append(", categoryCode=");
		builder.append(categoryCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
