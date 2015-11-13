<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>全水办公自动化系统</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<jsp:include page="/WEB-INF/jsp/common/inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="main_layout" class="easyui-layout">   
    
	<div data-options="region:'north',href:'<%=basePath%>layout/north'"
		style="height: 70px; overflow: hidden;" class="logo"></div>
	<div
		data-options="region:'south',href:'<%=basePath%>layout/south',border:false"
		style="height: 30px; overflow: hidden;"></div>

	<%-- <div data-options="region:'east',title:'East',split:true,href:'<%=basePath%>jsp/sys/east'"
		style="width:100px;"></div> --%>

	<div data-options="region:'west',href:'<%=basePath%>layout/west/my_office',split:true,iconCls:'icon-house',split: false" title="导航"
		style="width: 200px; overflow: hidden;">
		<ul id="mainMenu"></ul>
	</div>
	
	<div data-options="region:'center'" title="" style="overflow: hidden;">
	
		<div id="cc" class="easyui-layout" data-options="fit:true">   
  
    		<div data-options="region:'center',title:''" style="padding:0px;">
    			<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">   
<!--     				<div title="Tab1" style="padding:20px;">    -->
<!--        				 tab1     -->
<!--     				</div>    -->
    			  
    		</div>   
    		</div>
<!--    		    <div data-options="region:'east',iconCls:'icon-bell',title:'最新动态',split:true" style="width:400px;"></div>  -->
        </div>  
	</div>
</body>  
</html>