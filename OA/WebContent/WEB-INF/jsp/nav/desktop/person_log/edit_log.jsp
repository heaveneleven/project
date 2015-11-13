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
    	<form id="edit_log_form" method="post">
    	    <input type="hidden" name="log_id" value="${log.log_id}" />
    		<input type="hidden" id="log_type" value="${log.log_type}" />
    	<table>
    		<tr>
    			<td><label for="tm">日期:</label></td>   
        		<td><input class="easyui-datebox " value="${log.tm}" data-options="editable:false,required:true,missingMessage:'请输入日期',invalidMessage:'请输入日期'" name="tm"  /></td>   
    		</tr>
    		<tr>
    			<td><label for="log_title">标题:</label></td>  
        		<td><input  name="log_title" value="${log.log_title}" /></td>   
    		</tr>
    		<tr>
    			<td><label for="log_type">日志类型:</label></td>  
        		<td>
        		<select id="log_m_type" class="easyui-combobox"  name="log_type" style="width:135px;">   
   					 <option value="0">个人日志</option>   
   					 <option value="1">工作日志</option>   
			    </select> 
			    </td> 
    		</tr>
    	</table>
    	</form>
    	<textarea name="log_editor_edit" id="log_editor_edit" rows="20" cols="80">${log.log_content}</textarea>
    </div>

	<div data-options="region:'south',split:false" style="height:60px;background:#95B8E7;text-align:center;padding:10px;">
		<a href="javascript:void(0);" onclick="saveLog()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="cancelLog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> 
</div>  
<script>
            CKEDITOR.replace( 'log_editor_edit',{
            	language : 'zh-cn',
                uiColor : '#E0ECFF'	
            });
            /**保存日志*/
            function saveLog(){
            	var data = CKEDITOR.instances.log_editor_edit.getData();
            	$('#edit_log_form').form({    
            	    url:'person_log/edit_log/submit.do',    
            	    onSubmit: function(param){    
            	    	var isValid = $(this).form('validate');
            			//if (!isValid){
            			//	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
            			//} 
            			param.log_content =  data;
            			return isValid;	// 返回false终止表单提交
            	    },    
            	    success:function(data){ 
            	    	var data = eval('(' + data + ')');  
            	    	$.messager.alert('提示',data.msg);
            	    	if(data.success==true){
            	    		$("#modify_log").dialog('close');
            	    		$("#person_log_datagrid").datagrid('reload');
            	    	}
            	    }    
            	});    
            	$('#edit_log_form').submit();  
            }
            /**取消*/
            function cancelLog(){
            	$("#edit_log").dialog('close');
            }
</script>