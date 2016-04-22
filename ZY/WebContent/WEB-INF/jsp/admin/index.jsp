<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>专业选择系统-管理员登录</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="main_layout" class="easyui-layout">   
    
	<div data-options="region:'north',href:'<%=basePath%>admin/layout/north'"
		style="height: 110px; overflow: hidden;" class="logo"></div>
	<div
		data-options="region:'south',href:'<%=basePath%>admin/layout/south',border:false"
		style="height: 30px; overflow: hidden;"></div>

	<div data-options="region:'west',href:'<%=basePath%>admin/layout/west/menu',split:true,iconCls:'icon-house',split: false" title="学员管理"
		style="width: 200px; overflow: hidden;">
		<ul id="mainMenu"></ul>
	</div>
	
	<div data-options="region:'center'" title="" style="overflow: hidden;">
	
		<div id="cc" class="easyui-layout" data-options="fit:true">   
  
    		<div data-options="region:'center',title:''" style="padding:0px;">
    			<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">   
    			  	
    			</div>   
    		</div>
        </div>  
	</div>
</body>  
</html>