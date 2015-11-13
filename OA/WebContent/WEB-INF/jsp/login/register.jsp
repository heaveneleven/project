<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>OA用户登录</title>
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
	<div id="win" class="easyui-window" title="My Window" style="width:400px;height:400px"   
        data-options="title:'新用户注册',
		iconCls:'icon-user_add',
	    modal:true,
	    closable:false,
	    resizable:false,
	    collapsible:false,
	    maximizable:false,
	    minimizable:false,
	    shadow:true">   
    <div class="easyui-layout" data-options="fit:true">   
        <div data-options="region:'north'" style="height:300px">
        	<form id='reg_form'>
			<table style="margin-left:105px;margin-top:30px;text-align:right">
			<tr>
				<td><label for="login_name">登录名:</label></td>
				<td><input class="easyui-validatebox textbox" type="text" name="login_name" data-options="required:true,missingMessage:'请输入登录名'" /> </td>
			</tr>
			<tr></tr>
			<tr>
				<td><label for="user_name">用户名:</label></td>
				<td><input class="easyui-validatebox textbox" type="text" name="user_name" data-options="required:true,missingMessage:'请输入用户名'" /> </td>
			</tr>
			<tr></tr>
			<tr>
				<td><label for="name">密&nbsp;&nbsp;码:</label></td>
				<td><input class="easyui-validatebox textbox" type="password" name="login_password" data-options="required:true,missingMessage:'请输入密码'" /> </td>
			</tr>
			<tr></tr>
			<tr>
				<td><label for="name">重复密码:</label></td>
				<td><input class="easyui-validatebox textbox" type="password" name="re_login_password" data-options="required:true,missingMessage:'请再次输入密码'" /> </td>
			</tr>
		</table>
		</form>
        </div>   
        <div data-options="region:'center'" style="text-align:center"> 
            <br/>  
            <a id="user_reg" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">注册</a> 
			&nbsp;&nbsp;
			<a id="reset" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a> 
			&nbsp;&nbsp;
		<a id="reg_cancel" href="login/welcome" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a> 
        </div>   
    </div>   
</div>  
		
	<script type="text/javascript">
	/**点击重置*/
	$("#reset").click(function(){
		$(".textbox").val('');
	});
	
	/**登录按钮*/
	$("#user_reg").click(function(){
		$("#reg_form").form('submit', {    
			url:'register/submit.do',   
		    onSubmit: function(){//发送前操作
		    	if($("input[name='login_password']").val()!=$("input[name='re_login_password']").val()){
		    		$.messager.alert('警告','<strong><center>两次密码不一致！</center></strong>'); 
		    		$("input[name='re_login_password']").val('');
		    		return false;
		    	}
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
  		        	$.messager.alert('提示','<strong><center>'+data.msg+'</center></strong>');    
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