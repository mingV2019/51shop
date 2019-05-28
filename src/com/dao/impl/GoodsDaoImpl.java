package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bean.Goods;
import com.bean.SubType;
import com.bean.SuperType;
import com.dao.GoodsDao;
import com.utils.DBConn;
import com.utils.variable;

public class GoodsDaoImpl implements GoodsDao {

	//
	@Override
	public List<Goods> getGoodsBySubID(int id,int start ,int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select goodsId,subId,goodsName,introduce,nowprice,picture,InTime,hit FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY goodsId ASC) AS ROWNUM,* FROM goods where subId = ?)t " +
				"WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			prst.setInt(2, start);
			prst.setInt(3, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	}
	

	@Override
	public List<Goods> getGoodsBySuperID(int id,int start ,int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select goodsId,subId,goodsName,introduce,nowprice,picture,InTime,hit FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY goodsId ASC) AS ROWNUM,* FROM goods where subId in " +
				"(select subId FROM subType where superId = ?))t " +
				"WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			prst.setInt(2, start);
			prst.setInt(3, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	}
	
	@Override
	public int getGoodsCountByName(String name) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from goods where goodsName like ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1,"%" + name + "%");
			rs = prst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return count;
	}
	
	@Override
	public int getGoodsCountByID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from goods where subId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return count;
	}
	

	@Override
	public int getGoodsCountBySuperID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) FROM goods where subId in (select subId FROM subType where superId = ?)";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return count;
	}
	
	@Override
	public List<Goods> getNewGoodsAll() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select top " + variable.GOODSNUMBERTOP + " goodsId,subId,goodsName,introduce,nowprice,picture,InTime,newsGoods,hit from goods where newsGoods = 1";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	}

	@Override
	public List<Goods> getDiscountAll() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select top " + variable.GOODSNUMBERTOP + " goodsId,subId,goodsName,introduce,price,nowprice,picture,InTime,sale,hit from goods where sale = 1";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setPrice(rs.getInt("price"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setSale(rs.getInt("sale"));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	}
	
	@Override
	public List<Goods> getGoodsByName(String name,int start ,int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select goodsId,subId,goodsName,introduce,price,nowprice,picture,InTime,hit FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY goodsId ASC) AS ROWNUM,* FROM goods " +
				"where goodsName like ?)t WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, "%"+name+"%");
			prst.setInt(2, start);
			prst.setInt(3, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setPrice(rs.getInt("price"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	}
	
	@Override
	public List<Goods> getHotGoods(int top) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select top(?) goodsId,subId,goodsName,introduce,nowprice,picture,InTime,hit from goods order by hit desc";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, top);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			} 
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	
	}

	@Override
	public List<Goods> getRelatedGoods(int top,int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Goods> goodses = new ArrayList<Goods>();
		String sql = "select top(?) goodsId,subId,goodsName,introduce,nowprice,picture,InTime,hit  FROM goods where subId  = ? order by hit desc";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, top);
			prst.setInt(2, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
				goodses.add(goods);
			} 
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goodses;
	
	}

	@Override
	public Goods getGoodsInfoByID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Goods goods = null;
		String sql = "select goodsId,subId,goodsName,introduce,nowprice,picture,InTime,hit FROM goods where goodsId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setSubType(getSubTypeById(rs.getInt("subId")));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setNowPrice(rs.getInt("nowprice"));
				goods.setPicture(rs.getString("picture"));
				goods.setInTime(sdf.format(rs.getTimestamp("InTime")));
				goods.setHit(rs.getInt("hit"));
			} 
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return goods;
	
	}

	@Override
	public SuperType getSuperTypeById() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select top(1) superId,typeName from superType";
		SuperType superType = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				superType = new SuperType(rs.getInt("superId"), rs.getString("typeName"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return superType;
	}
	
	@Override
	public SubType getSubTypeById(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select subId,typeName,superId from subType where subId = ?";
		SubType subType = new SubType();
		subType.setSubId(id);
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				subType.setTypeName(rs.getString("typeName"));
				subType.setSuperType(getSuperTypeById(rs.getInt("superId")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return subType;
	}
	

	@Override
	public List<SubType> getSubTypeAll() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select subId,typeName,superId from subType";
		List<SubType> subTypes = new ArrayList<SubType>();
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				SubType subType = new SubType(rs.getInt("subId"), rs.getString("typeName"), getSuperTypeById(rs.getInt("superId")));
				subTypes.add(subType);
			}
			rs.close();
			prst.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return subTypes;
	}
	
	@Override
	public SuperType getSuperTypeById(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select superId,typeName from superType where superId = ?";
		SuperType superType = new SuperType();
		superType.setSuperId(id);
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) superType.setTypeName(rs.getString("typeName"));
			rs.close();
			prst.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return superType;
	}

	@Override
	public List<SuperType> getSuperTypeAll() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		List<SuperType> supers = new ArrayList<SuperType>();
		String sql = "select superId,typeName from superType";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				SuperType superType = new SuperType(rs.getInt("superId"), rs.getString("typeName"));
				supers.add(superType);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return supers;
	}

	@Override
	public List<SubType> getSubTypeBySuperID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		List<SubType> supers = new ArrayList<SubType>();
		String sql = "select subId,typeName,superId from subType where superId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			while(rs.next()) {
				SubType subType = new SubType(rs.getInt("subId"), rs.getString("typeName"), getSuperTypeById(rs.getInt("superId")));
				supers.add(subType);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(prst!=null) prst.close();
				} catch(Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return supers;
	}


	@Override
	public boolean updateGoodsByID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		boolean check = false;
		String sql = "update goods set hit = hit + 1 where goodsId = ?";
		try {
				conn = DBConn.getConn();
				prst = conn.prepareStatement(sql);
				prst.setInt(1, id);
				prst.executeUpdate();
				check = true;
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn!=null) conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return check;
	}


	@Override
	public boolean insertGoodsInfo(String _sql,Goods goods) {
		String sql = "insert into goods(subId,goodsName,introduce,price,nowprice,picture," +_sql + ",hit) values(?,?,?,?,?,?,?,?)";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, goods.getSubType().getSubId());
			prst.setString(2, goods.getGoodsName());
			prst.setString(3, goods.getIntroduce());
			prst.setInt(4, goods.getPrice());
			prst.setInt(5, goods.getNowPrice());
			prst.setString(6, goods.getPicture());
			prst.setInt(7, 1);
			prst.setInt(8, goods.getHit());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	}

	@Override
	public boolean updateGoodsInfo(String _sql, Goods goods) {
		String sql = "update goods set subId=?,goodsName=?,introduce=?,price=?,nowprice=?,picture=?,InTime=?,sale=?,newsGoods=?,hit=? where goodsId = ?";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, goods.getSubType().getSubId());
			prst.setString(2, goods.getGoodsName());
			prst.setString(3, goods.getIntroduce());
			prst.setInt(4, goods.getPrice());
			prst.setInt(5, goods.getNowPrice());
			prst.setString(6, goods.getPicture());
			prst.setString(7, goods.getInTime());
			if("sale".equals(_sql)) {
				prst.setInt(8, 1);
				prst.setInt(9, 0);
			}
			else if("newsGoods".equals(_sql)) {
				prst.setInt(8, 0);
				prst.setInt(9, 1);
			}
			prst.setInt(10, goods.getHit());
			prst.setInt(11, goods.getGoodsId());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	}


	@Override
	public boolean updateSuperTypeInfo(SuperType superType) {
		String sql = "update superType set typeName = ? where superId = ?";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, superType.getTypeName());
			prst.setInt(2, superType.getSuperId());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	
	}


	@Override
	public boolean updateSubTypeInfo(SubType subType) {
		String sql = "update subType set typeName = ?,superId = ? where subId = ?";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, subType.getTypeName());
			prst.setInt(2, subType.getSuperType().getSuperId());
			prst.setInt(3, subType.getSubId());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	}


	@Override
	public boolean insertSuperTypeInfo(SuperType superType) {
		String sql = "insert into superType(typeName) values(?)";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, superType.getTypeName());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	
	}


	@Override
	public boolean insertSubTypeInfo(SubType subType) {
		String sql = "insert into subType(typeName,superId) values(?,?)";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, subType.getTypeName());
			prst.setInt(2, subType.getSuperType().getSuperId());
			prst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst != null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return check;
	}
}
