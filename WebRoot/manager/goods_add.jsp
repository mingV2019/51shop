<%@page import="com.bean.SubType"%>
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
<%String _superId = request.getParameter("superId");
_superId = _superId==null?""+dao.getSuperTypeById().getSuperId():_superId;
int superId = Integer.parseInt(_superId);%>
<html>
<head>
<title>51商城――后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>manager/CSS/style.css" rel="stylesheet">
<script language="javascript" src="<%=basePath%>manager/JS/jquery.min.js"></script>
</head>
<script language="javascript">
	function mycheck() {
		if (form1.goodsName.value == "") {
			alert("请输入商品名称！");
			form1.goodsName.focus();
			return;
		}
		if (form1.picture.value == "") {
			alert("请输入图片文件的路径！");
			form1.picture.focus();
			return;
		}
		if (isNaN(form1.price.value)) {
			alert("您输入的原价错误，请重新输入！");
			form1.price.value = "";
			form1.price.focus();
			return;
		}
		if (isNaN(form1.nowPrice.value)) {
			alert("您输入的现价错误，请重新输入！");
			form1.price.value = "";
			form1.price.focus();
			return;
		}
		if (form1.introduce.value == "") {
			alert("请输入商品简介！");
			form1.introduce.focus();
			return;
		}
		if (form1.nowPrice.value == "" || form1.price.value == "") {
			alert("请输入原件或现价");
			form1.nowPrice.focus();
			form1.price.focus();
			return;
		}
		var nowPrice = document.getElementById("nowPrice").value;
		var price = document.getElementById("price").value;
		if (parseInt(nowPrice) >= parseInt(price)) {
			alert("现价不能比原价低或相等");
			return;
		}
		form1.submit();
	}
	$(document).ready(function() {
		$('#goodsType').change(function(){
			var check = $(this).children('option:selected').val();
			if(check == 'sale') {
				$('#price').attr('disabled',false);
			}else if(check == 'newsGoods') {
				$('#price').attr('disabled',true);
			}
		});
	}
	);
</script>

<body>
	<jsp:include page="banner.jsp" />
	<table width="1280" height="288" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		<tr>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10" height="38" align="right">&nbsp;</td>
						<td colspan="3" class="tableBorder_B_dashed">
							<img src="<%=basePath%>manager/images/manage_ico1.GIF" width="11" height="11">&nbsp;
							<a href="<%=basePath%>manager/superType.jsp"> [大分类信息管理]</a>&nbsp;&nbsp;&nbsp;
							<img src="<%=basePath%>manager/images/manage_ico2.GIF" width="11" height="11">&nbsp;
							<a href="<%=basePath%>manager/subType.jsp">[小分类信息管理]</a>&nbsp;&nbsp;&nbsp;
							<img src="<%=basePath%>manager/images/manage_ico3.GIF" width="12" height="12">&nbsp;
							<a href="<%=basePath%>manager/goods_add.jsp">[添加商品信息]</a>
						</td>
						<td width="12">&nbsp;</td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td height="10" colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="29" align="right">&nbsp;</td>
						<td width="10" background="images/manage_leftTitle_left.GIF">&nbsp;</td>
						<td width="1089" align="center" background="<%=basePath%>manager/images/manage_leftTitle_mid.GIF" class="word_white">
							<b>添加商品信息</b>
						</td>
						<td width="10" background="<%=basePath%>manager/images/manage_leftTitle_right.GIF">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<form action="<%=basePath%>goodsinfoservlet?action=insertGoodsInfo" enctype="multipart/form-data" method="post" name="form1" id="form1">
					<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolordark="#FFFFFF">
						<tr>
							<td width="14%" height="27">&nbsp;商品名称：</td>
							<td height="27" colspan="3">&nbsp; 
								<input name="goodsName" type="text" class="Sytle_text" id="bookID2" size="50">&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td height="27">&nbsp;所属大类：</td>
							<td width="31%" height="27" id="superType">&nbsp; 
								<select name="supertype" class="textarea" id="supertype" 
								onchange="window.open(this.options[this.selectedIndex].value,target='_self')">
								<%List<SuperType> superTypes = dao.getSuperTypeAll();
									for(SuperType superType : superTypes) {%>
									<option value="goods_add.jsp?superId=<%=superType.getSuperId()%>" 
									<%=superType.getSuperId()==superId?"selected":""%>>
										<%=superType.getTypeName()%>
									</option>
									<%} %>
								</select>
							</td>
							<td width="13%" height="27">&nbsp;所属小类：</td>
							<td width="42%" height="27" id="subType">&nbsp; 
								<select name="subtype" class="textarea" id="subtype">
								<%List<SubType> subTypes = dao.getSubTypeBySuperID(superId);
								for(SubType subType : subTypes) {%>
									<option value="<%=subType.getSubId()%>"><%=subType.getTypeName()%></option>
								<%} %>
								</select>
							</td>
						</tr>
						<tr>
							<td height="45">&nbsp;商品类型：</td>
							<td>&nbsp; 
								<select id="goodsType" name="goodsType">
									<option value="sale">打折</option>
									<option value="newsGoods">新品</option>
								</select>
							</td>
							<td>&nbsp;图片文件：</td>
							<td>&nbsp;<input name="picture" type="file" class="Style_upload" id="picture"></td>
							<!-- <input name="picture" type="text" class="Style_upload" id="picture"> -->
						</tr>
						<tr>
							<td height="41">&nbsp;原价：</td>
							<td height="41">
								<span style="float:left;"> 
								<input name="price" type="text" class="Sytle_text" id="price" value="0"></span>
								<span style="float:left;padding-top:10px;">&nbsp;(元)</span>
							</td>
							<td height="41">&nbsp;现价：</td>
							<td>
								<span style="float:left;"> 
								<input name="nowPrice" type="text" class="Sytle_text" id="nowPrice" value="0"></span>
								<span style="float:left;padding-top:10px;">&nbsp;(元)</span>
							</td>
						</tr>
						<tr>
							<td height="103">&nbsp;商品简介：</td>
							<td colspan="3">
								<span class="style5">&nbsp; </span> 
								<textarea name="introduce" cols="60" rows="5" class="textarea" id="introduce"></textarea>
							</td>
						</tr>
						<tr>
							<td height="38" colspan="4" align="center">
								<input name="Button" type="button" class="btn_bg_short" value="保存" onClick="mycheck()"> &nbsp; 
								<input name="Submit2" type="reset" class="btn_bg_short" value="重置"> &nbsp; 
								<input name="Submit3" type="button" class="btn_bg_short" value="返回" onClick="JScript:history.back(-1)">
							</td>
						</tr>
					</table>
				</form>
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
