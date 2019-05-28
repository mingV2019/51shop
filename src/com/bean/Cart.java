package com.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	/**
	 * map创建
	 * 1.创建Map存放条目，以物品ID为键
	 * 2.创建LinkedHashMap，为了保证顺序
	 */
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	public int getTotal() {
		int total = 0;
		for(CartItem cartItem : map.values()) {
			total+=cartItem.getTotal();
		}
		return total;
	}
	
	public void add(CartItem cartItem) {
		if(map.containsKey(cartItem.getGoods().getGoodsId())) {
			//1.拿到原条目
			CartItem _cartItem = map.get(cartItem.getGoods().getGoodsId());
			//2.条目数量合并 （原数量 + 新数量）
			_cartItem.setQuantity(_cartItem.getQuantity() + cartItem.getQuantity());
			//3.将合并后的条目放进map
			map.put(cartItem.getGoods().getGoodsId(), _cartItem);
		} else {
			//直接存放
			map.put(cartItem.getGoods().getGoodsId(), cartItem);
		}
	}
	
	public void del(int goodsId) {
		map.remove(goodsId);
	}
	
	public void clear() {
		map.clear();
	}
	
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	public int getCount() {
		return map.size();
	}
	
	public void saveCart(CartItem cartItem) {
		if(map.containsKey(cartItem.getGoods().getGoodsId())) {
			CartItem _cartItem = map.get(cartItem.getGoods().getGoodsId());
			_cartItem.setQuantity(cartItem.getQuantity());
			map.put(cartItem.getGoods().getGoodsId(), _cartItem);
		}
	}
}
