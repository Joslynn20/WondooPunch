package mvc.dto;

public class Option {
	private String optionCode; // 옵션코드
	private String optionName; // 옵션이름
	private int optionPrice; // 옵션가격
	private String productCode; // 상품코드

	public Option() {
	}

	/**
	 * 옵션전체검색 & 옵션등록
	 */
	public Option(String optionCode, String optionName, int optionPrice, String productCode) {
		super();
		this.optionCode = optionCode;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.productCode = productCode;
	}

	/**
	 * 상품코드 & 옵션코드 검색으로 수정하기
	 */
	public Option(String optionCode, String optionName, int optionPrice) {
		this.optionCode = optionCode;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "옵션 목록 : " + optionCode + " | " + optionName + " | " + optionPrice + " | " + productCode;
	}

} // class end
