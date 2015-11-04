<!DOCTYPE html>
<html>
<head>
<title>水情图形查询</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Highstock-2.1.3/js/highstock.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Highstock-2.1.3/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/chart/water/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	<input type="hidden" id="start" value="${start}"/>
	<input type="hidden" id="end" value="${end}"/>
	<input type="hidden" id="stcds" value="${stcds}"/>
	<input type="hidden" id="stnms" value="${stnms}">
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" >
				    <div id="container" style="min-width:700px;height:400px"></div>﻿
				</div>	
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>