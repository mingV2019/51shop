package com.service.impl;

import java.util.List;

import com.bean.Member;
import com.dao.MemberDao;
import com.dao.impl.MemberDaoImpl;
import com.service.MemberServiceDao;

public class MemberServiceDaoImpl implements MemberServiceDao {
	
	MemberDao  dao = new MemberDaoImpl();
	
	@Override
	public boolean loginCheck(String userName, String memberPwd) {
		return dao.loginCheck(userName, memberPwd);
	}

	@Override
	public boolean InsertMemberInfo(Member member) {
		return dao.InsertMemberInfo(member);
	}
	
	@Override
	public int getMemberNumber() {
		return dao.getMemberNumber();
	}
	
	@Override
	public int getMemberNumber(String userName) {
		return dao.getMemberNumber(userName);
	}
	
	@Override
	public List<Member> getMembersByName() {
		return dao.getMembersByName();
	}

	@Override
	public Member getMemberByName(String userName) {
		return dao.getMemberByName(userName);
	}

	@Override
	public boolean updateMemberInfo(Member member) {
		return dao.updateMemberInfo(member);
	}

}
