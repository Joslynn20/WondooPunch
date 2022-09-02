package mvc.dto;

public class Coupon {
	private String couponCode; // 쿠폰코드
	private String couponName; // 쿠폰명
	private int couponDC; // 할인율
	private int couponExpDate; // 유효기간
	private int couponRegDate; // 발행일자
	
	
	public Coupon() {}
	
	
	public Coupon(String couponCode, String couponName, int couponDC, int couponExpDate, int couponRegDate) {
		super();
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.couponDC = couponDC;
		this.couponExpDate = couponExpDate;
		this.couponRegDate = couponRegDate;
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


	public int getCouponExpDate() {
		return couponExpDate;
	}


	public void setCouponExpDate(int couponExpDate) {
		this.couponExpDate = couponExpDate;
	}


	public int getCouponRegDate() {
		return couponRegDate;
	}


	public void setCouponRegDate(int couponRegDate) {
		this.couponRegDate = couponRegDate;
	}


	@Override
	public String toString() {
		return couponCode + " | " + couponName + " | " + couponDC + " | " + couponExpDate + " | " + couponRegDate;
	}
	
}
