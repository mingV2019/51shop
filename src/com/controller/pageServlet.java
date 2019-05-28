package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DetailOrder;
import com.bean.Goods;
import com.bean.Member;
import com.bean.PageInfo;
import com.bean.ShopOrder;
import com.bean.SubType;
import com.service.GoodsServiceDao;
import com.service.OrderServiceDao;
import com.service.impl.GoodsServiceDaoImpl;
import com.service.impl.OrderServiceDaoImpl;
import com.utils.ChStr;

public class pageServlet extends HttpServlet {
	
	OrderServiceDao orderdao = new OrderServiceDaoImpl();
	List<DetailOrder> detailOrders = new ArrayList<DetailOrder>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		GoodsServiceDao dao = new GoodsServiceDaoImpl();
		List<ShopOrder> shopOrders = new ArrayList<ShopOrder>();
		List<Goods> goodses = new ArrayList<Goods>();
		List<SubType> subTypes = new ArrayList<SubType>();
		String action = request.getParameter("action");
		int pageThis = Integer.parseInt(request.getParameter("pageThis"));
		if("sort".equals(action)) {
			int id = Integer.parseInt(request.getParameter("superId"));
			String type = request.getParameter("type");
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountBySuperID(id),pageThis);
			subTypes = dao.getSubTypeBySuperID(id);
			goodses = dao.getGoodsBySuperID(id, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("superId", request.getParameter("superId"));
			request.setAttribute("subTypes", subTypes);
			request.setAttribute("action", action);
			request.setAttribute("type", type);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("detailSort".equals(action)) {
			int subId = Integer.parseInt(request.getParameter("subId"));
			int superId = Integer.parseInt(request.getParameter("superId"));
			String type = request.getParameter("type");
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByID(subId),pageThis);
			goodses = dao.getGoodsBySubID(subId, pageInfo.getPageStart(), pageInfo.getPageEnd());
			subTypes = dao.getSubTypeBySuperID(superId);
			request.setAttribute("subId", request.getParameter("subId"));
			request.setAttribute("superId", request.getParameter("superId"));
			request.setAttribute("subTypes", subTypes);
			request.setAttribute("action", action);
			request.setAttribute("type", type);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("search".equals(action)) {
			String searchword = request.getParameter("searchword");
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByName(searchword),pageThis);
			goodses = dao.getGoodsByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("action", action);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("shopOrder".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			Member member = (Member)request.getSession().getAttribute("memberSession");
			PageInfo pageInfo = new PageInfo(orderdao.getShopOrderByName(searchword, member.getMemberId()), pageThis);
			shopOrders = orderdao.getShopOrderByName(searchword, member.getMemberId(), pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("shopOrders", shopOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/orderList.jsp");
			dispatcher.forward(request, response);
		}else if("detailOrder".equals(action)) {
			DetailOrderManager(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/detailOrderList.jsp");
			dispatcher.forward(request, response);
		}else if("showGoodsAll".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByName(searchword), pageThis);
			goodses = dao.getGoodsByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("action", action);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/index.jsp");
			dispatcher.forward(request, response);
		}else if("shopOrderManager".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			PageInfo pageInfo = new PageInfo(orderdao.getShopOrderByName(searchword), pageThis);
			shopOrders = orderdao.getShopOrderByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("shopOrders", shopOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/orderManager.jsp");
			dispatcher.forward(request, response);
		}else if("detailOrderManager".equals(action)) {
			DetailOrderManager(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/detailOrderManager.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void DetailOrderManager(HttpServletRequest request,HttpServletResponse response) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int pageThis = Integer.parseInt(request.getParameter("pageThis"));
		PageInfo pageInfo = new PageInfo(orderdao.getDetailOrderCount(orderId), pageThis);
		detailOrders = orderdao.getDetailOrderPage(orderId, pageInfo.getPageStart(), pageInfo.getPageEnd());
		request.setAttribute("orderId", orderId);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("detailOrders", detailOrders);
	}
}
