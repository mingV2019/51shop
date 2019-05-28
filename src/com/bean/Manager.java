package com.bean;

public class Manager {
	private int managerId;
	private String managerName;
	private String managerPwd;
	
	public Manager() {
		
	}

	public Manager(int managerId, String managerName, String managerPwd) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerPwd = managerPwd;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPwd() {
		return managerPwd;
	}

	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}
	
	
}
