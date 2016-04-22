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
<script src="<%=basePath%>js/customer/change/my_order.js" type="text/javascript" charset="utf-8"></script>
<div id="my_order" class="easyui-layout" data-options='fit:true'> 
	我的库存
</div>  
