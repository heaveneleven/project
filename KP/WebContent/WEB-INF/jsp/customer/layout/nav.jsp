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
<script src="<%=basePath%>js/nav/customer/nav.js" type="text/javascript" charset="utf-8"></script>
<div id="aa" class="easyui-accordion"  data-options="fit:true,animate:false,iconCls:'icon-house'">   
    <div title="FBA换标业务" data-options="iconCls:'icon-reload'" style="padding:10px;">   
		 <ul id="change_business">
		 	
		 </ul>        
    </div>   
    <div title="FBA中转业务" data-options="iconCls:'icon-time_go'" style="padding:10px;">   
		<ul id="transfer_business">
			
		</ul>    
    </div>   
    <div title="海外仓业务" data-options="iconCls:'icon-anchor'" style="padding:10px;">   
		<ul id="overseas_business">
			
		</ul>    
    </div>
    <div title="FBA退货业务" data-options="iconCls:'icon-undo'" style="padding:10px;">   
		<ul id="return_business">
		
		</ul>
    </div>  
</div>  

