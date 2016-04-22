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
<div id="mod_stu" class="easyui-layout" data-options="fit:true">  
    <div data-options="region:'center',split:false" style="padding:30px;">
    	<form id="mod_stu_form" method="post">
    	<table>
    		<tr>
    			<td><label for="stu_no">学号:</label></td>   
        		<td><input class="easyui-validatebox textbox" disabled="disabled" data-options="required:true,missingMessage:'请输入学号'" value='${userInfo.stuno}' /></td>
    		</tr>
    		<tr>
    			<td><label for="stu_name">姓名:</label></td>  
    			<td><input class="easyui-validatebox textbox" data-options="required:true,missingMessage:'请输入姓名'" value='${userInfo.stuname}' /></td>
    		</tr>
    		<tr>
    			<td><label for="birth">出生年月:</label></td>   
        		<td><input class="easyui-datebox " data-options="editable:false,required:true,missingMessage:'请输入日期',invalidMessage:'请输入日期'" value='${userInfo.birth}'  /></td>   
    		</tr>
    		<tr>
    			<td><label for="stu_type">学员类型:</label></td>  
        		<td>
        		<select id="ccc" class="easyui-combobox" data-options="" name="type" style="width:135px;">   
   					 <option value="0">士兵生</option>   
   					 <option value="1">青年生</option>   
			    </select> 
			    </td> 
    		</tr>
    		<tr>
    		<td><label for="classification">类别:</label></td>  
        		<td>
        		<select id="ccv" class="easyui-combobox" data-options="" name="classification" style="width:135px;">   
   					 <option value="0">文科</option>   
   					 <option value="1">理科</option>   
			    </select> 
			    </td> 
    		</tr>
    	</table>
    	</form>
    </div>

	<div data-options="region:'south',split:false" style="height:60px;background:#95B8E7;text-align:center;padding:10px;">
		<a href="javascript:void(0);" onclick="saveStu()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="cancelStu()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> 
</div>  
<script>
            /**保存修改*/
            function saveStu(){
            	$('#add_stu_form').form({    
            	    url:'admin/manage/mod_stu_submit.do?stuno'+$("input[name='stuno']").val(),    
            	    onSubmit: function(param){    
            	    	var isValid = $(this).form('validate');
            			return isValid;	// 返回false终止表单提交
            	    },    
            	    success:function(data){ 
            	    	var data = eval('(' + data + ')');  
            	    	$.messager.alert('提示',data.msg);
            	    	$("#mod_stu").dialog('close');
            	    	$("#stu_info_datagrid").datagrid('reload');
            	    }    
            	});    
            	$('#mod_stu_form').submit();  
            }
            /**取消*/
            function cancelStu(){
            	$("#mod_stu_info").dialog('close');
            }
</script>