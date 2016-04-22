<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nuaa.utils.UserSessionInfo" %>
<%@ page import="com.nuaa.utils.SessionKey" %>
<%
	UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
%>
<div style="margin-top:20px;margin-left:20px;">
			<% if(sessionInfo != null){
				out.print("<h2>欢迎您："+sessionInfo.getUser_name()+"</h2>");
			}%>
</div>
	    <div class="easyui-panel" style="padding:2px;position: absolute; left: 200px; bottom: 0px;">
        <a href="javascript:void(0);" onclick="mainPage();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-house'">系统首页</a>
        <a href="javascript:void(0);" onclick="myInfo();"class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user_suit'">我的信息</a>
        <a href="javascript:void(0);" onclick="annunciate();"class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bell'">通知通告</a>
    	</div>

<div style="position: absolute; right: 0px; bottom: 0px;">
     <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
</div>

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="myInfo();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoffFun();">退出系统</div>
</div>
<script type="text/javascript" charset="utf-8">
	/**注销*/
	var logoffFun = function() {
		window.location.href="login/logoff.do";
	};
	/**系统首页*/
	function mainPage(){
		
	}	
	/**我的信息*/
	function myInfo(){
		
	}
	/**通知通告*/
	function annunciate(){
		
	}
</script>
