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
<script src="<%=basePath%>js/nav/desktop/person_info/person_info.js" type="text/javascript" charset="utf-8"></script>
<div id="person_info" style="padding:50px;"> 
	<div id="person_info_layout" class="easyui-layout" data-options="fit:true,maxWidth:750,maxHeight:400"></div>    
</div> 