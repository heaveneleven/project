<!DOCTYPE html>
<html>
<head>
<title>404错误</title>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"  href="<%=basePath%>js/jsTree/source/tree_component.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.main{
	
 	height : 500px; 
 	width : 850px; 
	/* background-color:#7872ef; */
 	margin : 0 auto; 
	text-align : center;
}
.main img{
	height : 500px;
	width : 850px;	
	margin-top : 0px;
}
</style>
</head>
<body> 
	 <jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
		<img class="img" src="<%=basePath%>images/404.jpg">	
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>