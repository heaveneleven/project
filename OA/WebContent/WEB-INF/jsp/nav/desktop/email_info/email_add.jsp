<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String contextPath = request.getContextPath();
%>
<script src="<%=basePath%>plugin/ckeditor/ckeditor.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>plugin/ckeditor/config.js" type="text/javascript" charset="utf-8"></script>
<div id="mail_in_layout" class="easyui-layout" data-options="fit:true,split:false">   
    <div data-options="region:'north',title:'收件人信息',iconCls:'icon-user'" style="height:120px;text-align:left;padding:10px;">
	 	<table>
        	<tr>
        		<td><label for="title">主题:</label></td>   
        		<td><input class="easyui-validatebox" type="text" name="title" data-options="required:true,missingMessage:'请输入邮件主题'" /></td>   
       		</tr>
       		<tr>
	        	<td><label for="email">收件人:</label></td>   
        		<td><input class="easyui-validatebox" type="text" name="recipient" data-options="required:true,missingMessage:'请输入收件人'" /></td>
        	</tr>   
          </table>	
    </div>   
    <div data-options="region:'center',title:'邮件内容',iconCls:'icon-page_green'">
    	<form>
            <textarea name="editor" id="editor" rows="20" cols="80">
                	请在此处输入邮件内容
            </textarea>
            <script>
            CKEDITOR.replace( 'editor',{
            	language : 'zh-cn',
                uiColor : '#E0ECFF'	
            });
            </script>
        </form>
    </div>   
</div>  
   