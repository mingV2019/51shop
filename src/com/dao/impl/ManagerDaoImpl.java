package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Manager;
import com.dao.ManagerDao;
import com.utils.DBConn;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public boolean managerLogin(Manager manager) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		boolean check = false;
		String sql = "select managerId,managerName,managerPwd from manager";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				if(manager.getManagerName().equals(rs.getString("managerName"))
				&&manager.getManagerPwd().equals(rs.getString("managerPwd"))) 
				check = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				check = false;
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					check = false;
					e.printStackTrace();
				}
			}
		}
		return check;
	}
	
}
