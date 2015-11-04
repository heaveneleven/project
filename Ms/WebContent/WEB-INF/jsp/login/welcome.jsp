<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登陆页面</title>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/login/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>css/login/login.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/login/style.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>css/login/supersized.css" rel="stylesheet" type="text/css"/> 
<script src="<%=basePath%>js/jquery/jquery-1.8.2.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/jquery/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/tooltips.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/login.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/supersized.3.2.7.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/supersized-init.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/supersized.3.2.7.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/login/js/scripts.js" type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<img src="images/logo.png" >
			</div>
		
			<div class="login_form">
				<form id="login_form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">用户名：</label> 
						<input id="username" value="" name="username" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in">
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="j_captcha" class="t">验证码：</label> -->
<!-- 						 <input id="j_captcha" name="j_captcha" type="text" class="form-control x164 in"> -->
<!-- 						<img id="captcha_img" alt="点击更换" title="点击更换" src="images/captcha.jpeg" class="m"> -->
<!-- 					</div> -->
					<div class="form-group">
						<label class="t"></label>
						<label for="j_remember" class="m">
						<input id="readerpw" type="checkbox" value="true">&nbsp;记住密码</label>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2014 - 2015 <a href="http://www.njquanshui.com" target="_blank">南京全水电子技术有限公司</a></div>
	</div>
</div>

<div style="text-align:center;">
</div>
</body>
</html>