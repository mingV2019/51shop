package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Cart;
import com.bean.CartItem;
import com.bean.Goods;
import com.bean.Member;
import com.service.GoodsServiceDao;
import com.service.impl.GoodsServiceDaoImpl;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		GoodsServiceDao dao = new GoodsServiceDaoImpl();
		Member member = new Member();
		Goods goods = new Goods();
		member = (Member)request.getSession().getAttribute("memberSession");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String action = request.getParameter("action");
		if("addCart".equals(action)) {
			if(member==null) response.sendRedirect(request.getContextPath()+"/front/login.jsp");
			else {
				if(cart==null) cart = new Cart();
				int goodsId = Integer.parseInt(request.getParameter("goodsId"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				String url = request.getParameter("url");
				goods = dao.getGoodsInfoByID(goodsId);
				CartItem cartItem = new CartItem(quantity, goods);
				cart.add(cartItem);
				request.getSession().setAttribute("cart", cart);
				response.sendRedirect(request.getContextPath()+url);
			}
		}else if("clearCart".equals(action)) {
			cart.clear();
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		}else if("saveCart".equals(action)) {
			if(cart==null) cart = new Cart();
			for(int i=0;i < cart.getCount();i++) {
				int goodsId = Integer.parseInt(request.getParameter("goodsId"+i));
				int quantity = Integer.parseInt(request.getParameter("quantity"+i));
				CartItem cartItem = new CartItem();
				cartItem.setQuantity(quantity);
				goods.setGoodsId(goodsId);
				cartItem.setGoods(goods);
				cart.saveCart(cartItem);
			}
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/front/cart_see.jsp");
		}else if("deleteCart".equals(action)) {
			int goodsId = Integer.parseInt(request.getParameter("goodsId"));
			cart.del(goodsId);
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect("front/cart_see.jsp");
		}
	}
}
