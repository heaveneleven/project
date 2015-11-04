<!DOCTYPE html>
<html>
<head>
<title>月降水量柱状图</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/jquery-ui-themes-1.11.2/themes/redmond/theme.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/jquery-ui-themes-1.11.2/themes/redmond/jquery-ui.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery.jqGrid.src.js" type="text/javascript"  charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/i18n/grid.locale-cn.js" type="text/javascript" charset="utf-8"></script> 
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/chart/singleRain/month/chose.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	/**修改日期样式，使其只显示年月*/
	.ui-datepicker-calendar {
    display: none; 
</style>
</head>
<body> 
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" >
				   <div style="height:30px; background:#87B7DA;padding : 3px;font-size:10px;">
					<label style="font-size:12px;">请选择月份</label>
					<input type="text" readonly="readonly" id="choseDate" style="width: 100px;height:12px;background:#E4F1FC">
					<button id="query" style="height:25px">点击查询</button>
				   </div>
					<div style="margin-left:240px;padding:10px;">
					<table id="chose" style="font-size:12px;"></table>
					<!-- jqGrid 分页 div gridPager -->
					<div id="gridPager"></div>
					</div>
				</div>	
				<div id="dialog" title="警告!">
  					<b id="msg"></b>
				</div>				
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>