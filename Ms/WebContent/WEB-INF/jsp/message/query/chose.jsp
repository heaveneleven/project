<!DOCTYPE html>
<html>
<head>
<title>原始报文查询</title>
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
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui-timepicker-addon.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/message/query/chose.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	/* css for timepicker */
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
.ui-timepicker-div dl dd { margin: 0 10px 10px 45%; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }

.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
.ui-timepicker-rtl dl dt{ float: right; clear: right; }
.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px; }
</style>
</head>
<body> 
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" >
				   <div style="height:30px; background:#87B7DA;padding : 3px;font-size:10px;">
					<label for="from" style="font-size:12px;">从</label>
					<input type="text" readonly="readonly" id="from" name="from"style="width: 135px;height:12px;background:#E4F1FC">
					<label for="to" style="font-size:12px;">到</label>
					<input type="text" readonly="readonly" id="to" name="to"style="width:135px;height:12px;background:#E4F1FC">
				   	<button id="query" style="height:25px">点击查询</button>
				   </div>
					<div style="margin-left:270px;padding:10px;">
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