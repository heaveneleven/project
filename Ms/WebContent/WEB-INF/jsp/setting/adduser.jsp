<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加账户</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/common/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"  href="<%=basePath%>css/setting/adduser.css">
<link href="<%=basePath%>css/jquery-ui-themes-1.11.2/themes/redmond/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/jquery-ui-themes-1.11.2/themes/redmond/theme.css" rel="stylesheet" type="text/css"/> 
<script src="<%=basePath%>js/jquery-ui-1.11.2/external/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/jquery-ui-1.11.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
	$(function(){
		//$("#dialog").dialog();	
		$("#subadd").click(function(){
			var dlg=$("#dialog").dialog({
				 resizable : false,
				 modal: true,
				 title : "警告",
				 buttons :{
					  "确定" : function(){
						  $(this).dialog("close");
					  }
				 }
			 }); 
		if($('input[name="username"]').val()==""){
			$("#msg").text("请输入用户名！");
			dlg.dialog();
			return false;
		}
		if($('input[name="password"]').val()=="" && $('input[name="repassword"]').val()==""){
			$("#msg").text("密码不能为空！");
			dlg.dialog();
			//alert("密码不能为空！");
			return false;
		}
		if($('input[name="password"]').val()!=$('input[name="repassword"]').val()){
			$("#msg").text("两次密码不一致！");
			dlg.dialog();
			//alert("两次密码不一致！");
			$('input[name="repassword"]').val("")
			return false;
		}
		$.post("/Ms/setting/subadduser.do",
				{
					username:$('input[name="username"]').val(),
					password:$('input[name="password"]').val(),
					authority:$("input[type='checkbox']").is(':checked') == true ? 1 : 0
				},
				function(result){
				//	var data = eval("(" + result + ")");// 将JSON字符串转换成对象 或者
					var data = JSON.parse(result);
					//alert(data.msg);
					$("#msg").text(data.msg);
					dlg.dialog();
					if(data.success==true){
					$('input[name="username"]').val("");
					$('input[name="password"]').val("");
					$('input[name="repassword"]').val("");
					$("input[type='checkbox']").attr("checked",false); 
					}
					$("#subpassword").val("点击提交");
				});
		});
	});
</script>
<body> 
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/> 
	<div class="main">
				<jsp:include page="/WEB-INF/jsp/setting/menu.jsp"/>  
				<div class="content">
					<div id="dialog" style="display:none">
  						<p id="msg"></p>
				    </div>
					<div class="resetpassword">
					<h3 style="color : #3B5888;">添加账户</h3>
					<table style="color : #3B5888;font-weight : bold;" cellspacing="10px">	
						<tr>
							<td>请输入用户名</td><td><input type="text" name="username"/></td>
						</tr>				
						<tr>
							<td>请输入密码</td><td><input type="password" name="password"/></td>
						</tr>
						<tr>
							<td>请再次输入密码</td><td><input type="password" name="repassword"/></td>
						</tr>
						<tr>
							<td>是否赋予管理员权限</td><td><input type="checkbox" id="authority" name="authority"/></td>
						</tr>
						<tr>
							<td></td><td></td>
						</tr>
					</table>
					<input id="subadd" type="button" value="点击提交"/>
					</div>
				</div>		
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>