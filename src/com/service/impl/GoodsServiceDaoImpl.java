package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bean.Goods;
import com.bean.SubType;
import com.bean.SuperType;
import com.dao.GoodsDao;
import com.dao.impl.GoodsDaoImpl;
import com.service.GoodsServiceDao;

public class GoodsServiceDaoImpl implements GoodsServiceDao {

	GoodsDao dao = new GoodsDaoImpl();
	
	@Override
	public List<Goods> getGoodsBySubID(int id,int start ,int end) {
		return dao.getGoodsBySubID(id, start, end);
	}
	
	@Override
	public List<Goods> getGoodsBySuperID(int id, int start, int end) {
		return dao.getGoodsBySuperID(id, start, end);
	}
	
	@Override
	public int getGoodsCountByName(String name) {
		return dao.getGoodsCountByName(name);
	}
	

	@Override
	public int getGoodsCountByID(int id) {
		return dao.getGoodsCountByID(id);
	}
	
	@Override
	public int getGoodsCountBySuperID(int id) {
		return dao.getGoodsCountBySuperID(id);
	}
	
	@Override
	public List<Goods> getNewGoodsAll() {
		List<Goods> goodses = new ArrayList<Goods>();
		goodses = dao.getNewGoodsAll();
		return goodses;
	}

	@Override
	public List<Goods> getDiscountAll() {
		List<Goods> goodses = new ArrayList<Goods>();
		goodses = dao.getDiscountAll();
		return goodses;
	}
	

	@Override
	public Goods getGoodsInfoByID(int id) {
		return dao.getGoodsInfoByID(id);
	}
	
	@Override
	public List<SuperType> getSuperTypeAll() {
		return dao.getSuperTypeAll();
	}

	@Override
	public List<SubType> getSubTypeBySuperID(int id) {
		return dao.getSubTypeBySuperID(id);
	}

	@Override
	public List<Goods> getHotGoods(int top) {
		return dao.getHotGoods(top);
	}

	@Override
	public List<Goods> getRelatedGoods(int top, int id) {
		return dao.getRelatedGoods(top,id);
	}
	
	@Override
	public List<Goods> getGoodsByName(String name,int start,int end) {
		return dao.getGoodsByName(name, start, end);
	}

	@Override
	public List<SubType> getSubTypeAll() {
		return dao.getSubTypeAll();
	}
	
	@Override
	public SubType getSubTypeById(int id) {
		return dao.getSubTypeById(id);
	}

	@Override
	public SuperType getSuperTypeById() {
		return dao.getSuperTypeById();
	}
	
	@Override
	public SuperType getSuperTypeById(int id) {
		return dao.getSuperTypeById(id);
	}

	@Override
	public boolean updateGoodsByID(int id) {
		return dao.updateGoodsByID(id);
	}

	@Override
	public boolean insertGoodsInfo(String _sql, Goods goods) {
		return dao.insertGoodsInfo(_sql, goods);
	}

	@Override
	public boolean updateGoodsInfo(String _sql, Goods goods) {
		return dao.updateGoodsInfo(_sql, goods);
	}

	@Override
	public boolean updateSuperTypeInfo(SuperType superType) {
		return dao.updateSuperTypeInfo(superType);
	}

	@Override
	public boolean updateSubTypeInfo(SubType subType) {
		return dao.updateSubTypeInfo(subType);
	}

	@Override
	public boolean insertSuperTypeInfo(SuperType superType) {
		return dao.insertSuperTypeInfo(superType);
	}

	@Override
	public boolean insertSubTypeInfo(SubType subType) {
		return dao.insertSubTypeInfo(subType);
	}

}
