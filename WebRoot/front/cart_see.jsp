<%@page import="com.dao.impl.OrderDaoImpl"%>
<%@page import="com.dao.OrderDao"%>
<%@page import="com.bean.CartItem"%>
<%@page import="com.bean.Cart"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	OrderDao dao = new OrderDaoImpl();
	Cart cart = (Cart)request.getSession().getAttribute("cart");
	String message = cart==null||cart.getCount()==0?"购物车为空":"";
	int money = 0;
	if("".equals(message)) money=cart.getTotal();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%=basePath%>front/js/jBox/Skins2/Pink/jbox.css" />
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>我的购物车-51商城</title>
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
			<div id="mr-content" class="mr-content col-xs-12">
				<div id="mrshop" class="mrshop common-home">
					<div class="container_oc">
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<h1>我的购物车</h1>
								<!-- 显示购物车中的商品 -->
								<%if("".equals(message)==false) {
									out.println("<tr><td colspan='5' align='center'>"+message+"</tr></td>");
								}else {%>
								<div class="table-responsive cart-info">
									<form id="goodsform" name="goodsform" method="post">
									<table class="table table-bordered" id="tab" name="tab">
										<thead>
											<tr>
												<td class="text-center image">商品图片</td>
												<td class="text-left name">商品名称</td>
												<td class="text-left quantity">数量/件</td>
												<td class="text-right price">单价</td>
												<td class="text-right total">总计</td>
												<td class="text-right total">删除</td>
											</tr>
										</thead>
										<tbody>
										<!-- 遍历购物车中的商品并显示 -->
											<!-- 显示一条商品信息 -->
											<%int count=0;
												for(CartItem cartItem : cart.getCartItems()) {
											%>
											<tr>
												<td class="text-center image" width="20%">
													<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=cartItem.getGoods().getGoodsId()%>">
													<img width="80px" src="<%=basePath%><%=cartItem.getGoods().getPicture()%>"> </a>
												</td>
												<td class="text-left name">
													<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=cartItem.getGoods().getGoodsId()%>"> <%=cartItem.getGoods().getGoodsName()%></a>
												</td>
												<td class="text-left quantity">
													<input type="number" id="quantity<%=count%>" name="quantity<%=count%>" value="<%=cartItem.getQuantity()%>"/>
													<input type="hidden" id="goodsId<%=count%>" name="goodsId<%=count%>" value="<%=cartItem.getGoods().getGoodsId()%>"/>
												</td>
												<td class="text-right price"><%=cartItem.getGoods().getNowPrice()%>元</td>
												<td class="text-right total"><%=cartItem.getTotal()%>元</td>
												<td class="text-right name"><a href="<%=basePath%>cartservlet?action=deleteCart&&action&&goodsId=<%=cartItem.getGoods().getGoodsId()%>">删除</a></td>
											</tr>
											<%count++; }%>
											
											<!-- 显示一条商品信息 -->
											<!-- //遍历购物车中的商品并显示 -->
										</tbody>
									</table>
									</form>
								</div>
								<!-- //显示购物车中的商品 -->
								<!-- 显示总计金额  -->
								<div class="row cart-total">
									<div class="col-sm-4 col-sm-offset-8">
										<table class="table table-bordered">
											<tbody>
												<tr >
												<span>
													<strong>总计:</strong>
													<p><%=cart.getTotal()%>元</p>
												</span>
												</tr>
											</tbody>
										</table>
									
									</div>
								</div>
								<%} %>
								<!-- //显示总计金额  -->
							</div>
						</div>

						<!-- 填写物流信息 -->
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<h1>物流信息</h1>
								<!-- 填写物流信息的表单 -->
								<form action="<%=basePath%>orderservlet?action=addOrder" method="post" id="myform">
									<div class="table-responsive cart-info">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<td class="text-right" width="20%">收货人姓名：</td>
													<td class="text-left quantity">
														<div class="input-group btn-block" style="max-width: 400px;">
															<input type="text" id="recevieName" name="recevieName" size="10" class="form-control">
														</div>
													</td>
												</tr>
												<tr>
													<td class="text-right">收货人手机：</td>
													<td class="text-left quantity">
														<div class="input-group btn-block" style="max-width: 400px;">
															<input type="text" id="tel" name="tel" size="10" class="form-control">
														</div>
													</td>
												</tr>
												<tr>
													<td class="text-right">收货人地址：</td>
													<td class="text-left quantity">
														<div class="input-group btn-block" style="max-width: 400px;">
															<input type="text" id="address" name="address" size="1" class="form-control">
														</div>
													</td>
												</tr>
												<tr>
													<td class="text-right">备注：</td>
													<td class="text-left quantity">
														<div class="input-group btn-block" style="max-width: 400px;">
															<input type="text" name="remark" size="1" class="form-control">
															<input id="message" name="message" type="hidden" value="<%=message%>"/>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</form>
								<!-- //填写物流信息的表单 -->
							</div>
						</div>
						<!-- //填写物流信息 -->
						<br />
						<!-- 显示支付方式 -->
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<h1>支付方式</h1>
								<div class="table-responsive cart-info">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<td class="text-left"><img src="<%=basePath%>images/zhifubao.png" /></td>
											</tr>
										</tbody>
									</table>
								</div>
								<br /> <br />
								<div class="buttons">
									<div class="pull-left">
										<a href="<%=basePath%>front/index.jsp" class="btn btn-primary btn-default">继续购物</a>
									</div>
									<div class="pull-left">
										<a href="<%=basePath%>cartservlet?action=clearCart" class="btn btn-primary btn-default">清空购物车</a>
									</div>
									<div class="pull-left">
										<a href="javascript:saveCart();" class="btn btn-primary btn-default">保存购物车</a>
									</div>
									<div class="pull-right">
										<a href="javascript:zhifu();" class="tigger btn btn-primary btn-primary">结账</a>
									</div>
								</div>
							</div>
						</div>
						<!-- //显示支付方式 -->
					</div>
				</div>
			</div>
			<!-- //页面主体内容 -->
		</div>
	</div>
	<!-- 版权栏 -->
	<jsp:include page="common-footer.jsp"></jsp:include>
	<!-- //版权栏 -->

	<!-- 使用jBox插件实现一个支付对话框 -->
	<script type="text/javascript" src="<%=basePath%>front/js/jBox/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>front/js/jBox/jquery.jBox-2.3.min.js"></script>

	<script type="text/javascript">
		function zhifu() {
			
			var message = document.getElementById('message').value;
			if(message != "") {
				alert("请添加物品至购物车");
				return;
			}
			
			//验证收货人姓名
			if ($('#recevieName').val() == "") {
				alert('收货人姓名不能为空！');
				return;
			}
			//验证收货人手机
			if ($('#tel').val() == "") {
				alert('收货人手机不能为空！');
				return;
			}
			//验证手机号是否合法
			if (isNaN($('#tel').val())) {
				alert("手机号请输入数字");
				return;
			}
			//验证收货人地址
			if ($('#address').val() == "") {
				alert('收货人地址不能为空！');
				return;
			}
			//设置对话框中要显示的内容
			var html = '<div class="popup_cont">'
					+ '<p>扫一扫支付</p>'
					+ '<strong>￥<font id="show_money_info"><%=money%></font></strong>'
					+ '<div style="width: 256px; height: 250px; text-align: center; margin-left: auto; margin-right: auto;" >'
					+ '<image src="<%=basePath%>images/qr.png" width="256" height="256" /></div>'
					+ '</div><p style="text-align:center">支付二维码仅为测试用</p>';
			var content = {
				state1 : {
					content : html,
					buttons : {
						'取消' : 0,
						'支付' : 1
					},
					buttonsFocus : 0,
					submit : function(v, h, f) {
						if (v == 0) {//取消按钮的响应事件
							return true; //关闭窗口
						}
						if (v == 1) {//支付按钮的响应事件
							alert("订单生成，请记住您的订单号【<%=dao.getOrderCount()+1%>】");
							document.getElementById('myform').submit();//提交表单
							return true;
						}
						return false;
					}
				}
			};
			$.jBox.open(content, '支付', 400, 450);//打开支付窗口
		}
		
		function saveCart() {
			document.getElementById('goodsform').action="<%=basePath%>cartservlet?action=saveCart";
			document.getElementById('goodsform').submit();
		}
	</script>
	<!-- // 使用jBox插件实现一个支付对话框 -->
</body>
</html>
