<!DOCTYPE html>
<html>
<head>
<title>日水情</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
<script src="<%=basePath%>js/dayinfo/waterinfo.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content">
				   <div style="height:30px; background:#87B7DA;padding : 3px;font-size:10px;">
					<button id="preDay" style="height:25px">前一天</button>&nbsp;&nbsp;&nbsp;<button id="nextDay" style="height:25px">后一天</button>
					&nbsp;&nbsp;&nbsp;<input type="text" id="choseDate" readonly="readonly" style="width: 100px;height:12px;background:#E4F1FC"/>
				  				   <input type="hidden" id="hide"/>
				   </div>
					<div style="margin-left:200px;padding:10px;">
					<table id="list"></table>
					<!-- jqGrid 分页 div gridPager -->
					<div id="gridPager"></div>
					</div>
				</div>					
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>