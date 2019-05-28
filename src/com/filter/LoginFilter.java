package com.filter;

import java.io.IOException;

import javax.jms.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Manager;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		Manager manager = (Manager)request.getSession().getAttribute("managerSession");
		if(manager == null) {
			chain.doFilter(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/goodsinfoservlet?action=showGoodsAll");
			return;
		}
	}

	@Override
	public void init(FilterConfig conifg) throws ServletException {
		
	}

}
