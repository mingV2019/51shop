<%@page import="com.bean.Manager"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="199" height="431" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="18" colspan="3"><img src="<%=basePath%>manager/images/manage_rightTitle.GIF" width="189" height="37" /></td>
	</tr>
	<tr>
		<td height="9" colspan="3" background="<%=basePath%>manager/images/manage_rightKtop.GIF"></td>
	</tr>

	<tr>
		<td colspan="3" align="center" valign="top" background="<%=basePath%>manager/images/manage_rightKmid.GIF">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="20%" height="50" align="center" class="tableBorder_B_dashed">
						<img src="<%=basePath%>manager/images/manage_listIco.GIF" width="10" height="11" />
					</td>
					<td width="80%" class="tableBorder_B_dashed">
						<a href="<%=basePath%>goodsinfoservlet?action=showGoodsAll">商品管理<br>Goods Manage</a>
					</td>
				</tr>
				<tr>
					<td height="50" align="center" class="tableBorder_B_dashed">
						<img src="<%=basePath%>manager/images/manage_listIco.GIF" width="10" height="11" />
					</td>
					<td class="tableBorder_B_dashed">
						<a href="#">会员管理<br>Member</a>
					</td>
				</tr>
				<tr>
					<td height="50" align="center" class="tableBorder_B_dashed">
						<img src="<%=basePath%>manager/images/manage_listIco.GIF" width="10" height="11" />
					</td>
					<td class="tableBorder_B_dashed">
						<a href="<%=basePath%>orderservlet?action=showShopOrderManager">订单管理<br>Order Manage</a>
					</td>
				</tr>
				<tr>
					<td height="50" align="center" class="tableBorder_B_dashed">
						<img src="<%=basePath%>manager/images/manage_listIco.GIF" width="10" height="11" />
					</td>
					<td class="tableBorder_B_dashed">
						<a href="<%=basePath%>managerservlet?action=destroy">退出后台<br>Exit Background </a>
					</td>
				</tr>
				<tr>
					<td height="50" align="center" class="tableBorder_B_dashed">
						<img src="<%=basePath%>manager/images/manage_listIco.GIF" width="10" height="11" />
					</td>
					<td class="tableBorder_B_dashed">
						<a href="<%=basePath%>index.jsp">主页<br>index</a>
					</td>
				</tr>
				<tr>
					<td height="9" colspan="2" align="center">
						<%Manager manager = (Manager)request.getSession().getAttribute("managerSession");
					  	if(manager!=null) out.println("您好！"+manager.getManagerName());
						%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="9" colspan="3" align="center" background="<%=basePath%>manager/images/manage_rightKbottom.GIF"></td>
	</tr>
</table>
