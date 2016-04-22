<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Kai Pro系统用户登录</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/default/easyui.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/icon.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/demo/demo.css" rel="stylesheet" type="text/css"/> 
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="win" class="easyui-window" style="width:400px;height:200px"   
        data-options="title:'用户登录',
		iconCls:'icon-key',
	    modal:true,
	    closable:false,
	    resizable:false,
	    collapsible:false,
	    maximizable:false,
	    minimizable:false,
	    shadow:true">   
    <div class="easyui-layout" data-options="fit:true">   
        <div data-options="region:'north'" style="height:110px">
        	<form id='login_form'>
		<table style="margin-left:105px;margin-top:20px;">
			<tr>
				<td><label for="name">用户名:</label></td>
				<td><input class="easyui-validatebox textbox" type="text" name="login_name" data-options="required:true,missingMessage:'请输入用户名'" /> </td>
			</tr>
			<tr>
				<td></td><td></td>
			</tr>
			<tr>
				<td></td><td></td>
			</tr>
			<tr>
				<td><label for="name">密&nbsp;&nbsp;码:</label></td>
				<td><input class="easyui-validatebox textbox" type="password" name="login_password" data-options="required:true,missingMessage:'请输入密码'" /> </td>
			</tr>

		</table>
		</form>
        </div>   
        <div data-options="region:'center'" style="text-align:center"> 
        <br/>  
            <a id="customer_login" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">客户登录</a> 
		&nbsp;&nbsp;
		<a id="admin_login" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">管理员登录</a> 
		&nbsp;&nbsp;
		<a id="reset" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a> 
		&nbsp;&nbsp;
		<a id="user_reg_btn" href="login/register" class="easyui-linkbutton" data-options="iconCls:'icon-user_add'">注册</a>     
        </div>   
    </div>   
</div>  
 
	<script type="text/javascript">
	/**点击重置*/
	$("#reset").click(function(){
		$("input[name='login_name']").val('');
		$("input[name='login_password']").val('');
	});
	/**管理员登录按钮*/
	$("#admin_login").click(function(){
		$("#login_form").form('submit', {    
			url:'login/admin/validate.do',   
		    onSubmit: function(){//发送前操作    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
 		   			window.location.href="login/admin/home/index";
 		        }else{    
 		        	$.messager.alert('警告','<strong><center>'+data.msg+'</center></strong>');    
 		        }    
 		     }  
		});  
	});
	/**客户登录按钮*/
	$("#customer_login").click(function(){
		$("#login_form").form('submit', {    
			url:'login/customer/validate.do',   
		    onSubmit: function(){//发送前操作    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
 		   			window.location.href="login/customer/home/index";
 		        }else{    
 		        	$.messager.alert('警告','<strong><center>'+data.msg+'</center></strong>');    
 		        }    
 		     }  
		});  
	});
	</script>
	<style scoped="scoped">
        .textbox{
            height:20px;
            margin:0;
            padding:0 2px;
            box-sizing:content-box;
        }
    </style>
</body>
</html>