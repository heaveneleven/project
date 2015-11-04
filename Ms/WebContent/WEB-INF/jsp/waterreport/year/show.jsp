<!DOCTYPE html>
<html>
<head>
<title>年水位报表</title>
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
<script src="<%=basePath%>js/waterreport/year/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	 <input type="hidden" id="stcd" value=${stcd}>
	 <input type="hidden" id="stnm" value=${stnm}>
	 <input type="hidden" id="date" value=${date}>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" style="overflow:auto;">
					<div style="height:30px;background:#87B7DA;padding : 4px;font-size:16px;color:#3B5888;">
						<label><b>${stnm}</b>&nbsp;<b>${date}</b>年水位报表&nbsp;&nbsp;<font style="font-size:12px;">(红线代表当日未来报文)</font>
						</label>
						<button id="export" style="height:25px;font-size:11px;">导出Excel</button>
				   </div>
					<table id="yearlist"></table>
				</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>