package com.bean;

public class CartItem {
	private int quantity;
	private Goods goods;
	private int total;
	
	public CartItem() {
		
	}
	
	public CartItem(int quantity, Goods goods) {
		super();
		this.quantity = quantity;
		this.goods = goods;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		quantity = quantity < 1 ? 1 : quantity;
		this.quantity = quantity;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getTotal() {
		total = quantity * goods.getNowPrice();
		return total;
	}
	
}
