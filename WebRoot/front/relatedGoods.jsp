<%@page import="com.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="dao" scope="page" class="com.service.impl.GoodsServiceDaoImpl"/>
<%
	int subId = Integer.parseInt(request.getParameter("subId"));
	String goodsId = request.getParameter("goodsId");
	List<Goods> goodses = dao.getRelatedGoods(6, subId);
	String url = "/front/goodsDetail.jsp?goodsId="+goodsId;
%>
<div class="row">
	<%for(Goods goods : goodses) { %>
	<div class="col-lg-2 col-md-3 col-sm-4 col-xs-12">
		<div class="product-grid transition">
			<div class="actions">
				<div class="image">
					<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>">
						<img src="<%=basePath%><%=goods.getPicture()%>">
					</a>
				</div>
				<div class="button-group">
					<div class="cart">
						<button class="btn btn-primary btn-primary" type="button"
							data-toggle="tooltip" title=""
							onclick='javascript:window.location.href="<%=basePath%>cartservlet?action=addCart&&goodsId=<%=goods.getGoodsId()%>&&quantity=1&&url=<%=url%>";'
							data-original-title="加入购物车">
							<i class="fa fa-shopping-cart"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="caption">
				<div class="name" style="height: 40px">
					<a style="width: 90%" href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>">
					<span style="color: #0885B1">名称:</span><%=goods.getGoodsName()%></a>
				</div>
				<p class="price" style="margin-top: 40px">
					<span class="price-tax">价格:<%=goods.getNowPrice()%> 元
					</span>
				</p>
			</div>
		</div>
	</div>
	<%} %>
</div>