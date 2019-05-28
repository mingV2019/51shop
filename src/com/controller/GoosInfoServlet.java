package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bean.Goods;
import com.bean.PageInfo;
import com.bean.SubType;
import com.bean.SuperType;

import com.service.GoodsServiceDao;
import com.service.impl.GoodsServiceDaoImpl;
import com.utils.ChStr;
import com.utils.variable;

public class GoosInfoServlet extends HttpServlet {
	
	GoodsServiceDao dao = new GoodsServiceDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Goods> goodses = new ArrayList<Goods>();
		List<SubType> subTypes = new ArrayList<SubType>();
		String action = request.getParameter("action");
		if("sort".equals(action)) {
			int superId = Integer.parseInt(request.getParameter("superId"));
			String type = request.getParameter("type");
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountBySuperID(superId),1);
			subTypes = dao.getSubTypeBySuperID(superId);
			goodses = dao.getGoodsBySuperID(superId, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("superId", request.getParameter("superId"));
			request.setAttribute("subTypes", subTypes);
			request.setAttribute("action", action);
			request.setAttribute("type", type);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("detailSort".equals(action)) {
			int subId = Integer.parseInt(request.getParameter("subId"));
			int superId = Integer.parseInt(request.getParameter("superId"));
			String type = request.getParameter("type");
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByID(subId),1);
			goodses = dao.getGoodsBySubID(subId, pageInfo.getPageStart(), pageInfo.getPageEnd());
			subTypes = dao.getSubTypeBySuperID(superId);
			request.setAttribute("subId", request.getParameter("subId"));
			request.setAttribute("superId", request.getParameter("superId"));
			request.setAttribute("subTypes", subTypes);
			request.setAttribute("action", action);
			request.setAttribute("type", type);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("search".equals(action)) {
			String searchword = request.getParameter("searchword");
			System.out.println(searchword);
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByName(searchword),1);
			goodses = dao.getGoodsByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("searchword", searchword);
			request.setAttribute("action", action);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if("showGoodsAll".equals(action)) {
			String searchword = request.getParameter("searchword");
			searchword = searchword==null?"":searchword;
			PageInfo pageInfo = new PageInfo(dao.getGoodsCountByName(searchword), 1);
			goodses = dao.getGoodsByName(searchword, pageInfo.getPageStart(), pageInfo.getPageEnd());
			request.setAttribute("action", action);
			request.setAttribute("searchword", searchword);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("goodses",	goodses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/index.jsp");
			dispatcher.forward(request, response);
		}else if("insertGoodsInfo".equals(action)) {
			fileUpload(request, response);
			response.sendRedirect("goodsinfoservlet?action=showGoodsAll");
		} else if("updateGoodsInfo".equals(action)) {
			fileUpload(request, response);
			response.sendRedirect("goodsinfoservlet?action=showGoodsAll");
		}else if("insertSuperType".equals(action)) {
			String typeName = request.getParameter("typeName");
			SuperType superType = new SuperType(0, typeName);
			dao.insertSuperTypeInfo(superType);
			response.sendRedirect("manager/superType.jsp");
		}else if("editSuperType".equals(action)) {
			String typeName = request.getParameter("typeName");
			int superId = Integer.parseInt(request.getParameter("superId"));
			SuperType superType = new SuperType(superId, typeName);
			dao.updateSuperTypeInfo(superType);
			response.sendRedirect("manager/superType.jsp");
		}else if("insertSubType".equals(action)) {
			String typeName = request.getParameter("typeName");
			int superId = Integer.parseInt(request.getParameter("superInfo"));
			SubType subType = new SubType(0, typeName, dao.getSuperTypeById(superId));
			dao.insertSubTypeInfo(subType);
			response.sendRedirect("manager/subType.jsp");
		}else if("editSubType".equals(action)) {
			int subId = Integer.parseInt(request.getParameter("subId"));
			String typeName = request.getParameter("typeName");
			int superId = Integer.parseInt(request.getParameter("superInfo"));
			SubType subType = new SubType(subId, typeName, dao.getSuperTypeById(superId));
			dao.updateSubTypeInfo(subType);
			response.sendRedirect("manager/subType.jsp");
		}
	}
	public void fileUpload(HttpServletRequest request, HttpServletResponse response) {
		request.getParameter("UTF-8");
		String savePath = this.getServletContext().getRealPath("/images/goods");
		File file = new File(savePath);
		if(!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + " 目录不存在，需要创建");
			//创建目录
			file.mkdir();
		}
		
		//消息提示
		String message = "";
		try {
			//使用Apache文件上传组件处理文件上传步骤：
			
			//1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			
			//3.判断提交上来的数据是否是上传表单的数据
			//按照传统方式获取数据
			if(!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			
			//4.使用ServletFileUpload解析器解析上传数据，解析结果返回一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			Goods goods = new Goods();
			String sql = "";
			int nowPrice = 0;
			int price = 0;
			for(FileItem item : list) {
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()) {
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					//添加数据项
					if("goodsName".equals(name)) goods.setGoodsName(value);
					if("supertype".equals(name)) continue;
					if("subtype".equals(name)) goods.setSubType(dao.getSubTypeById(Integer.parseInt(value)));
					if("goodsType".equals(name)) sql = value;
					if("introduce".equals(name)) goods.setIntroduce(value);
					if("nowPrice".equals(name)) nowPrice = Integer.parseInt(value);
					if("price".equals(name)) price = Integer.parseInt(value);
				}else{
					//如果fileitem中封装的是上传文件
					//得到上传文件名称
					String filename = item.getName();
					if(filename == null || filename.trim().equals("")) {
						continue;
					}
					//注意：不同浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如C:a\b\b\1.txt，而有些只是单纯的文件名，如1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf("."));
					goods.setPicture("images/goods/"+filename);
					//创建item中的上传文件的输入流
					InputStream in = item.getInputStream();
					//创建一个文件输出流 
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					//创建一个缓冲区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完的标识
					int len = 0;
					//循环输入流读入到缓冲区当中，(len=in.read(buffer))>0表示in里面还有数据
					while((len=in.read(buffer))>0) {
						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						out.write(buffer, 0, len);
					}
					//关闭输入流
					in.close();
					//关闭输出流
					out.close();
					//删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功!";
				}
			}
			//导入数据库
			if("sale".equals(sql)) {
				goods.setNowPrice(nowPrice);
				goods.setPrice(price);
			} else if("newsGoods".equals(sql)) {
				goods.setNowPrice(nowPrice);
				goods.setPrice(nowPrice);
			}
			dao.insertGoodsInfo(sql, goods);
		}catch(Exception e) {
			message = "文件上传失败!";
			e.printStackTrace();
		}
		System.out.println(message);
	}
}
