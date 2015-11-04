<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/common/footer.css" rel="stylesheet" type="text/css" />
	
<div class="copyright">	
		<!-- <hr width=1000px size=1 color=gray align=center noshade/> -->
		<hr size=1 color=gray noshade/>
		版权所有@<a href="http://www.njquanshui.com" target="_blank">南京全水电子技术有限公司</a>
		<!-- <div class="spoken">联系我们</div> -->
</div>