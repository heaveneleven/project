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
<script src="<%=basePath%>js/stu/layout/majorChose.js" type="text/javascript" charset="utf-8"></script>
    <div style="margin-top:10px;">
    	<ul id="major_tree" class="easyui-tree"></ul>  
    </div>