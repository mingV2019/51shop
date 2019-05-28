package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.bean.Member;
import com.dao.MemberDao;
import com.utils.DBConn;

public class MemberDaoImpl implements MemberDao {

	//
	@Override
	public boolean loginCheck(String userName, String memberPwd) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		boolean check = false;
		String sql = "select userName,memberPwd from member";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				if(userName.equals(rs.getString("userName"))
				&&memberPwd.equals(rs.getString("memberPwd"))) 
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
	
	@Override
	public boolean InsertMemberInfo(Member member) {
		PreparedStatement prst = null;
		Connection conn = null;
		boolean check = true;
		String sql = "insert into member(userName,trueName,memberPwd,Amount,tel,email) values(?,?,?,?,?,?);";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1 ,member.getUserName());
			prst.setString(2 ,member.getTrueName());
			prst.setString(3 ,member.getMemberPwd());
			prst.setInt(4 ,member.getAmount());
			prst.setString(5 ,member.getTel());
			prst.setString(6 ,member.getEmail());
			prst.executeUpdate();
		}catch(Exception e) {
			check = false;
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

	@Override
	public int getMemberNumber() {
		int count = 0;
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select COUNT(*) from member";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
	@Override
	public int getMemberNumber(String userName) {
		int count = 0;
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select COUNT(*) from member where userName = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, userName);
			rs = prst.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
	@Override
	public List<Member> getMembersByName() {
		return null;
	}

	@Override
	public Member getMemberByName(String userName) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		Member member = null;
		String sql = "select memberId,userName,trueName,memberPwd,Amount,tel,email,freeze from member where userName = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, userName);
			rs = prst.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getInt("memberId"), 
								rs.getString("userName"), 
								rs.getString("trueName"), 
								rs.getString("memberPwd"), 
								rs.getInt("amount"), 
								rs.getString("tel"), 
								rs.getString("email"), 
								rs.getInt("freeze"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return member;
	}

	@Override
	public Member getMemberByID(int memberId) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		Member member = null;
		String sql = "select memberId,userName,trueName,memberPwd,Amount,tel,email,freeze from member where memberId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, memberId);
			rs = prst.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getInt("memberId"), 
								rs.getString("userName"), 
								rs.getString("trueName"), 
								rs.getString("memberPwd"), 
								rs.getInt("amount"), 
								rs.getString("tel"), 
								rs.getString("email"), 
								rs.getInt("freeze"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return member;
	}

	@Override
	public boolean updateMemberInfo(Member member) {
		Connection conn = null;
		PreparedStatement prst = null;
		boolean check = false;
		String sql = "update member set userName = ?,trueName = ?,memberPwd = ?,Amount = ?,tel = ?,email = ?,freeze = ? where memberId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, member.getUserName());
			prst.setString(2, member.getTrueName());
			prst.setString(3, member.getMemberPwd());
			prst.setInt(4, member.getAmount());
			prst.setString(5, member.getTel());
			prst.setString(6, member.getEmail());
			prst.setInt(7, member.getFreeze());
			prst.setInt(8, member.getMemberId());
			prst.executeUpdate();
			check = true;
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}

}
