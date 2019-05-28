package com.dao;

import com.bean.Manager;

public interface ManagerDao {
	
	/**
	 * 管理员登录
	 */
	public boolean managerLogin(Manager manager);
}
