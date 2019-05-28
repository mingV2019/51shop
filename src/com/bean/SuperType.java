package com.bean;

public class SuperType {
	private int superId;
	private String typeName;
	public SuperType() {
		
	}
	public SuperType(int superId, String typeName) {
		super();
		this.superId = superId;
		this.typeName = typeName;
	}
	public int getSuperId() {
		return superId;
	}
	public void setSuperId(int superId) {
		this.superId = superId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
