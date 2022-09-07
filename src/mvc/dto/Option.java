package mvc.dto;

public class Option {
	private String optionCode; // 옵션코드
	private String optionName; // 옵션이름
	private int optionPrice; // 옵션가격
	private String productCode; // 상품코드

	public Option() {
	}

	public Option(String optionCode, String optionName, int optionPrice, String productCode) {
		super();
		this.optionCode = optionCode;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.productCode = productCode;
	}

	String getOptionCode() {
		return optionCode;
	}

	void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	String getOptionName() {
		return optionName;
	}

	void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	int getOptionPrice() {
		return optionPrice;
	}

	void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	String getProductCode() {
		return productCode;
	}

	void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return optionCode + " | " + optionName + " | " + optionPrice + " | " + productCode;
	}

} // class end
