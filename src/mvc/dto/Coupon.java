package mvc.dto;

public class Coupon {
	private String couponCode; // 쿠폰코드
	private String couponName; // 쿠폰명
	private int couponDC; // 할인율
	private String couponRegDate; // 등록일

	public Coupon() {
	}

	public Coupon(int couponDC) {
		this.couponDC = couponDC;
	}

	/**
	 * 전체쿠폰 확인 생성자
	 */
	public Coupon(String couponCode, String couponName, int couponDC, String couponRegDate) {
		super();
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.couponDC = couponDC;
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

	public String getCouponRegDate() {
		return couponRegDate;
	}

	public void setCouponRegDate(String couponRegDate) {
		this.couponRegDate = couponRegDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("쿠폰 [쿠폰코드: ");
		builder.append(couponCode);
		builder.append(", 쿠폰명: ");
		builder.append(couponName);
		builder.append(", 할인율: ");
		builder.append(couponDC);
		builder.append(", 쿠폰등록일: ");
		builder.append(couponRegDate);
		builder.append("]");
		return builder.toString();
	}

}
