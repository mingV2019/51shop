package com.service.impl;

import com.bean.Manager;
import com.dao.ManagerDao;
import com.dao.impl.ManagerDaoImpl;
import com.service.ManagerServiceDao;

public class ManagerServiceDaoImpl implements ManagerServiceDao {

	ManagerDao dao = new ManagerDaoImpl();
	@Override
	public boolean managerLogin(Manager manager) {
		return dao.managerLogin(manager);
	}
	
}
