package mvc.dto;

public class Coupon {
	private String couponCode; // 쿠폰코드
	private String couponName; // 쿠폰명
	private int couponDC; // 할인율	
	private String couponRegDate; // 등록일
	private String icCode;//발행쿠폰
	private String memberId; // 회원아이디
	private String couponExpDate; // 유효기간
	private String issuedDate;// 발행일
	
	
	public Coupon() { }
	
	public Coupon(int couponDC) { 
		this.couponDC = couponDC;
	}	
	
	public Coupon(String couponCode, String couponName, int couponDC, String couponRegDate) { // 전체 쿠폰 확인 생성자
		super();
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.couponDC = couponDC;		
		this.couponRegDate = couponRegDate;
	}
	
	public Coupon(String icCode, String memberId, String couponCode, String issuedDate, String couponExpDate, int couponDC) { // 발행쿠폰 생성자
		super();
		this.icCode = icCode;
		this.memberId = memberId;
		this.couponCode = couponCode;
		this.issuedDate = issuedDate;
		this.couponExpDate = couponExpDate;
		this.couponCode = couponCode;
	}	

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getCouponDC() {
		return couponDC;
	}

	public void setCouponDC(int couponDC) {
		this.couponDC = couponDC;
	}

	public String getCouponRegDate() {
		return couponRegDate;
	}

	public void setCouponRegDate(String couponRegDate) {
		this.couponRegDate = couponRegDate;
	}

	public String getIcCode() {
		return icCode;
	}

	public void setIcCode(String icCode) {
		this.icCode = icCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCouponExpDate() {
		return couponExpDate;
	}

	public void setCouponExpDate(String couponExpDate) {
		this.couponExpDate = couponExpDate;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	@Override
	public String toString() {
		return couponCode + " | " + couponName + " | " + couponDC + " | " + couponExpDate + " | " + couponRegDate;
	}
	
	
}
