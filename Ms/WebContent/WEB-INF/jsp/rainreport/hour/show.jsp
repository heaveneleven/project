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
<link href="<%=basePath%>css/report/table.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/rainreport/hour/show.js?<%=Math.random()%>" type="text/javascript" charset="utf-8"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
	 <input type="hidden" id="stcds" value=${stcds}>
	 <input type="hidden" id="start" value=${start}>
	 <input type="hidden" id="end" value= ${end}>
	 <input type="hidden" id="space" value=${space}>
	 <input type="hidden" id="stnms" value=${stnms}>
	 <!--分页功能的实现-->
	 <input type="hidden" id="page" value='1'>
	 <!--一共有多少页-->
	 <input type="hidden" id="totalpages" value=${totalpages}>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>  
				<div class="content" style="overflow:auto;">
					<div style="height:30px; background:#87B7DA;padding : 4px;font-size:16px;color:#3B5888;">
						<label>时段降水量报表&nbsp;&nbsp;&nbsp; <b>${start}</b>&nbsp;至&nbsp;<b>${end}</b> &nbsp;时间段选择为
						<% 
							if("five".equals(request.getParameter("space")))
								out.print("<b>5分钟</b>");
							else if("thirty".equals(request.getParameter("space")))
								out.print("<b>30分钟</b>");
							else
								out.print("<b>1小时</b>");
						%>
						</label>
						<button id="export" style="height:25px;float : right;font-size:11px;">导出Excel</button>
				   </div>
				   	 <!--进度条-->
					<!-- <div id="progressbar"><div class="progress-label">加载...</div></div> -->
					<table id="hourlist" style="margin:0px auto;"></table>
					<div style="height:25px;padding:4px;color:#3B5888;margin:0 auto;background:#87B7DA;">
					&nbsp;&nbsp;第&nbsp;<span id="nowpage" style="font-weight:bold"></span>&nbsp;页
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="preText">上一页</a>
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="nextText">下一页</a>					
					&nbsp;共&nbsp;<b>${totalpages}</b>&nbsp;页
					&nbsp;
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="first">首页</a>
					<a style="color:#3B5888;font-weight:bold" href='javascript:void(0)' id="last">尾页</a>		
					&nbsp;
					&nbsp;第&nbsp;<input id="pagenum" type="text"style="width:30px;height:10px;background:#E4F1FC">&nbsp;页
					<button id="chosepage" style="font-size:11px;">跳转</button>
				</div>
				</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>