package com.bean;

import com.utils.variable;

public class PageInfo {
	private int pageStart;		//页面开始定位
	private int pageEnd;		//页面结束定位
	private int recordCount;	//记录总数
	private int pageTotal;		//页面总数
	private int pageThis;		//当前页面数
	
	public PageInfo() {
		
	}
	
	public PageInfo(int recordCount, int pageThis) {
		this.recordCount = recordCount;
		this.pageThis = pageThis;
	}
	
	public int getPageThis() {
		if(pageThis == 0) pageThis = 1;
		else if(pageThis > getPageTotal()) pageThis = getPageTotal();
		return pageThis;
	}
	public void setPageThis(int pageThis) {
		this.pageThis = pageThis;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public int getPageTotal() {
		pageTotal = (getRecordCount()-1)/variable.PAGECOUNT + 1;
		return pageTotal;
	}
	
	public int getPageStart() {
		pageStart = (getPageThis() - 1)*variable.PAGECOUNT;
		return pageStart;
	}
	
	public int getPageEnd() {
		pageEnd = getPageThis()*variable.PAGECOUNT;
		return pageEnd;
	}
	
}
