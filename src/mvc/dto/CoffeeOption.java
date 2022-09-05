package mvc.dto;

public class CoffeeOption {
	private int coffeeOptionNo; // 커피상세옵션번호
	private int superQty; // 주문상세번호
	private int shotQty; // 샷추가
	private int creamQty; // 휘핑크림추가
	private int syrupQty; // 시럽추가
	
	public CoffeeOption() { }

	public CoffeeOption(int coffeeOptionNo, int superQty, int shotQty, int creamQty, int syrupQty) {
		super();
		this.coffeeOptionNo = coffeeOptionNo;
		this.superQty = superQty;
		this.shotQty = shotQty;
		this.creamQty = creamQty;
		this.syrupQty = syrupQty;
	}
	public CoffeeOption(int shotQty, int creamQty, int syrupQty) { // 고객 커피옵션추가생성자
		this.shotQty = shotQty;
		this.creamQty = creamQty;
		this.syrupQty = syrupQty;
	}

	public int getCoffeeOptionNo() {
		return coffeeOptionNo;
	}

	public void setCoffeeOptionNo(int coffeeOptionNo) {
		this.coffeeOptionNo = coffeeOptionNo;
	}

	public int getSuperQty() {
		return superQty;
	}

	public void setSuperQty(int superQty) {
		this.superQty = superQty;
	}

	public int getShotQty() {
		return shotQty;
	}

	public void setShotQty(int shotQty) {
		this.shotQty = shotQty;
	}

	public int getCreamQty() {
		return creamQty;
	}

	public void setCreamQty(int creamQty) {
		this.creamQty = creamQty;
	}

	public int getSyrupQty() {
		return syrupQty;
	}

	public void setSyrupQty(int syrupQty) {
		this.syrupQty = syrupQty;
	}

	@Override
	public String toString() {
		return coffeeOptionNo +" | " + superQty +" | " + shotQty  +" | " + creamQty +" | " + syrupQty;
	}
	

	

	
	
}
