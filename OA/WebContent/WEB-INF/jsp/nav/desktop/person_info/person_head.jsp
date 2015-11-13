<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String contextPath = request.getContextPath();
%>
<div style="text-align:center;margin-top:10%;">
<img style="width:156px;height:156px;" src="<%=basePath%>images/user/default/default.png"  alt="用户头像" />
</div>