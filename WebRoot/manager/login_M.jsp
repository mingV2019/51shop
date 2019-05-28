<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String message = (String)request.getAttribute("message");
	message = message == null?"":message;
%>
<html>
<head>
<title>后台管理!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script src="<%=basePath%>manager/JS/check.js"></script>
<style type="text/css">
body{
	margin: 0px;
}
</style>
</head>

<body>
<table width="100%" height="545" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td style="background-image:url(<%=basePath%>manager/images/managerlogin_bg.png);"> 
			<form name="form1" method="post" action="<%=basePath%>managerservlet?action=login" onSubmit="return checkM(form1)">
			<table width="448" height="345"  border="0" align="center"  style="margin-top:170px;" cellpadding="0" cellspacing="0" background="<%=basePath%>manager/images/managerlogin_dialog.png">
				<tr><td height="60" colspan="2" align="center">&nbsp;</td></tr>	  
				<tr>
					<td width="55" height="280" align="center" valign="top">&nbsp;</td>
					<td width="436" align="left" valign="top">
						<table style="margin-top:30px" width="88%" height="240"  border="0" cellpadding="0" cellspacing="0">
						<tr><td align="center"><%=message%></td></tr>
						<tr>
							<td width="99%" height="74" align="center">
								<input name="username" type="text" id="username" size="24"  style="background:url('<%=basePath%>manager/images/manager.png');background-repeat: no-repeat;background-position: left;padding:12px 0px 12px 44px;">
							</td>
						</tr>
						<tr>
							<td height="30" align="center">
							<span class="word_white">
								<input name="password" type="password" id="password" size="24" style="background:url('<%=basePath%>manager/images/manager_pwd.png');background-repeat: no-repeat;background-position: left;padding:12px 0px 12px 44px;">
							</span>
							</td>
						</tr>
						<tr>
							<td height="57" align="center">
							<input name="Submit" type="submit" class="login_ok" value="确认">&nbsp;
							<input name="Submit2" type="reset" class="login_reset" value="重置">
							</td>
						</tr>
						<tr>
							<td height="35" align="right">
								<a href="<%=basePath%>/front/index.jsp"><img src="<%=basePath%>manager/images/back.png"> 返回商城主页</a>
							</td>
						</tr>            
						</table>
					</td>
				</tr>
			</table>
			<table width="491" height="39" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" >All CopyRights &copy; reserved 2016 吉林省明日科技有限公司</td>
				</tr>
			</table>
		</form>
		</td>
	</tr>
</table>
</body>
</html>
