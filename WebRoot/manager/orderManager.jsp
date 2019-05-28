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
<html>
<head>
<title>51商城――后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script src="<%=basePath%>manager/JS/check.js"></script>
<script src="<%=basePath%>manager/onclock.JS"></script>
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
					<td width="1089" align="center" style="color:#000" class="word_white">
						
					</td>
					<td width="10">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				</table>
				<form action="<%=basePath%>orderservlet?action=delOrder&&pageThis=<%=pageInfo.getPageThis()%>&&searchword=<%=searchword%>" method="post" name="frm"> 
				<table width="100%" height="40"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#E6E6E6">
					<tr bgcolor="#eeeeee" align="center">
						<td class="text-center quantity">序号</td>
						<td class="text-center quantity">用户名</td>
						<td class="text-center quantity">订单号</td>
						<td class="text-center name">收件人姓名</td>
						<td class="text-center name">地址</td>
						<td class="text-center name">电话</td>
						<td class="text-center name">备注</td>
						<td class="text-center name">下单日期</td>
						<td class="text-center quantity">种类数量</td>
						<td class="text-center quantity">总计</td>
						<td class="text-center name">删除操作</td>
					</tr>
					<%
						int count = 0;
						for (ShopOrder shopOrder : shopOrders) {
					%>
					<tr bgcolor="#eeeeee" align="center">
						<td class="text-center quantity"><%=++count%></td>
						<td class="text-center name"><%=shopOrder.getMember().getUserName()%></td>
						<td class="text-center quantity"><%=shopOrder.getOrderId()%></td>
						<td class="text-center name"><%=shopOrder.getReceiveName()%></td>
						<td class="text-center name"><%=shopOrder.getAddresses()%></td>
						<td class="text-center name"><%=shopOrder.getTel()%></td>
						<td class="text-center name"><%=shopOrder.getRemark()%></td>
						<td class="text-center name"><%=shopOrder.getOrderDate()%></td>
						<td class="text-center quantity">
							<a href="<%=basePath%>orderservlet?action=showDetailOrderManager&&orderId=<%=shopOrder.getOrderId()%>">
								<%=shopOrder.getBnumber()%>
							</a>
						</td>
						<td class="text-center quantity"><%=shopOrder.getTotal()%></td>
						<td align="center"><input name="del" type="checkbox" class="noborder" value="<%=shopOrder.getOrderId()%>">删除</td>
					</tr>
					<%
						}
					%>
				</table>
				<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
                      <tr> 
                        <td width="73%" height="24">&nbsp;</td> 
                        <td width="27%" align="right"><input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(frm.del,frm.checkbox)"> 
                          [全选/反选] [<a style="color:red;cursor:hand;" onClick="checkdel(frm.del,frm)">删除</a>] 
                          <div id="ch"> 
                            <input name="del" type="checkbox" class="noborder" value="0"> 
                          </div></td> 
                        <!--层ch用于放置隐藏的checkbox控件，因为当表单中只是一个checkbox控件时，应用javascript获得其length属性值为undefine--> 
                        <script language="javascript">ch.style.display="none";</script> 
                      </tr> 
                  </table>
                  </form>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" height="30">当前页数：【<%=pageInfo.getPageThis()%>】&nbsp;&nbsp;
							<a href="<%=basePath%>pageservlet?pageThis=1&&action=shopOrderManager&&searchword=<%=searchword%>">第一页</a>
							<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() - 1%>&&action=shopOrderManager&&searchword=<%=searchword%>">上一页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() + 1%>&&action=shopOrderManager&&searchword=<%=searchword%>">下一页</a>
							<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=shopOrderManager&&searchword=<%=searchword%>">最后一页&nbsp;</a>
							&nbsp;&nbsp;共【<%=pageInfo.getPageTotal()%>】页
						</td>
					</tr>
				</table>
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

