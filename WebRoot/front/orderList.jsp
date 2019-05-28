<%@page import="com.bean.PageInfo"%>
<%@page import="com.bean.ShopOrder"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	String searchword = (String)request.getAttribute("searchword");
	List<ShopOrder> shopOrders = (List<ShopOrder>) request.getAttribute("shopOrders");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>我的购物车-51商城</title>
<link rel="stylesheet" href="<%=basePath%>front/css/mr-01.css"
	type="text/css">

<script src="<%=basePath%>front/js/jsArr01.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/module.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/jsArr02.js" type="text/javascript"></script>
<script src="<%=basePath%>front/js/tab.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="/front/order-search.jsp" />
	<!-- 网站头部 -->
	<jsp:include page="/front/common-header.jsp"></jsp:include>
	<!-- //网站头部 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- 显示订单列表 -->
			<div id="mr-content" class="mr-content col-xs-12">
				<div id="mrshop" class="mrshop common-home">
					<div class="container_oc">
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<h1>我的订单</h1>
								<div class="table-responsive cart-info">
									<table class="table table-bordered">
										<thead>
											<tr>
												<td class="text-center quantity">序号</td>
												<td class="text-center quantity">订单号</td>
												<td class="text-center name">收件人姓名</td>
												<td class="text-center name">地址</td>
												<td class="text-center name">电话</td>
												<td class="text-center name">备注</td>
												<td class="text-center name">下单日期</td>
												<td class="text-center quantity">种类数量</td>
												<td class="text-center quantity">总计</td>
											</tr>
										</thead>
										<%
											int count = 0;
											for (ShopOrder shopOrder : shopOrders) {
										%>
										<tbody>
											<tr>
												<td class="text-center quantity"><%=++count%></td>
												<td class="text-center quantity"><%=shopOrder.getOrderId()%></td>
												<td class="text-center name"><%=shopOrder.getReceiveName()%></td>
												<td class="text-center name"><%=shopOrder.getAddresses()%></td>
												<td class="text-center name"><%=shopOrder.getTel()%></td>
												<td class="text-center name"><%=shopOrder.getRemark()%></td>
												<td class="text-center name"><%=shopOrder.getOrderDate()%></td>
												<td class="text-center quantity">
													<a href="<%=basePath%>orderservlet?action=showDetailOrder&&orderId=<%=shopOrder.getOrderId()%>">
														<%=shopOrder.getBnumber()%>
													</a>
												</td>
												<td class="text-center quantity"><%=shopOrder.getTotal()%></td>
											</tr>
										</tbody>
										<%
											}
										%>
									</table>
								</div>
							</div>
							<div style="text-align:center;">
								<div class="row pagination" style="display:inlineblock;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="30">当前页数：【<%=pageInfo.getPageThis()%>】&nbsp;&nbsp;
												<a href="<%=basePath%>pageservlet?pageThis=1&&action=shopOrder&&searchword=<%=searchword%>">第一页</a>
												<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() - 1%>&&action=shopOrder&&searchword=<%=searchword%>">上一页</a>
												&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() + 1%>&&action=shopOrder&&searchword=<%=searchword%>">下一页</a>
												<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=shopOrder&&searchword=<%=searchword%>">最后一页&nbsp;</a>
												&nbsp;&nbsp;共【<%=pageInfo.getPageTotal()%>】页
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<br /> <br />
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<br /> <br />
								<div class="buttons">
									<div class="pull-right">
										<a href="<%=basePath%>front/index.jsp" class="tigger btn btn-primary btn-primary">继续购物</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- //显示订单列表 -->
		</div>
	</div>
	<!-- 版权栏 -->
	<jsp:include page="/front/common-footer.jsp" />
	<!-- //版权栏 -->
</body>
</html>
