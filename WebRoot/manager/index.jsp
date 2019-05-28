<%@page import="com.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.PageInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String action = (String)request.getAttribute("action");
	String searchword = (String)request.getAttribute("searchword");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	List<Goods> goodses = (List<Goods>)request.getAttribute("goodses");
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
	<table width="1280" height="114"  border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top"><img src="<%=basePath%>manager/images/manage_banner.JPG" width="1280" height="114" /></td>
	  </tr>
	</table>
	<table width="1280" height="288"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  	<tr>
	    <td align="center" valign="top">
	    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
			<tr>
			  <td width="18" height="45" align="right">&nbsp;</td>
			  <td colspan="3" class="tableBorder_B_dashed">
			  	<img src="<%=basePath%>manager/images/manage_ico1.GIF" width="11" height="11">&nbsp;
			  	<a href="<%=basePath%>manager/superType.jsp">[大分类信息管理]</a>&nbsp;&nbsp;&nbsp;
			  	<img src="<%=basePath%>manager/images/manage_ico2.GIF" width="11" height="11">&nbsp;
			  	<a href="<%=basePath%>manager/subType.jsp">[小分类信息管理]</a>&nbsp;&nbsp;&nbsp;
			  	<img src="<%=basePath%>manager/images/manage_ico3.GIF" width="12" height="12">&nbsp;
			  	<a href="<%=basePath%>manager/goods_add.jsp">[添加商品信息]</a></td>
			  <td width="24">&nbsp;</td>
			</tr>
			<tr>
			  <td align="right">&nbsp;</td>
			  <td height="10" colspan="3">&nbsp;</td>
			  <td>&nbsp;</td>
			</tr>
			<tr>
			  <td height="29" align="right">&nbsp;</td>
			  <td width="10" background="images/manage_leftTitle_left.GIF">&nbsp;</td>
			  <td width="989" align="center" background="<%=basePath%>manager/images/manage_leftTitle_mid.GIF" class="word_white">
			  		<b>商品列表</b>
			  </td>
			  <td width="10" background="images/manage_leftTitle_right.GIF">&nbsp;</td>
			  <td>&nbsp;</td>
			</tr>
	    </table>
	    <table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
	    	<tr>
	    		<!-- 搜索条 -->
	    		<form method="post" action="<%=basePath%>goodsinfoservlet?action=showGoodsAll">
		    		<td>
						<input type="text" name="searchword" size="20" style="border: 0px;" class="top-nav-search-input" placeholder="请输入需要搜索的商品信息" value="" /> 
		    		</td>
		    		<td width="5%">
		    			<input type="image" src="<%=basePath%>images/search.png" class="search_box_img" onFocus="this.blur()" />
		    		</td>
	    		</form>
	    		<!-- //搜索条 -->
	    	</tr>
			<tr>
				<td valign="top" colspan="2">
				<table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
	            	<tr>
						<td height="13" align="center">&nbsp;</td>
					</tr>
				</table>
				<form action="<%=basePath%>goodsinfoservlet?action=showGoodsAll" method="post" name="frm">
	            <table width="100%" height="40"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#E6E6E6">
	              <tr bgcolor="#eeeeee">
	                <td width="35%" align="center">商品名称</td>
	                <td width="5" align="center">类型</td>
	                <td width="5" align="center">原价</td>
	                <td width="5" align="center">现价</td>
	                <td width="35" align="center">进货时间</td>
	                <td width="5" align="center">点击次数</td>
	                <td width="5" align="center">修改操作</td>
	              </tr>
	              <%
	              	for(Goods goods : goodses) {
	              %>
	              <tr bgcolor="#eeeeee">
	                <td width="35%" align="center"><%=goods.getGoodsName()%></td>
	                <td width="5" align="center"><%=goods.getSubType().getTypeName()%></td>
	                <td width="5" align="center"><%=goods.getPrice()%></td>
	                <td width="5" align="center"><%=goods.getNowPrice()%></td>
	                <td width="35" align="center"><%=goods.getInTime()%></td>
	                <td width="5" align="center"><%=goods.getHit()%></td>
	                <td width="5" align="center">
	                	<a href="<%=basePath%>manager/goods_edit.jsp?goodsId=<%=goods.getGoodsId()%>">修改</a>
	                </td>
	              </tr>
	              <%} %>
	           	</table>
				</form>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
			  		<tr>
			    	<td height="30" align="center">当前页数：[<%=pageInfo.getPageThis()%>/<%=pageInfo.getPageTotal()%>]&nbsp;
						<a href="<%=basePath%>pageservlet?pageThis=1&&action=<%=action%>&&searchword=<%=searchword%>">第一页</a>　
						<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() - 1%>&&action=<%=action%>&&searchword=<%=searchword%>">上一页</a>
				　		<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis() + 1%>&&action=<%=action%>&&searchword=<%=searchword%>">下一页</a>　
						<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=<%=action%>&&searchword=<%=searchword%>">最后一页&nbsp;</a>
					</td>
			  		</tr>
				</table>
				</td>		  
			</tr>
		</table>
		</td>
		<td width="182" valign="top">
			<table width="100%" height="431"  border="0" cellpadding="0" cellspacing="0">
	     			<tr><td width="199" valign="top" bgcolor="#FFFFFF"><jsp:include page="/manager/navigation.jsp"/></td></tr>
	   		</table>
	   	</td>	  
	</tr>
	</table>
<jsp:include page="copyright.jsp"/>
</body>
</html>
