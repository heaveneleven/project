<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div style="margin:20px 0;"></div>
<form id="person_modify_form" method="post"  style="padding-top:10px;padding-left:80px;">   
 <table style="text-align:right;">
	<tr>
        <td><label for="name">姓名:</label></td>  
        <td><input class="textbox" disabled="disabled" value='${userInfo.stuname}' name="stuname"  /></td>   
	</tr>
	<tr></tr>
	<tr>
        <td><label for="stuno">学号:</label></td>  
        <td><input class="textbox" disabled="disabled"  value='${userInfo.stuno}' name="stuno"  /></td>   
	</tr>
	<tr></tr>
	<tr>    
        <td><label for="sex">性别:</label></td>   
        <td><input class="textbox" disabled="disabled" value='${userInfo.sex}' name="sex" ></td>  
	</tr>
	<tr></tr>
	<tr>
        <td><label for="type">类别:</label></td>  
        <td><input class="textbox" disabled="disabled"  value='${userInfo.type}' name="type"  /></td>   
	</tr>
	<tr></tr>
	<tr>
        <td><label for="classification">文理:</label></td>  
        <td><input class="textbox" disabled="disabled"  value='${userInfo.classification}' name="classification"  /></td>   
	</tr>
	<tr></tr>
	<tr> 
        <td><label for="birthday">出生年月:</label></td>   
        <td><input class="textbox" disabled="disabled" name="birth" value='${userInfo.birth}' /></td>   
	</tr>
	<tr></tr>
	<tr>
        <td><label for="email">E-mail:</label></td>   
        <td><input class="easyui-validatebox textbox" value='${userInfo.email}' name="email" data-options="validType:'email',invalidMessage:'请输入有效邮箱'"></td>  
	</tr>
    <tr></tr>
    <tr>    
        <td><label for="mobile">移动电话:</label></td>   
        <td><input class="easyui-validatebox textbox" data-options="invalidMessage:'请输入有效移动电话'" name='tel' value='${userInfo.tel}' ></td>   
	</tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td style="float:left;"><a href="javascript:void(0)" onclick="subForm()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存修改</a></td></tr>
   </table>
</form>  
	<script type="text/javascript">
		function subForm(){
			$('#person_modify_form').form('submit', {    
				url:'stu/info/person_info/submit.do?stuno='+$("input[name='stuno']").val(),   
			    onSubmit: function(param){//发送前操作
			    	var isValid = $(this).form('validate');
					return isValid;	// 返回false终止表单提交
			    },    
			    success:function(data) {
			    	var data = eval('(' + data + ')');  
	 		        	$.messager.alert('提示',data.msg);    
	 		            
	 		     }  
			});  
		}
	</script>
    <style scoped="scoped">
        .textbox{
            height:20px;
            margin:0;
            padding:0 2px;
            box-sizing:content-box;
        }
    </style>