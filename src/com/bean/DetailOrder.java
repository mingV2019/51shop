package com.bean;

public class DetailOrder {
	private int detailOrderId;
	private ShopOrder shopOrder;
	private Goods goods;
	private int price;
	private int number;
	private int total;
	
	public DetailOrder() {
		
	}

	public DetailOrder(int detailOrderId, ShopOrder shopOrder, Goods goods,
			int price, int number, int total) {
		super();
		this.detailOrderId = detailOrderId;
		this.shopOrder = shopOrder;
		this.goods = goods;
		this.price = price;
		this.number = number;
		this.total = total;
	}

	public int getDetailOrderId() {
		return detailOrderId;
	}

	public void setDetailOrderId(int detailOrderId) {
		this.detailOrderId = detailOrderId;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
