package com.bean;

public class Goods {
	private int goodsId;
	private String goodsName;
	private String introduce;
	private int price;
	private int nowPrice;
	private String picture;
	private String InTime;
	private int newsGoods;
	private int sale;
	private int hit;
	private SubType subType;
	
	public Goods() {
		
	}

	public Goods(int goodsId, String goodsName, String introduce, int price,
			int nowPrice, String picture, String inTime, int newsGoods,
			int sale, int hit, SubType subType) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.introduce = introduce;
		this.price = price;
		this.nowPrice = nowPrice;
		this.picture = picture;
		InTime = inTime;
		this.newsGoods = newsGoods;
		this.sale = sale;
		this.hit = hit;
		this.subType = subType;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(int nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getInTime() {
		return InTime;
	}

	public void setInTime(String inTime) {
		InTime = inTime;
	}

	public int getNewsGoods() {
		return newsGoods;
	}

	public void setNewsGoods(int newsGoods) {
		this.newsGoods = newsGoods;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public SubType getSubType() {
		return subType;
	}

	public void setSubType(SubType subType) {
		this.subType = subType;
	}
	
	
}
