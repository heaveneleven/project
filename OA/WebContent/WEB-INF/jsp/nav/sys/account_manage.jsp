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
<script src="<%=basePath%>js/nav/sys/sys_set/account_manager.js"  type="text/javascript" charset="utf-8"></script>
<div id="log_layout" class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">   
    <div data-options="region:'north',title:'查询条件',iconCls:'icon-find',split:false,collapsible:'false'" style="height:75px;text-align:center;padding:10px;">
    	<div>
    		 <label for="name">用户账号:</label>   
        	 <input  class="mybox" type="text" name="title" />&nbsp;&nbsp;&nbsp; 
        	 <label for="name">用户密码:</label>   
        	 <input  class="mybox" type="text" name="type" />&nbsp;&nbsp;&nbsp; 
        	 <label for="tm">创建时间：</label>   
        	 <input class="easyui-datebox mybox" data-options="editable:false" name="tm_from">
        	 <label for="tm">至</label>   
        	 <input class="easyui-datebox mybox" data-options="editable:false" name="tm_to">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>
         	 &nbsp;&nbsp;
         	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>  
         </div> 
    </div>   
    <div data-options="region:'center',title:'账号列表',split:'false'" >
    	<table id="account_manager_datagrid"></table>
    	<div id="account_manager_tb">
			<a href="javascript:void(0);" onclick="addAccount()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加账号</a>
			<a href="javascript:void(0);" onclick="delAccount()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除账号</a>
			<a href="javascript:void(0);" onclick="modifyAccount()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改账号</a>
			<a href="javascript:void(0);" onclick="checkAccount()" class="easyui-linkbutton" data-options="iconCls:'icon-eye',plain:true">查看账号</a>
		</div>
    </div>   
</div> 
 <div id="add_account"></div>
 <div id="modify_account"></div> 
 <div id="check_account"></div> 