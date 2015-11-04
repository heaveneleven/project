<%@ page import="com.njqs.utils.UserInfo"%>
<%@ page import="com.njqs.utils.SessionKey"%>
<%
	UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
			SessionKey.UserInfoKey);
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="menu">
	<div class="title1">系统设置</div>
	<div class="menuContent">
		<table>
			<tr>
				<td><div class="title2">账号管理></div></td>
			</tr>
			<tr>
				<td><div class="title3">
						<a href="setting/resetpassword">&nbsp;密码修改</a>
						<!-- 判断是否具有管理员权限 -->
						<%
							if (userInfo != null && userInfo.getUser().getAuthority() != null
									&& "1".equals(userInfo.getUser().getAuthority().toString())) {
								//out.print("&nbsp<a href='setting/adduser'>添加账户</a>");
								out.print("&nbsp;&nbsp;<a href='setting/usermanager'>账户管理</a>");
							}
						%>

					</div></td>
			</tr>

			<tr>
				<td><hr width=200 size=1 color=#5151A2 align=center noshade></td>
			</tr>
			<%
				if (userInfo != null && userInfo.getUser().getAuthority() != null
						&& "1".equals(userInfo.getUser().getAuthority().toString())) {
					out.print("<tr><td><div  class='title2'>站点配置></div></td></tr>");
					out.print("<tr><td><div class='title3'>&nbsp<a href='setting/wRelation/show'>水库水容关系</a>");
					out.print("&nbsp;<a href='setting/vRelation/show'>水位流速关系</a></div></td></tr>");
					out.print("<tr><td><div class='title3'>&nbsp<a href='setting/baseHigh/show'>水位基值设置</a>");
					out.print("&nbsp;<a href='setting/warn/show'>水位预警设置</a></div></td></tr>");
					out.print("<tr><td><div class='title3'>&nbsp<a href='setting/map/show'>实时地图设置</a>");
					out.print("&nbsp;</div></td></tr>");
					out.print("<tr><td><hr width=200 size=1 color=#5151A2 align=center noshade></td></tr>");
					out.print("<tr><td><div  class='title2'>人员管理></div></td></tr>");
					out.print("<tr><td><div class='title3'>&nbsp;<a href='setting/manager/show'>人员管理设置</a>");
					out.print("&nbsp;</div></td></tr>");
					out.print("<tr><td><hr width=200 size=1 color=#5151A2 align=center noshade></td></tr>");
				}
				out.print("<tr><td><div  class='title2'>手机应用></div></td></tr>");
				out.print("<tr><td><div class='title3'>&nbsp;<a href='android/file/download.do'>手机应用下载</a>");
				out.print("&nbsp;</div></td></tr>");
			%>
		</table>
	</div>
</div>
