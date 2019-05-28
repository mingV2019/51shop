package com.utils;

public class ChStr {
	
	/**
	 * 功能：解决中文乱码问题
	 */
	
	public String chStr(String str) {
		if(str == null) {
			str = "";
		} else {
			try {
				//将中文字符串转换为UTF-8编码
				str = new String (str.getBytes("ISO-8859-1"),"UTF-8").trim();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}
