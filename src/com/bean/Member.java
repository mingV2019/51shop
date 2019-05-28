package com.bean;

public class Member {
	private int memberId;
	private String userName;
	private String trueName;
	private String memberPwd;
	// private String city;
	// private String addresses;
	// private String postcode;
	//private int orderId;
	// private String cardType;
	// private int grade;
	private int Amount;
	private String tel;
	private String email;
	private int freeze;

	public Member() {

	}

	public Member(int memberId, String userName, String trueName,
			String memberPwd, int amount, String tel,
			String email, int freeze) {
		
		this.memberId = memberId;
		this.userName = userName;
		this.trueName = trueName;
		this.memberPwd = memberPwd;
		this.Amount = amount;
		this.tel = tel;
		this.email = email;
		this.freeze = freeze;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	/*
	 * public String getCity() { return city; } public void setCity(String city)
	 * { this.city = city; } public String getAddresses() { return addresses; }
	 * public void setAddresses(String addresses) { this.addresses = addresses;
	 * } public String getPostcode() { return postcode; } public void
	 * setPostcode(String postcode) { this.postcode = postcode; }
	 
		public int getorderId() {
			return orderId;
		}
	
		public void setorderId(int orderId) {
			this.orderId = orderId;
		}

	
	 * public String getCardType() { return cardType; } public void
	 * setCardType(String cardType) { this.cardType = cardType; } public int
	 * getGrade() { return grade; } public void setGrade(int grade) { this.grade
	 * = grade; }
	 */
	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFreeze() {
		return freeze;
	}

	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}

}
