$(function(){
	/**点击重置*/
	$("#reset").click(function(){
		$("input[name='login_name']").val('');
		$("input[name='login_password']").val('');
	});
	/**学员登录按钮*/
	$("#stu_login").click(function(){
		$("#login_form").form('submit', {    
			url:'login/stu_login.do',   
		    onSubmit: function(){//发送前操作    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
  		        	window.location.href="login/stu/index";
 		        }else{    
 		        	$.messager.alert('警告','<strong><center>'+data.msg+'</center></strong>');    
 		        }    
 		     }  
		});
	});
	/**管理员登录按钮*/
	$("#admin_login").click(function(){
		$("#login_form").form('submit', {    
			url:'login/admin_login.do',   
		    onSubmit: function(){//发送前操作   
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
 		   			window.location.href="login/admin/index";
 		        }else{    
 		        	$.messager.alert('警告','<strong><center>'+data.msg+'</center></strong>');    
 		        }    
 		     }  
		}); 
	});
	/**干部登录按钮*/
	$("#sir_login").click(function(){
		$("#login_form").form('submit', {    
			url:'login/validate.do',   
		    onSubmit: function(){//发送前操作    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data) {
		    	var data = eval('(' + data + ')');  
  		        if(data.success == true){    
 		   			window.location.href="login/index";
 		        }else{    
 		        	$.messager.alert('警告','<strong><center>'+data.msg+'</center></strong>');    
 		        }    
 		     }  
		}); 
	});
});