package com.service;

import java.util.List;

import com.bean.DetailOrder;
import com.bean.ShopOrder;

public interface OrderServiceDao {
	
	/**
	 * 删除多条订单
	 */
	public boolean deleteShopOrders(int id[]);
	
	/**
	 * 创建一个新的订单
	 */
	public boolean insertNewOrder(ShopOrder shopOrder);
	
	/**
	 * 添加一个新的详细订单
	 */
	public boolean insertNewDetailOrder(DetailOrder detailOrder);
	
	/**
	 * 查询订单总个数
	 */
	public int getOrderCount();
	
	/**
	 * 根据用户ID查询订单总个数
	 */
	public int getOrderCount(int id);
	
	/**
	 * 根据订单收件人姓名查询订单个数
	 */
	public int getShopOrderByName(String name,int id);
	
	/**
	 * 根据订单收件人姓名查询订单个数
	 */
	public int getShopOrderByName(String name);
	
	/**
	 * 查询详细订单个数
	 */
	public int getDetailOrderCount(int id);
	
	/**
	 * 根据订单ID查询单个订单
	 */
	public ShopOrder getShopOrderByID(int id);
	
	/**
	 * 查询所有订单
	 */
	public List<ShopOrder> getShopOrderByName(String name, int id, int start, int end);
	
	/**
	 * 根据收件人姓名检索所有订单
	 */
	public List<ShopOrder> getShopOrderByName(String name, int start, int end);
	
	/**
	 * 查询所有订单
	 */
	public List<DetailOrder> getDetailOrderPage(int id, int start, int end);
}
