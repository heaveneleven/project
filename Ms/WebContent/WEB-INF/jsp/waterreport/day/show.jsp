<!DOCTYPE html>
<html>
<head>
<title>日水位报表</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/theme.jsp"></jsp:include>
<link href="<%=basePath%>css/report/table.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/waterreport/day/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	 <input type="hidden" id="stcds" value=${stcds}>
	 <input type="hidden" id="stnms" value=${stnms}>
	 <input type="hidden" id="date" value=${date}>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" style="overflow:auto;">
					<div style="height:30px;width:892px; background:#87B7DA;padding : 4px;font-size:16px;color:#3B5888;">
						<label>日水位报表&nbsp;&nbsp;&nbsp; <b>${date}</b>&nbsp;&nbsp;<font
					style="font-size: 12px;">(红线代表该时段未来报文)</font>
						</label>
						<button id="export" style="height:25px;font-size:11px;">导出Excel</button>
				   </div>
					<table id="daylist" style="width:900px;margin:0px auto;"></table>
				</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>