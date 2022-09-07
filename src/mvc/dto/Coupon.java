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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCouponExpDate() {
		return couponExpDate;
	}

	public void setCouponExpDate(String couponExpDate) {
		this.couponExpDate = couponExpDate;
	}

	public String getCouponIssuedDate() {
		return couponIssuedDate;
	}

	public void setCouponIssuedDate(String couponIssuedDate) {
		this.couponIssuedDate = couponIssuedDate;
	}

	// toString
}
