package com.bean;

public class ShopOrder {
	private int orderId;
	private int bnumber;
	private int total;
	private String receiveName;
	private String addresses;
	private String tel;
	private String orderDate;
	private String remark;
	private Member member;
	
	public ShopOrder() {
		
	}

	public ShopOrder(int orderId, int bnumber, int total, String receiveName,
			String addresses, String tel, String orderDate, String remark,
			Member member) {
		super();
		this.orderId = orderId;
		this.bnumber = bnumber;
		this.total = total;
		this.receiveName = receiveName;
		this.addresses = addresses;
		this.tel = tel;
		this.orderDate = orderDate;
		this.remark = remark;
		this.member = member;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBnumber() {
		return bnumber;
	}

	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	

}
