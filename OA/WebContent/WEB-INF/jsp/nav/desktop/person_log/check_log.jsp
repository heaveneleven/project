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
<div id="cc" class="easyui-layout" data-options="fit:true">  
    <div data-options="region:'center',split:false" style="padding:0px;">
    		<input type="hidden" id="log_type" value="${log.log_type}" />
    	<table>
    		<tr>
    			<td><label>日期:</label></td>   
        		<td><label>${log.tm}</label></td>   
    		</tr>
    		<tr>
    			<td><label>标题:</label></td>  
        		<td><label>${log.log_title}</label></td>   
    		</tr>
    		<tr>
    			<td><label>日志类型:</label></td>  
        		<td><label>${log_type}</label></td>
    		</tr>
    	</table>
    	<textarea name="log_editor_check" id="log_editor_check" rows="20" cols="80">${log.log_content}</textarea>
    </div>

	<div data-options="region:'south',split:false" style="height:60px;background:#95B8E7;text-align:center;padding:10px;">
		<a href="javascript:void(0);" onclick="cancelLog()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
	</div> 
</div>  
<script>
            CKEDITOR.replace( 'log_editor_check',{
            	language : 'zh-cn',
                uiColor : '#E0ECFF',
                readOnly : true
            });
            /**取消*/
            function cancelLog(){
            	$("#check_log").dialog('close');
            }
</script>