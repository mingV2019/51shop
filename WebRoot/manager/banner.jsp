<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="1280" height="114"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><img src="<%=basePath%>manager/images/manage_banner.JPG" width="1280" height="114" /></td>
  </tr>
</table>