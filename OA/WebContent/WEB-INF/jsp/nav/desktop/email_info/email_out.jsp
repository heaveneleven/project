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
<script src="<%=basePath%>js/nav/desktop/email_info/email_out/email_out.js"  type="text/javascript" charset="utf-8"></script>
<div id="mail_out_layout" class="easyui-layout" data-options="fit:true,split:false">   
    <div data-options="region:'north',title:'查询条件',iconCls:'icon-find'" style="height:75px;text-align:center;padding:10px;">
    	 <div>
    		 <label for="name">邮件主题:</label>   
        	 <input  class="textbox" type="text" name="title" />&nbsp;&nbsp;&nbsp; 
        	 <label for="sender">收件人:</label>   
        	 <input  class="textbox" type="text" name="recipient" />&nbsp;&nbsp;&nbsp;
        	 <label for="tm">发件日期:</label>   
        	 <input class="easyui-datebox textbox" data-options="editable:false" name="tm">&nbsp;&nbsp;&nbsp;
         	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>  
         </div> 
    </div>   
    <div data-options="region:'center',split:false">
    	<table id="email_out_datagrid"></table>
    	<div id="email_out_tb">
			<a href="javascript:void(0);" onclick="addEmail()" class="easyui-linkbutton" data-options="iconCls:'icon-email_add',plain:true">新建邮件</a>
			<a href="javascript:void(0);" onclick="delEmail()" class="easyui-linkbutton" data-options="iconCls:'icon-email_delete',plain:true">删除邮件</a>
		</div>
    </div>   
</div>  
<style>
        .textbox{
            height:21px;
            width:100px;
            margin:0;
            padding:0 2px;
            box-sizing:content-box;
        }
</style>

  
