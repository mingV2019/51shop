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

import com.bean.Cart;
import com.bean.CartItem;
import com.bean.DetailOrder;
import com.bean.Member;
import com.bean.ShopOrder;
import com.bean.PageInfo;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;

public class OrderServlet extends HttpServlet {
	OrderDao dao = new OrderDaoImpl();
	List<ShopOrder> shopOrders = new ArrayList<ShopOrder>();
	List<DetailOrder> detailOrders = new ArrayList<DetailOrder>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		Member member = (Member)request.getSession().getAttribute("memberSession");
		if("addOrder".equals(action)) {
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			int bnumber = cart.getCount();
			String recevieName = request.getParameter("recevieName");
			String tel = request.getParameter("tel");
			String addresses = request.getParameter("address");
			String remark = request.getParameter("remark");
			ShopOrder shopOrder = new ShopOrder(dao.getOrderCount() + 1, bnumber, cart.getTotal(), recevieName, addresses, tel, null, remark, member);
			dao.insertNewOrder(shopOrder);
			for(CartItem cartItem : cart.getCartItems()) {
				DetailOrder detailOrder = new DetailOrder(0, shopOrder, cartItem.getGoods(), cartItem.getTotal(), cartItem.getQuantity(),cartItem.getTotal());
				dao.insertNewDetailOrder(detailOrder);
			}
			cart.clear();
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		} else if("showShopOrder".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			PageInfo pageInfo = new PageInfo(dao.getShopOrderByName(searchword, member.getMemberId()), 1);
			shopOrders = dao.getShopOrderByName(searchword, member.getMemberId(), pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("shopOrders", shopOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/orderList.jsp");
			dispatcher.forward(request, response);
		} else if("showDetailOrder".equals(action)) {
			showDetailShop(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/detailOrderList.jsp");
			dispatcher.forward(request, response);
		} else if("showShopOrderManager".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			PageInfo pageInfo = new PageInfo(dao.getShopOrderByName(searchword), 1);
			shopOrders = dao.getShopOrderByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("shopOrders", shopOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/orderManager.jsp");
			dispatcher.forward(request, response);
		} else if("showDetailOrderManager".equals(action)) {
			showDetailShop(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/detailOrderManager.jsp");
			dispatcher.forward(request, response);
		} else if("delOrder".equals(action)) {
			String _ids[] = request.getParameterValues("del");
			int ids[] = new int[_ids.length-1];
			for(int i=0;i<ids.length;i++) ids[i] = Integer.parseInt(_ids[i]);
			dao.deleteShopOrders(ids);
			PageInfo pageInfo = new PageInfo(dao.getShopOrderByName(""), 1);
			shopOrders = dao.getShopOrderByName("", pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", "");
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("shopOrders", shopOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/orderManager.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void showDetailShop(HttpServletRequest request, HttpServletResponse response) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		PageInfo pageInfo = new PageInfo(dao.getDetailOrderCount(orderId), 1);
		detailOrders = dao.getDetailOrderPage(orderId, pageInfo.getPageStart(), pageInfo.getPageEnd());
		request.setAttribute("orderId", orderId);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("detailOrders", detailOrders);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("shopOrders", shopOrders);
	}
}
