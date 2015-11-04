<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.njqs.utils.UserInfo" %>
<%@ page import="com.njqs.utils.SessionKey" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%
	UserInfo userInfo=(UserInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
%>
<link href="<%=basePath%>css/common/header.css" rel="stylesheet"
	type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div class="head">
	<img class="img" src="<%=basePath%>images/header/head.jpg"/>
	<div class="welcomeFlag">
			<% if(userInfo != null){
				out.print("欢迎您："+userInfo.getUser().getUsername());
			}%>
	</div>
	<div class="navi">
		<div><a href="/Ms/realtime/table">信息服务</a></div>
		<div><a href="/Ms/realtime/map">实时地图</a></div>
		<div><a href="/Ms/login/manager/show">管理人员</a></div>
		<div><a href="login/setting.do">系统设置</a></div>
		<div><a href="login/logoff.do">注销</a></div>
	</div>
</div>