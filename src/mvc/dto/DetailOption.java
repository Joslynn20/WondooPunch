package mvc.dto;

public class DetailOption {

	private int detailOptionNo; // 상세옵션번호
	private int superNo; //장바구니 or 주문상세번호
	private int optionCode; // 옵션코드
	private int detailOtionQty; // 옵션수량
	private int detailOptionPrice; // 옵션 총 가격

	public DetailOption() {

	}

	public DetailOption(int detailOptionNo, int superNo, int optionCode, int detailOtionQty, int detailOptionPrice) {
		super();
		this.detailOptionNo = detailOptionNo;
		this.superNo = superNo;
		this.optionCode = optionCode;
		this.detailOtionQty = detailOtionQty;
		this.detailOptionPrice = detailOptionPrice;
	}

	int getDetailOptionNo() {
		return detailOptionNo;
	}

	void setDetailOptionNo(int detailOptionNo) {
		this.detailOptionNo = detailOptionNo;
	}

	int getSuperNo() {
		return superNo;
	}

	void setSuperNo(int superNo) {
		this.superNo = superNo;
	}

	int getOptionCode() {
		return optionCode;
	}

	void setOptionCode(int optionCode) {
		this.optionCode = optionCode;
	}

	int getDetailOtionQty() {
		return detailOtionQty;
	}

	void setDetailOtionQty(int detailOtionQty) {
		this.detailOtionQty = detailOtionQty;
	}

	int getDetailOptionPrice() {
		return detailOptionPrice;
	}

	void setDetailOptionPrice(int detailOptionPrice) {
		this.detailOptionPrice = detailOptionPrice;
	}

}
