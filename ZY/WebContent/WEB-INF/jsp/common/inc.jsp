<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link href="<%=basePath%>plugin/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet"> --%>
<%-- <script src="<%=basePath%>plugin/jquery-2.1.1/jquery.min.js" type="text/javascript" charset="utf-8"></script> --%>
<%-- <script src="<%=basePath%>plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script> --%>
base href="<%=basePath%>">
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/default/easyui.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/themes/icon.css" rel="stylesheet" type="text/css"/> 
<link href="<%=basePath%>plugin/jquery-easyui-1.4.3/demo/demo.css" rel="stylesheet" type="text/css"/> 
<script src="<%=basePath%>plugin/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>