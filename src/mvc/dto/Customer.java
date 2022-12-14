package mvc.dto;

public class Customer {

	private String userId; // 회원ID
	private String userPw; // 회원비밀번호
	private String userName; // 이름
	private String userBirth; // 생년월일
	private String userPhoneNo; // 전화번호
	private String userRegDate; // 가입일

	public Customer() {

	}

	/**
	 * 고객 수정 생성자
	 */

	public Customer(String userId, String userPw, String userName, String userBirth, String userPhoneNo) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userPhoneNo = userPhoneNo;

	}

	public Customer(String userId, String userPw, String userName, String userBirth, String userPhoneNo,
			String userRegDate) {
		this(userId, userPw, userName, userBirth, userPhoneNo);
		this.userRegDate = userRegDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getRegDate() {
		return userRegDate;
	}

	public void setRegDate(String regDate) {
		this.userRegDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Id: ");
		builder.append(userId);
		builder.append(", Password: ");
		builder.append(userPw);
		builder.append(", 이름: ");
		builder.append(userName);
		builder.append(", 생년월일: ");
		builder.append(userBirth);
		builder.append(", 전화번호=");
		builder.append(userPhoneNo);
		builder.append(", 가입일=");
		builder.append(userRegDate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return userId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		/*
		 * if (this == obj) return true; if (obj == null) return false; if (getClass()
		 * != obj.getClass()) return false;
		 */
		Customer other = (Customer) obj;
		if (this.userId.equals(other.getUserId())) {
			return true;
		}
		return false;

	}

}
