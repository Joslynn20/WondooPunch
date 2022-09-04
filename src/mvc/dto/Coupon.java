package mvc.dto;

public class Coupon {
	private String couponCode; // 쿠폰코드
	private String couponName; // 쿠폰명
	private int couponDC; // 할인율
	private String couponExpDate; // 유효기간
	private String couponRegDate; // 발행일자
	private int icCode;//발행쿠폰
	private String memberId; // 회원아이디
	
	
	public Coupon() {}	
	
	public Coupon(String couponCode, String couponName, int couponDC, String couponExpDate,String couponRegDate) { // 전체 쿠폰 확인 생성자
		super();
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.couponDC = couponDC;
		this.couponExpDate = couponExpDate;
		this.couponRegDate = couponRegDate;
	}
	
	public Coupon(int icCode, String memberId, String couponCode) { // 발행쿠폰 생성자
		super();
		this.icCode = icCode;
		this.memberId = memberId;
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

	public String getCouponExpDate() {
		return couponExpDate;
	}

	public void setCouponExpDate(String couponExpDate) {
		this.couponExpDate = couponExpDate;
	}

	public String getCouponRegDate() {
		return couponRegDate;
	}

	public void setCouponRegDate(String couponRegDate) {
		this.couponRegDate = couponRegDate;
	}

	public int getIcCode() {
		return icCode;
	}

	public void setIcCode(int icCode) {
		this.icCode = icCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return couponCode + " | " + couponName + " | " + couponDC + " | " + couponExpDate + " | " + couponRegDate;
	}
	
	
}
