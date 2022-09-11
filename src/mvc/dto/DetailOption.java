package mvc.dto;

public class DetailOption {

	private int detailOptionNo; // 상세옵션번호
	private int superNo; // 장바구니 or 주문상세번호
	private String optionCode; // 옵션코드
	private int detailOtionQty; // 옵션수량
	private int detailOptionPrice; // 옵션 총 가격

	public DetailOption() {

	}

	public DetailOption(String optionCode, int detailOtionQty) {
		this.optionCode = optionCode;
		this.detailOtionQty = detailOtionQty;
	}

	public DetailOption(int detailOptionNo, int superNo, String optionCode, int detailOtionQty, int detailOptionPrice) {
		super();
		this.detailOptionNo = detailOptionNo;
		this.superNo = superNo;
		this.optionCode = optionCode;
		this.detailOtionQty = detailOtionQty;
		this.detailOptionPrice = detailOptionPrice;
	}

	public int getDetailOptionNo() {
		return detailOptionNo;
	}

	public void setDetailOptionNo(int detailOptionNo) {
		this.detailOptionNo = detailOptionNo;
	}

	public int getSuperNo() {
		return superNo;
	}

	public void setSuperNo(int superNo) {
		this.superNo = superNo;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public int getDetailOtionQty() {
		return detailOtionQty;
	}

	public void setDetailOtionQty(int detailOtionQty) {
		this.detailOtionQty = detailOtionQty;
	}

	public int getDetailOptionPrice() {
		return detailOptionPrice;
	}

	public void setDetailOptionPrice(int detailOptionPrice) {
		this.detailOptionPrice = detailOptionPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("주문상세 옵션 [주문옵션번호: ");
		builder.append(detailOptionNo);
		builder.append(", 옵션코드: ");
		builder.append(optionCode);
		builder.append(", 옵션 수량: ");
		builder.append(detailOtionQty);
		builder.append(", 옵션 가격: ");
		builder.append(detailOptionPrice);
		builder.append("]");
		return builder.toString();
	}

}
