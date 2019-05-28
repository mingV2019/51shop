package com.service;

import com.bean.Manager;

public interface ManagerServiceDao {
	
	/**
	 * 管理员登录
	 */
	public boolean managerLogin(Manager manager);
}
