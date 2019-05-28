package com.service.impl;

import java.util.List;

import com.bean.DetailOrder;
import com.bean.ShopOrder;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.service.OrderServiceDao;

public class OrderServiceDaoImpl implements OrderServiceDao {

	OrderDao dao = new OrderDaoImpl();

	@Override
	public boolean deleteShopOrders(int[] id) {
		return dao.deleteShopOrders(id);
	}
	
	@Override
	public boolean insertNewOrder(ShopOrder shopOrder) {
		return dao.insertNewOrder(shopOrder);
	}
	
	@Override
	public boolean insertNewDetailOrder(DetailOrder detailOrder) {
		return dao.insertNewDetailOrder(detailOrder);
	}
	
	@Override
	public int getOrderCount() {
		return dao.getOrderCount();
	}
	
	@Override
	public int getOrderCount(int id) {
		return dao.getOrderCount(id);
	}
	
	@Override
	public int getDetailOrderCount(int id) {
		return dao.getDetailOrderCount(id);
	}
	
	@Override
	public ShopOrder getShopOrderByID(int id) {
		return dao.getShopOrderByID(id);
	}

	@Override
	public int getShopOrderByName(String name, int id) {
		return dao.getShopOrderByName(name, id);
	}

	@Override
	public int getShopOrderByName(String name) {
		return dao.getShopOrderByName(name);
	}
	
	@Override
	public List<ShopOrder> getShopOrderByName(String name, int id, int start, int end) {
		return dao.getShopOrderByName(name, id, start, end);
	}
	
	@Override
	public List<ShopOrder> getShopOrderByName(String name, int start, int end) {
		return dao.getShopOrderByName(name, start, end);
	}
	
	@Override
	public List<DetailOrder> getDetailOrderPage(int id, int start, int end) {
		return dao.getDetailOrderPage(id, start, end);
	}
	
}
