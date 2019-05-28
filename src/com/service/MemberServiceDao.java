package com.service;

import java.util.List;

import com.bean.Member;
import com.bean.SubType;
import com.bean.SuperType;

public interface MemberServiceDao {
	
	/**
	 * 用户登录
	 */
	public boolean loginCheck(String userName, String memberPwd);
	
	/**
	 * 增加用户信息
	 */
	public boolean InsertMemberInfo(Member member);
	
	/**
	 * 查询用户数量
	 */
	public int getMemberNumber();
	
	/**
	 * 修改用户信息
	 */
	public int getMemberNumber(String userName);
	
	/**
	 * 根据姓名检索用户信息
	 */
	public List<Member> getMembersByName();
	
	/**
	 * 根据用户名查询用户信息
	 */
	public Member getMemberByName(String userName);
	
	/**
	 * 修改用户信息
	 */
	public boolean updateMemberInfo(Member member);
	
}
