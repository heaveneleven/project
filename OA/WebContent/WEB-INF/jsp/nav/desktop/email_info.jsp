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
<script src="<%=basePath%>js/nav/desktop/email_info/email_info.js" type="text/javascript" charset="utf-8"></script>
<div id="email_info" class="easyui-layout" data-options='fit:true'>   
    <div data-options="region:'west',iconCls:'icon-mail_box',title:'邮箱目录',split:true" style="width:180px;padding-left:20px;">
    	<ul id="email_root"></ul> 
    </div>   
</div>  
