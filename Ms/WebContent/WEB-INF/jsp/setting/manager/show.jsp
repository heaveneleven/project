<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>人员管理设置</title>
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
<script src="<%=basePath%>js/setting/manager/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
</head> 
<body> 
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/setting/menu.jsp"/>  
				<div class="content">
					<div style="margin-left:20px;padding:10px;">
					<table id="list"></table>
					<!-- jqGrid 分页 div gridPager -->
					<div id="gridPager"></div>
					</div>
				</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>