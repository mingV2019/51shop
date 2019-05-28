<%@page import="com.bean.SuperType"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:useBean id="dao" scope="page" class="com.service.impl.GoodsServiceDaoImpl"></jsp:useBean>
<%
	List<SuperType> superTypes = dao.getSuperTypeAll();
%>
<html>
<head>
<title>51商城――后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script src="<%=basePath%>manager/JS/check.js"></script>
</head>
<body>
	<jsp:include page="/manager/banner.jsp" />
	<table width="1280" height="288" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		<tr>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10" height="38" align="right">&nbsp;</td>
						<td colspan="3" class="tableBorder_B_dashed">
							<a href="<%=basePath%>manager/super_add.jsp"> 
								<img src="<%=basePath%>images/manage_ico1.GIF" width="11" height="13">&nbsp;[添加大分类信息]
							</a>
						</td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td height="10" colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="29" align="right">&nbsp;</td>
						<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_left.GIF">&nbsp;</td>
						<td width="1089" align="center" background="<%=basePath%>manager/images/manage_leftTitle_mid.GIF" class="word_white">
							<b>大分类信息列表</b>
						</td>
						<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_right.GIF">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table> <!---->
				<table width="92%" height="192" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<table width="100%" height="14" border="0" cellpadding="0" cellspacing="0">
							</table>
							<table width="100%" height="48" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#E6E6E6">
								<tr bgcolor="#eeeeee">
									<td height="20" align="center">大分类信息ID</td>
									<td height="20" align="center">大分类信息名称</td>
									<td width="8%" align="center">修改操作</td>
								</tr>
								<%for(SuperType superType : superTypes) {%>
								<tr style="padding:5px;">
									<td height="20" align="center"><%=superType.getSuperId()%></td>
									<td height="20" align="center"><%=superType.getTypeName()%></td>
									<td height="20" align="center"><a href="<%=basePath%>manager/super_edit.jsp?superId=<%=superType.getSuperId()%>">修改</a></td>
								</tr>
								<%} %>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td width="182" valign="top">
				<table width="100%" height="431" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="199" valign="top" bgcolor="#FFFFFF">
							<jsp:include page="/manager/navigation.jsp" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/manager/copyright.jsp" />
</body>
</html>
