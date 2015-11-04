$(function(){
	$("#send").click(function(){
		$("#send").val("登录中···");
		$("#username")
		/*
		$.post("/Ms/login/submit.do",
				{
					username:$("#username").val(),
					password:$("#password").val()
				},
				function(result){
				//	var data = eval("(" + result + ")");// 将JSON字符串转换成对象 或者
					var data = JSON.parse(result);
					if(data && data.success==true){
						location.replace('/Ms/login/home.do');
					}else{
						
					}
				});
			*/
		$("#form").form({
			url : "/Ms/login/submit.do",
			onSubmit :function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$("#send").val("点击登录");	// 如果表单是无效的则还原按钮状态
				}
				return isValid;	// 返回false终止表单提交
			},
			success:function(result){
				var data = JSON.parse(result);
				if(data && data.success==true){
					location.replace('/Ms/login/home.do');
				}else{
					$("#warn").show();
					$("#send").val("点击登录");
					$("#username").val("");
					$("#password").val("");
				}
			}
		});
		$("#form").submit();
	});
});