package mvc.dto;

public class DetailOption {

	private int detailOptionNo; // 상세옵션번호
	private int superNo; // 장바구니 or 주문상세번호
	private String optionCode; // 옵션코드
	private int detailOtionQty; // 옵션수량
	private int detailOptionPrice; // 옵션 총 가격

	public DetailOption() {

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

	public void setOptionCode(String string) {
		this.optionCode = string;
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

}
