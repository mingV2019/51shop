package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Member;
import com.service.MemberServiceDao;
import com.service.impl.MemberServiceDaoImpl;
import com.utils.variable;

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String url = request.getParameter("url");
		MemberServiceDao dao = new MemberServiceDaoImpl();
		if ("register".equals(action)) {
			String userName = request.getParameter("username");
			String trueName = request.getParameter("truename");
			String memberPwd = request.getParameter("pwd");
			int amount = variable.AUMOUNT;
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			int freeze = variable.FREAZE;
			Member member = new Member(0, userName, trueName, memberPwd,amount, tel, email, freeze);
			dao.InsertMemberInfo(member);
			response.sendRedirect(request.getContextPath() + "/front/login.jsp");
		} else if ("login".equals(action)) {
			String userName = request.getParameter("username");
			String memberPwd = request.getParameter("password");
			String codeText = request.getParameter("checkCode");
			String code = (String) request.getSession().getAttribute(
					"randCheckCode");
			boolean loginCheck = dao.loginCheck(userName, memberPwd);
			boolean codeCheck = codeText.equalsIgnoreCase(code);
			if (loginCheck == true && codeCheck == true) {
				Member member = new Member();
				member = dao.getMemberByName(userName);
				request.getSession().setAttribute("memberSession", member);
				if ("".equals(url) == true)
					response.sendRedirect("front/index.jsp");
				else
					response.sendRedirect(request.getContextPath() + url);
			} else {
				String message = "";
				if (loginCheck == false)
					message = message + "<br>用户名或密码有误";
				if (codeCheck == false)
					message = message + "<br>验证码输入错误";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/front/login.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("editPassword".equals(action)) {
			String userName = request.getParameter("username");
			String memberPwd = request.getParameter("pwd");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			Member _member = (Member)request.getSession().getAttribute("memberSession");
			Member member = new Member(_member.getMemberId(), userName, _member.getTrueName(), 
					memberPwd,_member.getAmount(), tel, email, _member.getFreeze());
			dao.updateMemberInfo(member);
			response.sendRedirect(request.getContextPath() + "/front/login.jsp");
		} else if ("".equals(action)) {
			
		} else if ("destroy".equals(action)) {
			request.getSession().removeAttribute("memberSession");
			request.getSession().removeAttribute("cart");
			response.sendRedirect(request.getContextPath() + "/front/login.jsp");
		}
	}
}
