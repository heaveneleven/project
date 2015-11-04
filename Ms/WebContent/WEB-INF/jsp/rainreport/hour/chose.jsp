<!DOCTYPE html>
<html>
<head>
<title>时段降水量报表</title>
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
<script src="<%=basePath%>js/rainreport/hour/chose.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" >
				   <div style="height:30px; background:#87B7DA;padding : 3px;font-size:10px;">
					<label for="from" style="font-size:12px;">从</label>
					<input type="text" readonly="readonly" id="from" name="from"style="width: 100px;height:12px;background:#E4F1FC">
					<label for="to" style="font-size:12px;">到</label>
					<input type="text" readonly="readonly" id="to" name="to"style="width: 100px;height:12px;background:#E4F1FC">
				   	<label style="font-size:12px;">请选择时间间隔</label>
				   	<select id="space" style="background:#E4F1FC;">
				   		<option value="five">5分钟</option>
				   		<option value="thirty">30分钟</option>
				   		<option value="sixty">1小时</option>
				   	</select>
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