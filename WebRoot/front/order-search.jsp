<%@page language="java" contentType="text/html; charset=UTF-8" import="com.bean.Member" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%Member member = (Member)request.getSession().getAttribute("memberSession"); 
String url = "/front/index.jsp";%>
<jsp:useBean id="chStr" scope="page" class="com.utils.ChStr" />

<div id="toolbar" style="background-color: #F0F0F0; width: 100%;">
	<div class="container">
		<div class="row">
			<div class="toolbar-ct-1">
				<p>
					<i class="fa fa-phone"></i> <span style="margin-right: 15px;">电话：400-675-1066</span>
					<%if(member == null) {%>
						<a href="<%=basePath%>front/login.jsp?url=<%=url%>">登录</a>&nbsp; ｜ &nbsp;<a href="<%=basePath%>front/register.jsp">注册</a>
					<%} else {%>
					您好，<%=member.getUserName()%>
					&nbsp; &nbsp;<a href="modifyMember.jsp">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="<%=basePath%>memberservlet?action=destroy">退出</a><%}%>
				</p>
			</div>
			<div class="toolbar-ct-2">
				<%if(member!=null) {%>
					<a href="<%=basePath%>orderservlet?action=showShopOrder">我的订单</a>
					&nbsp;&nbsp; | &nbsp;&nbsp; 
					<a href="collect.jsp">我的收藏</a>
				<%}else{ %>
					<a href="<%=basePath%>front/login.jsp">我的订单</a>
					&nbsp;&nbsp; | &nbsp;&nbsp; 
					<a href="<%=basePath%>front/login.jsp">我的收藏</a>
				<%} %>
			</div>
		</div>
	</div>
	<div style="background-color: white; width: 100%">
		<div class="container">
			<div class="row">
				<div class="toolbar-ct col-xs-12 col-md-6  hidden-sm hidden-xs">
					<div class="toolbar-ct-1">
						<a href="<%=basePath%>front/index.jsp"> <img src="<%=basePath%>images/51logo.png"> </a>
					</div>
					<div>
						<!-- 搜索条 -->
						<div class="search_box">
							<div class="top-nav-search">
								<form method="post" action="<%=basePath%>orderservlet?action=showShopOrder">
									<input type="text" name="searchword" size="38" style="border: 0px;" class="top-nav-search-input" placeholder="请输入需要搜索的订单信息" value="" /> 
									<input type="image" src="<%=basePath%>images/search.png" class="search_box_img" onFocus="this.blur()" />
								</form>
							</div>
						</div>
						<!-- //搜索条 -->
					</div>
				</div>

				<div class="toolbar-ct toolbar-ct-right col-xs-12 col-md-3">
					<div class="toolbar-ct-2 " style="margin-top: 35px; border: 1px solid #EAEAEA; padding: 5px;">
						<%if(member!=null) {%>
						<a href="<%=basePath%>front/cart_see.jsp" style="color: #E33737; font-size: 20px;">
							<i class="fa fa-cart1"></i> 我的购物车
						</a>
						<%}else {%>
						<a href="<%=basePath%>front/login.jsp" style="color: #E33737; font-size: 20px;">
							<i class="fa fa-cart1"></i> 我的购物车
						</a>
						<%} %>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>