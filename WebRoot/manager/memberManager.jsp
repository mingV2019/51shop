<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>51商城――后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script src="<%=basePath%>manager/JS/check.js"></script>
<script src="<%=basePath%>manager/onclock.JS"></script>
</head>

<body>
	<jsp:include page="/manager/banner.jsp" />
	<table width="1280" height="288" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		<tr>
			<td align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right">&nbsp;</td>
						<td height="10" colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="29" align="right">&nbsp;</td>
						<td width="10" background="images/manage_leftTitle_left.GIF">&nbsp;</td>
						<td width="1089" align="center" background="<%=basePath%>manager/images/manage_leftTitle_mid.GIF" class="word_white">
							<b>会员列表</b>
						</td>
						<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_right.GIF">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table> <!---->
				<table width="100%" height="14" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="13" align="center">&nbsp;</td>
					</tr>
				</table>
				<table width="96%" height="48" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
					<tr>
						<td width="20%" height="27" align="center">用户名</td>
						<td width="20%" align="center">真实姓名</td>
						<td width="15%" align="center">电话</td>
						<td width="20%" align="center">Email</td>
						<td width="15%" align="center">销费额</td>
						<td width="10%" align="center">操作</td>
						<%--<td width="11%" align="center">冻结/解冻</td> --%>
					</tr>
					<!-- 用户信息遍历开始 -->
					
					<tr style="padding:5px;">
						<td height="24" align="center">
							<a href="member_detail.jsp?ID=<%=0%>"><%=0%></a>&nbsp;</td>
						<td align="center"><%=0%>&nbsp;</td>
						<td align="center"><%=0%>&nbsp;</td>
						<td align="center"><%=0%>&nbsp;</td>
						<td align="center"><%=0%></td>
						<td align="center">
							<a href="#">修改</a><br>
							<input id="delid" name="delid" type="checkbox" class="noborder" value="<%=0%>">
						</td>
					</tr>
					<!-- 用户信息遍历结束 -->
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="73%" height="24">&nbsp;</td>
							<td width="27%" align="right"><input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(frm.delid,frm.checkbox)"> [全选/反选] 
							[<a style="color:red;cursor:hand;" onClick="checkdel(frm.delid,frm)">删除</a>]
							<div id="ch">
								<input name="delid" type="checkbox" class="noborder" value="0">
							</div>
							</td>
							
							<!--层ch用于放置隐藏的checkbox控件，因为当表单中只是一个checkbox控件时，应用javascript获得其length属性值为undefine-->
							<script language="javascript">
								ch.style.display = "none";
							</script>
						</tr>
					</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="27" align="right">当前页数：[<%=0%>/<%=0%>]&nbsp; 
						<a href="membermanage.jsp?Page=1">第一页</a> 
						<a href="membermanage.jsp?Page=<%=0 - 1%>">上一页</a>
						<a href="membermanage.jsp?Page=<%=0 + 1%>">下一页</a> 
						<a href="membermanage.jsp?Page=<%=0%>">最后一页&nbsp;</a> 
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
</body>
</html>
