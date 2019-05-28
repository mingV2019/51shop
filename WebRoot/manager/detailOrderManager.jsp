<%@page import="com.bean.PageInfo"%>
<%@page import="com.bean.DetailOrder"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	int orderId = (Integer)request.getAttribute("orderId");
	List<DetailOrder> detailOrders = (List<DetailOrder>)request.getAttribute("detailOrders");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
%>
<html>
<head>
<title>51商城――后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script src="<%=basePath%>manager/JS/jquery.min.js"></script>
</head>

<body>
	<jsp:include page="/manager/banner.jsp" />
	<table width="1280" height="288" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		<tr>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">&nbsp;</td>
					<td height="10" colspan="3">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<form action="<%=basePath%>orderservlet?action=showShopOrderManager" method="post" onsubmit="return search();">
					<td align="right">&nbsp;</td>
					<td height="10" colspan="3">
							<input type="text" placeholder="根据收件人查询" name="searchword" id="searchword" /> 
					</td>
					<td><input type="image" src="<%=basePath%>images/search.png" class="search_box_img" onFocus="this.blur()" /></td>
					</form>
				</tr>
				<tr>
					<td height="29" align="right">&nbsp;</td>
					<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_left.GIF">&nbsp;</td>
					<td width="1089" align="center" background="<%=basePath%>manager/images/manage_leftTitle_mid.GIF" class="word_white">
						<b>订单列表</b>
					</td>
					<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_right.GIF">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="29" align="right">&nbsp;</td>
					<td width="10">&nbsp;</td>
					<td width="1089" align="center" style="color:#000" class="word_white"></td>
					<td width="10">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				</table>
				<table width="96%" height="48" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
					<tr bgcolor="#eeeeee" align="center">
						<td class="text-center name">货物图片</td>
						<td class="text-center quantity">序号</td>
						<td class="text-center quantity">订单号</td>
						<td class="text-center name">货物名称</td>
						<td class="text-center quantity">货物单价</td>
						<td class="text-center quantity">货物数量</td>
						<td class="text-center quantity">总计</td>
					</tr>
					<%
						int count = 0;
						for (DetailOrder detailOrder : detailOrders) {
					%>
					<tr bgcolor="#eeeeee" align="center">
						<td class="text-center quantity">
							<img src="<%=basePath%><%=detailOrder.getGoods().getPicture()%>" 
							class="img-responsive" style="width: 60px"/>
						</td>
						<td class="text-center quantity"><%=++count%></td>
						<td class="text-center quantity"><%=detailOrder.getShopOrder().getOrderId()%></td>
						<td class="text-center name"><%=detailOrder.getGoods().getGoodsName()%></td>
						<td class="text-center name"><%=detailOrder.getPrice()%></td>
						<td class="text-center name"><%=detailOrder.getNumber()%></td>
						<td class="text-center name"><%=detailOrder.getTotal()%></td>
					</tr>
					<%
						}
					%>
				</table>
				<div style="text-align:center;">
				<div class="row pagination" style="display:inlineblock;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" height="30">当前页数：【<%=pageInfo.getPageThis()%>】&nbsp;&nbsp;
								<a href="<%=basePath%>pageservlet?pageThis=1&&action=detailOrderManager&&orderId=<%=orderId%>">第一页</a>
								<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() - 1%>&&action=detailOrderManager&&orderId=<%=orderId%>">上一页</a>
								&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() + 1%>&&action=detailOrderManager&&orderId=<%=orderId%>">下一页</a>
								<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=detailOrderManager&&orderId=<%=orderId%>">最后一页&nbsp;</a>
								&nbsp;&nbsp;共【<%=pageInfo.getPageTotal()%>】页
							</td>
						</tr>
					</table>
				</div>
			</div>
			</td>
			<td width="182" valign="top">
				<table width="100%" height="431" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="199" valign="top" bgcolor="#FFFFFF">
							<jsp:include page="/manager/navigation.jsp" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<jsp:include page="/manager/copyright.jsp" />
	<!-- 
	<script type="text/javascript">
		function search() {
			var z = /^[0-9]*$/;
			if (!z.test($('#orderId').val())) {
				alert('订单号为数字格式！');
				return false;
			}
		
			return true;
		}
	</script>
	 -->
</body>
</html>

