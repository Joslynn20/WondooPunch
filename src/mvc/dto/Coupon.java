package mvc.dto;

public class Coupon {
	private String couponCode; // 쿠폰코드
	private String couponName; // 쿠폰명
	private int couponDC; // 할인율
	private String couponRegDate; // 등록일
	private String icCode;// 발행쿠폰코드
	private String userId; // 회원아이디
	private String couponExpDate; // 유효기간
	private String couponIssuedDate;// 발행일

	public Coupon() {
	}

	public Coupon(String couponCode, String couponName, int couponDC, String couponRegDate, String icCode,
			String userId, String couponExpDate, String couponIssuedDate) {
		super();
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.couponDC = couponDC;
		this.couponRegDate = couponRegDate;
		this.icCode = icCode;
		this.userId = userId;
		this.couponExpDate = couponExpDate;
		this.couponIssuedDate = couponIssuedDate;
	}

	String getCouponCode() {
		return couponCode;
	}

	void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	String getCouponName() {
		return couponName;
	}

	void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	int getCouponDC() {
		return couponDC;
	}

	void setCouponDC(int couponDC) {
		this.couponDC = couponDC;
	}

	String getCouponRegDate() {
		return couponRegDate;
	}

	void setCouponRegDate(String couponRegDate) {
		this.couponRegDate = couponRegDate;
	}

	String getIcCode() {
		return icCode;
	}

	void setIcCode(String icCode) {
		this.icCode = icCode;
	}

	String getUserId() {
		return userId;
	}

	void setUserId(String userId) {
		this.userId = userId;
	}

	String getCouponExpDate() {
		return couponExpDate;
	}

	void setCouponExpDate(String couponExpDate) {
		this.couponExpDate = couponExpDate;
	}

	String getCouponIssuedDate() {
		return couponIssuedDate;
	}

	void setCouponIssuedDate(String couponIssuedDate) {
		this.couponIssuedDate = couponIssuedDate;
	}

	//toString
}
