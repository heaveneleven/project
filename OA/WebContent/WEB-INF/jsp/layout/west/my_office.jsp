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
<script src="<%=basePath%>js/nav/desktop/desktop.js" type="text/javascript" charset="utf-8"></script>
<div id="aa" class="easyui-accordion"  data-options="fit:true,animate:false,iconCls:'icon-house'">   
    <div title="我的桌面" data-options="iconCls:'icon-mydesktop'" style="padding:10px;">   
		 <ul id="desktop_tree"></ul>        
    </div>   
    <div title="信息管理" data-options="iconCls:'icon-message'" style="padding:10px;">   
		<ul id="info_tree"></ul>    
    </div>   
    <div title="通讯管理" data-options="iconCls:'icon-telephone'" style="padding:10px;">   
		<ul id="connect_tree"></ul>    
    </div>
    <div title="文档管理" data-options="iconCls:'icon-page'" style="padding:10px;">   
		<ul id="doc_tree"></ul>
    </div>  
    <div title="任务管理" data-options="iconCls:'icon-date'" style="padding:10px;">   
		<ul id="job_tree"></ul>
    </div> 
    <div title="行政管理" data-options="iconCls:'icon-book'" style="padding:10px;">   
		<ul id="depart_tree"></ul>
    </div>  
</div>  

