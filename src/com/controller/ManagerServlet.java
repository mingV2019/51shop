package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Manager;
import com.service.ManagerServiceDao;
import com.service.impl.ManagerServiceDaoImpl;

public class ManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		ManagerServiceDao dao = new ManagerServiceDaoImpl();
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Manager manager = new Manager(0, username, password);
			boolean check = dao.managerLogin(manager);
			if(check == true) {
				request.getSession().setAttribute("managerSession", manager);
				response.sendRedirect("goodsinfoservlet?action=showGoodsAll");
			}
			else {
				request.setAttribute("message", "用户名或密码不正确");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/login_M.jsp");
				dispatcher.forward(request, response);
			}
		} else if("destroy".equals(action)) {
			request.getSession().removeAttribute("managerSession");
			response.sendRedirect("manager/login_M.jsp");
		}
	}
}
