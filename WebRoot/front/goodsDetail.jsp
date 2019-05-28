<%@page import="com.bean.Goods"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="dao" scope="page" class="com.service.impl.GoodsServiceDaoImpl"/>
<%int goodsId = Integer.parseInt(request.getParameter("goodsId"));
  Goods goods = dao.getGoodsInfoByID(goodsId);
  dao.updateGoodsByID(goodsId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>51商城</title>
<link rel="stylesheet" href="<%=basePath%>front/css/mr-01.css" type="text/css">

<script src="<%=basePath%>front/js/jsArr01.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/module.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/jsArr02.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/tab.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="/front/index-loginCon.jsp" />
	<!-- 网站头部 -->
	<jsp:include page="/front/common-header.jsp"/>
	<!-- //网站头部 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- 页面主体内容 -->
			<div id="mr-content"
				class="mr-content col-xs-12 col-sm-12 col-md-9 col-md-push-3">
				<div id="mrshop" class="mrshop common-home">
					<div class="container_oc">
						<div class="row">
							<div id="content_oc" class="col-sm-12 view-product">
								<!-- 显示商品详细信息 -->
								<div class="row">
									<div class="col-xs-12 col-md-4 col-sm-4">
										<ul class="thumbnails" style="list-style: none">
											<li><a class="thumbnail" href="#"> <img src="<%=basePath%><%=goods.getPicture()%>"></a></li>
										</ul>
									</div>
									<div class="col-xs-12 col-md-8 col-sm-8">
										<div style="margin-left: 30px; margin-top: 20px">
											<h1 class="product-title"><%=goods.getGoodsName()%></h1>
											<h2>价格：<%=goods.getNowPrice()%>元</h2>
											<div class="rating"><p>商城活动：全场满99包邮</p></div>
											<div id="product"><hr>
												<div class="form-group">
													<label class="control-label" for="shuliang"> 数量 </label>
													<input type="number" name="quantity" value="1" size="2" id="quantity" class="form-control"> <br>
													<input type="hidden" name="goodsId" id="goodsId" value="<%=goods.getGoodsId()%>"/>
													<div class="btn-group">
														<button type="button" onclick="addCart();" class="btn btn-primary btn-primary">
															<i class="fa fa-shopping-cart"></i> 添加到购物车</button>
														<button type="button" id="button-wishlist" data-toggle="tooltip" class="btn"
														 title="收藏" data-original-title="收藏"> <i class="fa fa-heart"></i></button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-12 description_oc clearfix">
										<ul class="nav nav-tabs htabs">
											<li class="active" style="width: 150px">
												<a href="#tab-description" data-toggle="tab" aria-expanded="true">商品描述</a>
											</li>
										</ul>
										<div class="tab-content" style="border: 1px solid #eee; overflow: hidden;">
											<div class="tab-pane active" id="tab-description"><%=goods.getIntroduce()%></div>
										</div>
									</div>
								</div>
								<!-- //显示商品详细信息 -->
								<!-- 显示相关商品 -->
								<div class="mr-module related-products">
									<h3 class="module-title ">相关商品</h3>
									<!-- 显示底部相关商品 -->
									<jsp:include page="/front/relatedGoods.jsp">
										<jsp:param name="subId" value="<%=goods.getSubType().getSubId()%>" />
										<jsp:param name="goodsId" value="<%=goods.getGoodsId()%>" />
									</jsp:include>
									<!-- // 显示底部相关商品 -->
								</div>
								<!-- //显示相关商品 -->
								<!-- //根据商品ID获取并显示商品信息 -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- //页面主体内容 -->
			<!-- 显示左侧热门商品 -->
			<jsp:include page="/front/leftHotGoods.jsp"/>
			<!-- // 显示左侧热门商品 -->

		</div>
	</div>
	<!-- 版权栏 -->
	<jsp:include page="/front/common-footer.jsp"/>
	<!-- //版权栏 -->
	<script src="/front/js/jquery.1.3.2.js" type="text/javascript"></script>
	<script type="text/javascript">
	function addCart() {
		var num = document.getElementById("quantity").value;//获取输入的商品数量
		var id = document.getElementById("goodsId").value;//获取商品ID
		//验证输入的数量是否合法
			if (num < 1) {//如果输入的数量不合法
				alert('数量不能小于1！');
				return;
			}
			window.location.href = "<%=basePath%>cartservlet?action=addCart&&goodsId="+id+"&&quantity=1&&url=/front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>";//调用添加购物车页面，实现将该商品添加到购物车
			return true;
		}
	</script>
</body>
</html>
