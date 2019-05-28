package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bean.DetailOrder;
import com.bean.Goods;
import com.bean.Member;
import com.bean.ShopOrder;
import com.dao.GoodsDao;
import com.dao.MemberDao;
import com.dao.OrderDao;
import com.utils.DBConn;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean deleteShopOrders(int ids[]) {
		String sql1 = "delete from detailOrder where orderId = ?";
		String sql2 = "delete from shopOrder where orderId = ?";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql1);
			for(int id : ids) {
				prst.setInt(1, id);
				prst.executeUpdate();
			}
			prst = conn.prepareStatement(sql2);
			for(int id : ids) {
				prst.setInt(1, id);
				prst.executeUpdate();
			}
			check = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst!=null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}
	
	@Override
	public boolean insertNewOrder(ShopOrder shopOrder) {
		String sql = "insert into shopOrder(orderId,bnumber,total,receiveName,addresses,tel,remark,memberId) values(?,?,?,?,?,?,?,?)";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, shopOrder.getOrderId());
			prst.setInt(2, shopOrder.getBnumber());
			prst.setInt(3, shopOrder.getTotal());
			prst.setString(4, shopOrder.getReceiveName());
			prst.setString(5, shopOrder.getAddresses());
			prst.setString(6, shopOrder.getTel());
			prst.setString(7, shopOrder.getRemark());
			prst.setInt(8, shopOrder.getMember().getMemberId());
			prst.executeUpdate();
			check = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst!=null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}

	@Override
	public boolean insertNewDetailOrder(DetailOrder detailOrder) {
		String sql = "insert into detailOrder(orderId,goodsId,price,number,total) values(?,?,?,?,?)";
		boolean check = false;
		Connection conn = null;
		PreparedStatement prst = null;
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, detailOrder.getShopOrder().getOrderId());
			prst.setInt(2, detailOrder.getGoods().getGoodsId());
			prst.setInt(3, detailOrder.getPrice());
			prst.setInt(4, detailOrder.getNumber());
			prst.setInt(5, detailOrder.getTotal());
			prst.executeUpdate();
			check = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prst!=null) prst.close();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}
	
	@Override
	public int getOrderCount() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from shopOrder";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
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
	public int getOrderCount(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from shopOrder where orderId = ?";
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
	public int getShopOrderByName(String name, int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from shopOrder where receiveName like ? and memberId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, "%" + name + "%");
			prst.setInt(2, id);
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
	public int getShopOrderByName(String name) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from shopOrder where receiveName like ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, "%" + name + "%");
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
	public int getDetailOrderCount(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from detailOrder where orderId = ?";
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
	public ShopOrder getShopOrderByID(int id) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		ShopOrder shopOrder = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MemberDao dao = new MemberDaoImpl();
		String sql = "select orderId,bnumber,total,receiveName,addresses,tel,orderDate,remark,memberId from shopOrder where orderId = ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			if(rs.next()) {
				shopOrder = new ShopOrder(rs.getInt("orderId"), 
						rs.getInt("bnumber"), 
						rs.getInt("total"),
						rs.getString("receiveName"), 
						rs.getString("addresses"), 
						rs.getString("tel"), 
						sdf.format(rs.getDate("orderDate")), 
						rs.getString("remark"), 
						dao.getMemberByID(rs.getInt("memberId")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prst!=null) prst.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return shopOrder;
	}
	
	@Override
	public List<ShopOrder> getShopOrderByName(String name, int id, int start, int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ShopOrder> shopOrders = new ArrayList<ShopOrder>();
		MemberDao dao = new MemberDaoImpl();
		String sql = "select orderId,bnumber,total,receiveName,addresses,tel,orderDate,remark,memberId FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY orderId ASC) AS ROWNUM,* FROM shopOrder where receiveName like ? and memberId = ?)t " +
				"WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, "%" + name + "%");
			prst.setInt(2, id);
			prst.setInt(3, start);
			prst.setInt(4, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				ShopOrder shopOrder =  new ShopOrder(rs.getInt("orderId"), rs.getInt("bnumber"),rs.getInt("total"),
				rs.getString("receiveName"), rs.getString("addresses"), rs.getString("tel"), sdf.format(rs.getDate("orderDate")), 
				rs.getString("remark"), dao.getMemberByID(rs.getInt("memberId")));
				shopOrders.add(shopOrder);
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
		return shopOrders;
	}

	@Override
	public List<ShopOrder> getShopOrderByName(String name, int start, int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ShopOrder> shopOrders = new ArrayList<ShopOrder>();
		MemberDao dao = new MemberDaoImpl();
		String sql = "select orderId,bnumber,total,receiveName,addresses,tel,orderDate,remark,memberId FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY orderId ASC) AS ROWNUM,* FROM shopOrder where receiveName like ?)t " +
				"WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setString(1, "%" + name + "%");
			prst.setInt(2, start);
			prst.setInt(3, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				ShopOrder shopOrder =  new ShopOrder(rs.getInt("orderId"), rs.getInt("bnumber"),rs.getInt("total"),
				rs.getString("receiveName"), rs.getString("addresses"), rs.getString("tel"), sdf.format(rs.getDate("orderDate")), 
				rs.getString("remark"), dao.getMemberByID(rs.getInt("memberId")));
				shopOrders.add(shopOrder);
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
		return shopOrders;
	}
	
	@Override
	public List<DetailOrder> getDetailOrderPage(int id, int start, int end) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		List<DetailOrder> detailOrders = new ArrayList<DetailOrder>();
		GoodsDao dao = new GoodsDaoImpl();
		String sql = "select detailOrderId,orderId,goodsId,price,number,total FROM " +
				"(SELECT ROW_NUMBER() OVER ( ORDER BY detailOrderId ASC) AS ROWNUM,* FROM detailOrder where orderId = ?)t " +
				"WHERE t.ROWNUM > ? AND t.ROWNUM <= ?";
		try {
			conn = DBConn.getConn();
			prst = conn.prepareStatement(sql);
			prst.setInt(1, id);
			prst.setInt(2, start);
			prst.setInt(3, end);
			rs = prst.executeQuery();
			while(rs.next()) {
				DetailOrder detailOrder = new DetailOrder(rs.getInt("detailOrderId"), 
														getShopOrderByID(rs.getInt("orderId")), 
														dao.getGoodsInfoByID(rs.getInt("goodsId")), 
														rs.getInt("price"), 
														rs.getInt("number"),
														rs.getInt("total"));
				detailOrders.add(detailOrder);
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
		return detailOrders;
	}
}
