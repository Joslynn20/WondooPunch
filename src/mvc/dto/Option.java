package mvc.dto;

public class Option {
	private String optionCategory; // 옵션카테고리
	private String optionCode; // 옵션코드
	private String optionName;  // 옵션이름
	private int optionPrice; // 옵션가격
		
	public Option() { }

	public Option(String optionCode, String optionName, int optionPrice, String optionCategory) {
		super();
		this.optionCategory = optionCategory;
		this.optionCode = optionCode;
		this.optionName = optionName;
		this.optionPrice = optionPrice;		
	}	
		
	public String getOptionCategory() {
		return optionCategory;
	}

	public void setOptionCategory(String optionCategory) {
		this.optionCategory = optionCategory;
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

	@Override
	public String toString() {
		return optionCode + " | " + optionName + " | " + optionPrice + " | " + optionCategory;
	}
	 
} // class end
	
	
	

	
	
	
	

