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
    	<form id="add_log_form" method="post">
    	<table>
    		<tr>
    			<td><label for="tm">日期:</label></td>   
        		<td><input class="easyui-datebox " data-options="editable:false,required:true,missingMessage:'请输入日期',invalidMessage:'请输入日期'" name="tm"  /></td>   
    		</tr>
    		<tr>
    			<td><label for="log_title">标题:</label></td>  
        		<td><input  name="log_title" /></td>   
    		</tr>
    		<tr>
    			<td><label for="log_type">日志类型:</label></td>  
        		<td>
        		<select id="cc" class="easyui-combobox" data-options="" name="log_type" style="width:135px;">   
   					 <option value="0">个人日志</option>   
   					 <option value="1">工作日志</option>   
			    </select> 
			    </td> 
    		</tr>
    	</table>
    	</form>
    	<textarea name="log_editor" id="log_editor" rows="20" cols="80"></textarea>
    </div>

	<div data-options="region:'south',split:false" style="height:60px;background:#95B8E7;text-align:center;padding:10px;">
		<a href="javascript:void(0);" onclick="saveLog()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="cancelLog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> 
</div>  
<script>
            CKEDITOR.replace( 'log_editor',{
            	language : 'zh-cn',
                uiColor : '#E0ECFF'	
            });
            /**保存日志*/
            function saveLog(){
            	var data = CKEDITOR.instances.log_editor.getData();
            	$('#add_log_form').form({    
            	    url:'person_log/add_log/submit.do',    
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
            	    	$("#add_log").dialog('close');
            	    	$("#person_log_datagrid").datagrid('reload');
            	    }    
            	});    
            	$('#add_log_form').submit();  
            }
            /**取消*/
            function cancelLog(){
            	$("#add_log").dialog('close');
            }
</script>