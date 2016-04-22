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
<script src="<%=basePath%>js/nav/admin/nav.js" type="text/javascript" charset="utf-8"></script>
<div id="aa" class="easyui-accordion"  data-options="fit:true,animate:false,iconCls:'icon-house'">   
    <div title="订单管理" data-options="iconCls:'icon-report'" style="padding:10px;">   
		 <ul id="order_manager">
		 	
		 </ul>        
    </div>   
    <div title="库存管理" data-options="iconCls:'icon-package'" style="padding:10px;">   
		<ul id="repertory_manager">
			
		</ul>    
    </div>   
    <div title="发货管理" data-options="iconCls:'icon-car'" style="padding:10px;">   
		<ul id="deliver_manager">
			
		</ul>    
    </div>
    <div title="系统管理" data-options="iconCls:'icon-folder_wrench'" style="padding:10px;">   
		<ul id="sys_manager">
		
		</ul>
    </div>  
</div>  

