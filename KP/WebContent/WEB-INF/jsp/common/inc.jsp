<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/default/easyui.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/icon.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/demo/demo.css" rel="stylesheet" type="text/css"/> 
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
 