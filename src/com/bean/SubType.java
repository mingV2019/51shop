package com.bean;

public class SubType {
	private int subId;
	private String typeName;
	private SuperType superType;
	
	public SubType(){
		
	}

	public SubType(int subId, String typeName, SuperType superType) {
		super();
		this.subId = subId;
		this.typeName = typeName;
		this.superType = superType;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public SuperType getSuperType() {
		return superType;
	}

	public void setSuperType(SuperType superType) {
		this.superType = superType;
	}
	
}
