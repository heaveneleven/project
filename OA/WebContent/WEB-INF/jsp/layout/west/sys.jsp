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
<script src="<%=basePath%>js/nav/sys/sys.js"type="text/javascript" charset="utf-8"></script>
<div id="aa" class="easyui-accordion"  data-options="fit:true,animate:false,iconCls:'icon-house'">   
    <div title="系统设置" data-options="iconCls:'icon-wrench'" style="padding:10px;">   
		 <ul id="sys_tree"></ul>        
    </div>   
</div>  
<script type="text/javascript">
	
</script>
