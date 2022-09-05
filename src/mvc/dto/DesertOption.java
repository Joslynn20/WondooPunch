package mvc.dto;

public class DesertOption {
	private int desertOptionNo; // 디저트상세옵션번호
	private int superNo; // 주문상세번호
	private int hotQty; // 데우기
	private int cheeseQty; // 크림치즈추가
	
	public DesertOption() {}

	public DesertOption(int desertOptionNo, int superNo, int hotQty, int cheeseQty) {
		super();
		this.desertOptionNo = desertOptionNo;
		this.superNo = superNo;
		this.hotQty = hotQty;
		this.cheeseQty = cheeseQty;
	}

	

	public DesertOption(int hotQty, int cheeseQty) { // 고객 주문에서 디저트옵션추가
		super();
		this.hotQty = hotQty;
		this.cheeseQty = cheeseQty;
	}

	public int getDesertOptionNo() {
		return desertOptionNo;
	}

	public void setDesertOptionNo(int desertOptionNo) {
		this.desertOptionNo = desertOptionNo;
	}

	public int getSuperNo() {
		return superNo;
	}

	public void setSuperNo(int superNo) {
		this.superNo = superNo;
	}

	public int getHotQty() {
		return hotQty;
	}

	public void setHotQty(int hotQty) {
		this.hotQty = hotQty;
	}

	public int getCheeseQty() {
		return cheeseQty;
	}

	public void setCheeseQty(int cheeseQty) {
		this.cheeseQty = cheeseQty;
	}

	@Override
	public String toString() {
		return desertOptionNo + " | " + superNo + " | " + hotQty + " | " + cheeseQty;
	}
	
	
}
