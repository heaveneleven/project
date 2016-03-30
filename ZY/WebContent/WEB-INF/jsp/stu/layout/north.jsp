<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nuaa.utils.StuSessionInfo" %>
<%@ page import="com.nuaa.utils.SessionKey" %>
<%
	StuSessionInfo sessionInfo=(StuSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
%>
		<div>
			<div style="margin-top:40px;position: absolute; right: 30px;">
				<% if(sessionInfo != null){
					out.print("<h3>姓名："+sessionInfo.getStuname()+"&nbsp&nbsp&nbsp学号："+sessionInfo.getStuno()+"</h3>");
				}%>
			</div>
		</div>
	    <div class="easyui-panel" style="padding:2px;position: absolute; left: 200px; bottom: 0px;">
        <a href="javascript:void(0);" onclick="myInfo()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user'">我的信息</a>
<!--         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-building_go'">成绩查询</a> -->
        <a href="javascript:void(0);" onclick="majChose()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-report'">专业选择</a>
    	</div>

<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="$.changeTheme('default');" title="default">default</div> 
	<div onclick="$.changeTheme('gray');" title="gray">gray</div>
	<div onclick="$.changeTheme('metro');" title="metro">metro</div>
	<div onclick="$.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="$.changeTheme('black');" title="black">black</div>
	<div class="menu-sep"></div>
	<div onclick="$.changeTheme('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="$.changeTheme('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="$.changeTheme('metro-green');" title="metro-green">metro-green</div>
	<div onclick="$.changeTheme('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="$.changeTheme('metro-red');" title="metro-red">metro-red</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="$('#loginDialog').dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoffFun();">退出系统</div>
</div>
<script type="text/javascript" charset="utf-8">
	var logoffFun = function() {
		window.location.href="login/logoff.do";
	};
	/**我的日常办公*/
	function myInfo(){
		$("#main_layout").layout('remove','west');
		$("#main_layout").layout('add',{    
		    region: 'west', 
		    iconCls:"icon-user",
		    title: '我的信息',   
		    width:200,
		    split: false,
		    href:'stu/layout/west/myInfo'
		}); 
	}
	function majChose(){
		$("#main_layout").layout('remove','west');
		$("#main_layout").layout('add',{    
		    region: 'west', 
		    iconCls:"icon-report",
		    title: '专业选择',
		    width:200,
		    split: false,
		    href:'stu/layout/west/majorChose'
		}); 
	}
</script>
