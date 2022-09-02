package mvc.dto;

public class Option {
	private String optionCategory; // 옵션카테고리
	private String optionCode; // 옵션코드
	private String optionName;  // 옵션이름
	private int optionPrice; // 옵션가격
		
	public Option() { }

	public Option(String optionCategory, String optionCode, String optionName, int optionPrice) { // 옵션 등록생성자
		super();
		this.optionCategory = optionCategory;
		this.optionCode = optionCode;
		this.optionName = optionName;
		this.optionPrice = optionPrice;		
	}	
	
	public Option(String optionCode, int optionPrice) { // 옵션 수정생성자
		this.optionCode = optionCode;
		this.optionPrice = optionPrice;
	}

	public Option(String optionCode) {	// 옵션 삭제생성자
		this.optionCode = optionCode;
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
		return optionCategory + " | " + optionCode + " | " + optionName + " | " + optionPrice;
	}
	 /**
	  * 객체가 다르더라도 상품번호가 같으면 무조건 같다라고 이해하기 위해 오버라이드
	  * */

	@Override
	public int hashCode() {
		
		return optionCode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Option other = (Option) obj;
		if(optionCode.equals(other.optionCode)) {
			return true;
		} else {
		return false;
		}
	}

} // class end
	
	
	

	
	
	
	

