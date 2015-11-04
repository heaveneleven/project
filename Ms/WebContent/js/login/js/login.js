// JavaScript Document
//支持Enter键登录
		document.onkeydown = function(e){
			if($(".bac").length==0)
			{
				if(!e) e = window.event;
				if((e.keyCode || e.which) == 13){
					var obtnLogin=document.getElementById("submit_btn")
					obtnLogin.focus();
				}
			}
		}

    	$(function(){
    		/**记住密码*/
    		if ($.cookie("rmbUser") == "true"){ 
    		document.getElementById("readerpw").checked=true; 
    		$("#username").val($.cookie("username")); 
    		$("#password").val($.cookie("password")); 
    		} 
    		/**在页面加载完后让用户名输入框获得焦点*/
    		$('input[name="username"]').focus();
			//提交表单
			$('#submit_btn').click(function(){
				show_loading();
				//var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
				if($('#username').val() == ''){
					show_err_msg('请输入用户名！');	
					$('#username').focus();
				}else if($('#password').val() == ''){
					show_err_msg('请输入密码！');
					$('#password').focus();
				}else{
					$.post("/Ms/login/submit.do",
							{
								username:$('input[name="username"]').val(),
								password:$('input[name="password"]').val()
							},
							function(result){
							//	var data = eval("(" + result + ")");// 将JSON字符串转换成对象 或者
								var data = JSON.parse(result);
								if(data && data.success==true){
									clickpw();
									show_msg('登录成功！  正在为您跳转...','/');
									//location.replace('/Ms/login/home.do');
									  location.replace('/Ms/realtime/table');
								}else if(data && data.success==false){
									show_err_msg(data.msg);
									$('input[name="password"]').val("");
								}else{
									show_err_msg("连接数据库出错！");
								}
							});
					
				}
			});
		});
    	/**保存账号密码*/
    	function clickpw(){ 
		if ($("#readerpw").attr("checked")){ 
		var username = $("#username").val(); 
		var password = $("#password").val(); 
		$.cookie("rmbUser", "true", { expires: 30 }); // 存储一个带7天期限的 cookie 
		$.cookie("username", username, { expires: 30 }); // 存储一个带7天期限的 cookie 
		$.cookie("password", password, { expires: 30 }); // 存储一个带7天期限的 cookie 
		} else{ 
			$.cookie("rmbUser", "false", { expires: -1 }); 
			$.cookie("username", '', { expires: -1 }); 
			$.cookie("password", '', { expires: -1 }); 
		   } 
		} 