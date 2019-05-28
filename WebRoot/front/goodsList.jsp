<%@page import="com.bean.SubType"%>
<%@page import="com.bean.PageInfo"%>
<%@page import="com.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String type = (String)request.getAttribute("type"); type=type==null?"":type;
	String action = (String)request.getAttribute("action");
	String superId = (String)request.getAttribute("superId");
	String subId = (String)request.getAttribute("subId");
	String searchword = (String)request.getAttribute("searchword");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	List<Goods> goodses = (List<Goods>)request.getAttribute("goodses");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="<%=basePath%>/front/css/mr-01.css" type="text/css">

<script src="<%=basePath%>/front/js/jsArr01.js" type="text/javascript"></script>
<script src="<%=basePath%>/front/js/module.js" type="text/javascript"></script>
<script src="<%=basePath%>/front/js/jsArr02.js" type="text/javascript"></script>
<script src="<%=basePath%>/front/js/tab.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="/front/index-loginCon.jsp" />
	<!-- 网站头部 -->
	<jsp:include page="/front/common-header.jsp"/>
	<!-- //网站头部 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- //分页显示图书列表 -->
			<div id="mr-content" class="mr-content col-xs-12 col-sm-12 col-md-9 col-md-push-3">
			  <div id="system-message-container" style="display: none;"></div>
				<div id="mrshop" class="mrshop common-home">
					<div class="container_oc">
						<div class="row">
							<div id="content_oc" class="col-sm-12">
								<div class="box_oc">
									<div class="box-heading">
										<h1 class="mrshop_heading_h1">
											<% if("search".equals(action)==true) {%>
												查询结果
											<%} else { 
												List<SubType> subTypes = (List<SubType>)request.getAttribute("subTypes");
												for(SubType subType : subTypes) {%>
													<a href="<%=basePath%>goodsinfoservlet?action=detailSort&&type=<%=type%>&&subId=<%=subType.getSubId()%>&&superId=<%=subType.getSuperType().getSuperId()%>">
													【<%=subType.getTypeName()%>】</a>
												<%} %>
											<%}%>
										</h1>
									</div>
									<div class="box-content">
										<hr>
										<div class="row">
											<%
												for(Goods goods : goodses) {
											%>
											<div class="product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12">
												<div class="product-thumb">
													<div class="actions">
														<div class="image">
															<a style="width: 95%" href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>">
																<img src="<%=basePath%><%=goods.getPicture()%>" class="img-responsive"/>
															</a>
														</div>
													</div>
													<div>
														<div class="caption">
															<div class="name">
																<a href="<%=basePath%>front/goodsDetail.jsp?goodsId=<%=goods.getGoodsId()%>" style="width: 95%">
																	<span style="color: #0885B1">名称：<%=goods.getGoodsName()%></span> </a>
															</div>
															<p class="price">
																<span class="price-new">分类：<%=goods.getSubType().getTypeName()%></span>
																<span class="price-tax">价格:<%=goods.getNowPrice()%>元</span>
															</p>
														</div>
													</div>
												</div>
											</div>
										<%} %>
										</div>
										<div style="text-align:center;">
										<div class="row pagination" style="display:inlineblock;">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="30">
														当前页数：【<%=pageInfo.getPageThis()%>】&nbsp;&nbsp;
														<%if("detailSort".equals(action)) {%>
														 	<a href="<%=basePath%>pageservlet?pageThis=1&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>&&subId=<%=subId%>">第一页</a>
														 	<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()-1%>&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>&&subId=<%=subId%>">上一页</a>
														 	&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()+1%>&&action=<%=action%>
															&&type=<%=type%>&&superId=<%=superId%>&&subId=<%=subId%>">下一页</a>
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=<%=action%>
															&&type=<%=type%>&&superId=<%=superId%>&&subId=<%=subId%>">最后一页&nbsp;</a>
														<%} else if("sort".equals(action)) {%>
															<a href="<%=basePath%>pageservlet?pageThis=1&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>">第一页</a> 
														 	<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()-1%>&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>">上一页</a>
															&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()+1%>&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>">下一页</a>
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&action=<%=action%>&&type=<%=type%>&&superId=<%=superId%>">最后一页&nbsp;</a>
														<% } else {%>
															<a href="<%=basePath%>pageservlet?pageThis=1&&action=<%=action%>&&type=<%=type%>&&searchword=<%=searchword%>">第一页</a> 
														 	<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()-1%>&&type=<%=type%>&&action=<%=action%>&&searchword=<%=searchword%>">上一页</a>
															&nbsp;&nbsp;&nbsp;&nbsp;共【<%=pageInfo.getRecordCount()%>】条数据&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageThis()+1%>&&type=<%=type%>&&action=<%=action%>&&searchword=<%=searchword%>">下一页</a>
															<a href="<%=basePath%>pageservlet?pageThis=<%=pageInfo.getPageTotal()%>&&type=<%=type%>&&action=<%=action%>&&searchword=<%=searchword%>">最后一页&nbsp;</a>
														<%} %>
														&nbsp;&nbsp;共【<%=pageInfo.getPageTotal()%>】页
													</td>
												</tr>
											</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- //分页显示图书列表 -->
		<!-- 显示左侧热门商品 -->
		<jsp:include page="/front/leftHotGoods.jsp"/>
		<!-- // 显示左侧热门商品 -->
		</div>
	</div>
	<!-- 版权栏 -->
	<jsp:include page="/front/common-footer.jsp"/>
	<!-- //版权栏 -->
</body>
</html>