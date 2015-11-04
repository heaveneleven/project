<!DOCTYPE html>
<html>
<head>
<title>原始来报次数查询</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/theme.jsp"></jsp:include>
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/report/table.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/message/count/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	<!--<input type="hidden" id="stcds" value=${stcds}>--> 
	 <input type="hidden" id="start">
	 <input type="hidden" id="end">
	 <input type="hidden" id="stnms" value=${stnms}>
	 <input type="hidden" id="stcds" value=${stcds}>
	  <!--分页功能的实现-->
	 <input type="hidden" id="page" value='1'>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" style="white-space:nowrap;overflow-x:auto;overflow-y:auto;">
				   <div style="height:30px; background:#87B7DA;padding : 3px;font-size:10px;">
					<label for="from" style="font-size:12px;">从</label>
					<input type="text" readonly="readonly" id="from" name="from"style="width: 100px;height:12px;background:#E4F1FC">
					<label for="to" style="font-size:12px;">到</label>
					<input type="text" readonly="readonly" id="to" name="to"style="width: 100px;height:12px;background:#E4F1FC">
				   	<button id="query" style="height:25px">点击查询</button>
				   </div>
					
					<table id="messagecount" style="margin:0px auto;"></table>
					<div style="height:25px;padding:4px;color:#3B5888;margin:0 auto;background:#87B7DA;">
					&nbsp;&nbsp;第&nbsp;<span id="nowpage" style="font-weight:bold"></span>&nbsp;页
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="preText">上一页</a>
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="nextText">下一页</a>					
					&nbsp;共&nbsp;<b id="totalpages"></b>&nbsp;页
					&nbsp;
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="first">首页</a>
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="last">尾页</a>		
					&nbsp;
					&nbsp;第&nbsp;<input id="pagenum" type="text"style="width:30px;height:10px;background:#E4F1FC">&nbsp;页
					<button id="chosepage" style="font-size:11px;">跳转</button>
					</div>
					
				</div>	
				<div id="dialog" title="警告!">
  					<b id="msg"></b>
				</div>	
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>