<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/theme.jsp"></jsp:include>
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery.jqGrid.src.js" type="text/javascript"  charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/i18n/grid.locale-cn.js" type="text/javascript" charset="utf-8"></script> 
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/setting/userManager.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
</head> 
<body> 
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/setting/menu.jsp"/>  
				<div class="content">
					<div style="margin-left:170px;padding:10px;">
					<table id="list"></table>
					<!-- jqGrid 分页 div gridPager -->
					<div id="gridPager"></div>
					</div>
					<div id="dialog" title="添加登陆账户">
						<div class="resetpassword">
					<table style="color : #3B5888;font-weight : bold;" cellspacing="10px">	
						<tr>
							<td>请输入用户名</td><td><input type="text" name="username"/></td>
						</tr>				
						<tr>
							<td>请输入密码</td><td><input type="password" name="password"/></td>
						</tr>
						<tr>
							<td>请再次输入密码</td><td><input type="password" name="repassword"/></td>
						</tr>
						<tr>
							<td>是否赋予管理员权限</td><td><input type="checkbox" id="authority" name="authority"/></td>
						</tr>
						<tr>
							<td></td><td></td>
						</tr>
					</table>
					<input id="subadd" type="button" value="点击提交"/>
					</div>
					</div>
				</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>