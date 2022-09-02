package mvc.dto;

public class OrderLine {
	
	  private int orderLineNo; // pk
	  private int orderNo; // fk
	  private String productCode;
	  private int orderQty;
	  private int orderPrice;
	  private int categoryCode; //fk
	
	public OrderLine() {
		
	}
	
	public OrderLine(int orderLineNo, int orderNo, String productCode, int orderQty, int orderPrice, int categoryCode) {
		super();
		this.orderLineNo = orderLineNo;
		this.orderNo = orderNo;
		this.productCode = productCode;
		this.orderQty = orderQty;
		this.orderPrice = orderPrice;
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
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("orderLine [orderLineNo=");
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
