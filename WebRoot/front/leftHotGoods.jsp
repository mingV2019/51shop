<%@page import="com.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="dao" scope="page" class="com.service.impl.GoodsServiceDaoImpl"/>
<%
	List<Goods> goodses = dao.getHotGoods(6);
%>
<div class="mr-sidebar mr-sidebar-left col-xs-12 col-sm-4 col-sm-pull-8 col-md-3 col-md-pull-9  hidden-sm hidden-xs">
	<div class="mr-module module " id="Mod157">
		<div class="module-inner">
			<h3 class="module-title ">
				<span>热门商品</span>
			</h3>
			<div class="module-ct">
				<div class="mrshop">
					<div class="container_oc">
						<div class="box_oc">
							<div>
								<div class="box-product product-grid">
									<%
										for(Goods goods : goodses) {
									%>
									<div>
										<div class="image">
											<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>">
												<img src="<%=basePath%><%=goods.getPicture()%>" width="80px">
											</a>
										</div>
										<div class="name">
											<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>"> 
												<%=goods.getGoodsName()%>
											</a>
										</div>
										<div class="rating">
											<span class="fa fa-stack">
												<i class="fa fa-star fa-stack-2x"></i>
												<i class="fa fa-star-o fa-stack-2x"></i>
											</span> 
											<span class="fa fa-stack">
												<i class="fa fa-star fa-stack-2x"></i>
												<i class="fa fa-star-o fa-stack-2x"></i>
											</span> 
											<span class="fa fa-stack">
												<i class="fa fa-star fa-stack-2x"></i>
												<i class="fa fa-star-o fa-stack-2x"></i>
											</span>
											<span class="fa fa-stack">
												<i class="fa fa-star fa-stack-2x"></i>
												<i class="fa fa-star-o fa-stack-2x"></i>
											</span> 
											<span class="fa fa-stack">
												<i class="fa fa-star fa-stack-2x"></i>
												<i class="fa fa-star-o fa-stack-2x"></i>
											</span>
										</div>
										<div class="price">价格：<%=goods.getNowPrice()%>元</div>
									</div>
									<%} %>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>