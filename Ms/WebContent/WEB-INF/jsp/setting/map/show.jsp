<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>地图设置</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/theme.jsp"></jsp:include>
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/realtime/realtime.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/jquery.jqGrid.src.js" type="text/javascript"  charset="utf-8"></script>
<script src="<%=basePath%>js/Guriddo_jqGrid_JS_4.7.1/js/i18n/grid.locale-cn.js" type="text/javascript" charset="utf-8"></script> 
<script src="<%=basePath%>js/setting/map/show.js" type="text/javascript" charset="utf-8"></script>
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
<script src="<%=basePath%>js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
    <style type="text/css">  
    	#container{height:99%;width:68%; border:2px solid gray;float:left;}  
    	#tab{float:left;margin-left:3px;margin-top:5px;}
    </style>  
</head>
<body> 
     <!-- 设置变量rowID来保存jqgrid的行ID -->
     <input type="hidden" id="rowID" value='null'>
     <input type="hidden" id='center' value="set">
     <input type="hidden" id="range" value=${range}>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main" style="padding:0px;">
				<div class="content" style="width:1190px;margin-left:0px;">
					<div id="container"></div>
					<div id="tab">
					<span>省份：</span>
					<select id="pro" class="easyui-combobox" name="pro" style="width:80px;">   
					</select>  
					&nbsp;&nbsp;
					<span>城市：</span>
					<select id="city" class="easyui-combobox" name="city" style="width:80px;">   
					</select> 
					<hr/>
					<span>中心位置设置</span>&nbsp;&nbsp;<button id="setC">设置</button>&nbsp;&nbsp;<button id="cancel">取消</button><br/>
					<div id="setShow">
						<span>经度：</span><input id="clgtd" type="text" value=${lgtd} style="width:80px;"readonly="readonly"/>
						<span>经度：</span><input id="clttd" type="text" value=${lttd} style="width:80px;"readonly="readonly"/>
					</div>
					<table id="list"></table>
					<!-- jqGrid 分页 div gridPager -->
					<div id="gridPager"></div>
					</div>
				</div>				
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>