package mvc.dto;

public class IssuedCoupon {
	private String icCode;// 발행쿠폰코드
	private String userId; // 회원아이디
	private String couponCode; // 쿠폰코드
	private String couponIssuedDate;// 발행일
	private String couponExpDate; // 유효기간
	private int couponDC; // 할인율

	public IssuedCoupon() {}
	
	/**
	 * 마이페이지내 발행쿠폰 확인하기 & 고객 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 * */ 
	public IssuedCoupon (String icCode, String couponIssuedDate, String couponExpDate,int couponDC) { 
		super();
		this.icCode = icCode;
		this.couponIssuedDate = couponIssuedDate;
		this.couponExpDate = couponExpDate;
		this.couponDC = couponDC;
	}

	public String getIcCode() {
		return icCode;
	}

	public void setIcCode(String icCode) {
		this.icCode = icCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponIssuedDate() {
		return couponIssuedDate;
	}

	public void setCouponIssuedDate(String couponIssuedDate) {
		this.couponIssuedDate = couponIssuedDate;
	}

	public String getCouponExpDate() {
		return couponExpDate;
	}

	public void setCouponExpDate(String couponExpDate) {
		this.couponExpDate = couponExpDate;
	}

	public int getCouponDC() {
		return couponDC;
	}

	public void setCouponDC(int couponDC) {
		this.couponDC = couponDC;
	}

	@Override
	public String toString() {
		return  icCode + " | " + couponIssuedDate + " | " + couponExpDate + " | " + couponDC;
	}
	
	
	



}
