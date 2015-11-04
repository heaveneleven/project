<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>实时地图</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>js/jquery-ui-1.11.2/external/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/realtime/map.js" type="text/javascript" charset="utf-8"></script>
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
    <style type="text/css">  
    	#container{height:100%}  
    </style>  
</head>
<body> 
	 <input type="hidden" id="lgtd" value=${lgtd}>
	 <input type="hidden" id="lttd" value=${lttd}>
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main" style="height:700px;">
				<div class="content" style="width:100%;height:100%;margin-left : 0px; ">
					<div id="container"></div>
				</div>				
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>