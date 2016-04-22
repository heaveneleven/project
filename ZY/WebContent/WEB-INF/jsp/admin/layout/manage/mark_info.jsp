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
<script src="<%=basePath%>js/admin/manage/stu_mark.js"  type="text/javascript" charset="utf-8"></script>
<div id="log_layout" class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">   
    <div data-options="region:'north',title:'查询条件',iconCls:'icon-find',split:false,collapsible:'false'" style="height:75px;text-align:center;padding:10px;">
    	<div>
    		 <label for="name">学员姓名:</label>   
        	 <input  class="mybox" type="text" name="title" />&nbsp;&nbsp;&nbsp; 
        	 <label for="name">学员学号:</label>   
        	 <input  class="mybox" type="text" name="type" />&nbsp;&nbsp;&nbsp; 
         	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>
         	 &nbsp;&nbsp;
         	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>  
         </div> 
    </div>   
    <div data-options="region:'center',title:'学员成绩列表',split:'false'" >
    	<table id="stu_mark_datagrid"></table>
    	<div id="stu_mark_tb">
			<a href="javascript:void(0);" onclick="modifyMark()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改学员信息</a>
		</div>
    </div>   
</div> 
 <div id="mod_stu_mark"></div> 
