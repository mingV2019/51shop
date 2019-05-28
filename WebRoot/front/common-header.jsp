<%@page import="java.util.List"%>
<%@page import="com.bean.SuperType"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="dao" scope="page" class="com.service.impl.GoodsServiceDaoImpl"/>
<header id="mr-header" class="wrap mr-header">
	<div class="container">
		<div class="row">
			<!-- 主导航条 -->
			<nav id="mr-mainnav" class="col-xs-12 col-md-6 mr-mainnav navbar navbar-default">
				<div class="mr-navbar navbar-collapse collapse">
					<div class="mr-megamenu animate slide" data-duration="400"
						data-responsive="true">
						<ul class="nav navbar-nav level0">
							<li itemprop="name" data-id="435" data-level="1">
								<a id="index" itemprop="url" class="" href="<%=basePath%>front/index.jsp" data-target="#">首页 </a>
							</li>
							<%List<SuperType> superTypes = dao.getSuperTypeAll();
								int number = 0;
								for(SuperType s : superTypes) {
							%>
							<li itemprop="name" data-id="510" data-level="1">
								<a id="sort<%=++number%>" itemprop="url" class="" 
								href="<%=basePath%>goodsinfoservlet?superId=<%=s.getSuperId()%>&&type=<%=number%>&&action=sort" 
								data-target="#">
									<%=s.getTypeName()%>
								</a>
							</li>
							<%} %>
						</ul>
					</div>
				</div>
			</nav>
			<!-- //主导航条 -->
		</div>
	</div>
</header>
<script>
	// 获取页面参数
	function GetString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");//正则表达式
		var r = window.location.search.substr(1).match(reg);//获取鼠标点击区域
		if (r != null)
			return unescape(r[2]);//返回区域编号
		return null;
	}
	var type = GetString('type');
	if (type === '1') {
		var sort1 = document.getElementById('sort1');
		sort1.style.backgroundColor = "#9E2626";

	}
	if (type === '2') {
		var sort2 = document.getElementById('sort2');
		sort2.style.backgroundColor = "#9E2626";

	}
	if (type === '3') {
		var sort3 = document.getElementById('sort3');
		sort3.style.backgroundColor = "#9E2626";

	}
	if (type === '4') {
		var sort4 = document.getElementById('sort4');
		sort4.style.backgroundColor = "#9E2626";

	}
	if (type == null) {//默认登录无点击
		var index = document.getElementById('index');
		index.style.backgroundColor = "#9E2626";

	}
</script>