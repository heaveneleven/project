<!DOCTYPE html>
<html>
<head>
<title>多站时段柱状图</title>
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
<script src="<%=basePath%>js/Highcharts-4.0.3/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Highcharts-4.0.3/js/highcharts-3d.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Highcharts-4.0.3/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/chart/multiRain/month/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	<input type="hidden" id="stcds" value="${stcds}"/>
	<input type="hidden" id="date" value="${date}"/>
	<input type="hidden" id="stnms" value="${stnms}">
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" >
				    <div id="container" style="min-width:700px;height:400px"></div> ﻿
				     <div id="sliders" style="min-width:310px;max-width: 800px;margin: 0 auto;">
				      <table>
				       <tr><td><b style="font-size:14px;color:#3B5888;">阿尔法角</b></td>
				       <td><input id="R0" type="range" min="0" max="45" value="15"/>
				        <span id="R0-value" class="value"></span>
				        </td></tr> <tr><td><b style="font-size:14px;color:#3B5888;">贝塔角</b></td><td>
				        <input id="R1" type="range" min="0" max="45" value="15"/>
				         <span id="R1-value" class="value"></span></td></tr>
				          </table> </div>
				</div>	
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>