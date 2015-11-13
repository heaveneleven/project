<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div style="margin:20px 0;"></div>
<input type="hidden" id='judge_sex' value='${userInfo.user_sex}'>
<form id="person_modify_form" method="post"  style="padding-top:10px;padding-left:80px;">   
 <table style="text-align:right;">
	<tr>
        <td><label for="name">姓名:</label></td>  
        <td><input class="easyui-validatebox textbox" value='${userInfo.user_name}' name="user_name" data-options="required:true" /></td>   
	</tr>
	<tr>
        <td><label for="email">E-mail:</label></td>   
        <td><input class="easyui-validatebox textbox" value='${userInfo.user_email}' name="user_email" data-options="validType:'email',invalidMessage:'请输入有效邮箱'"></td>  
	</tr>
    <tr>    
        <td><label for="sex">性别:</label></td>   
        <td style="float:left;">
        	<input type="radio" name="user_sex" value="0" />男
            <input type="radio" name="user_sex" value="1"/>女
        </td>   
	</tr>
	<tr> 
        <td><label for="birthday">生日:</label></td>   
        <td><input class="easyui-datebox textbox" data-options="editable:false" name="user_birth" value='${userInfo.user_birth}' /></td>   
	</tr>
	<tr> 
        <td><label for="post">职务:</label></td>   
        <td><input class="textbox" name="post" value='${userInfo.post}' ></td>   
	</tr>
	<tr> 
        <td><label for="depart">所属部门:</label></td>   
        <td><input class="easyui-validatebox textbox" data-options="" name="depart" value='${userInfo.depart}' ></td>   
	</tr>
    <tr>
        <td><label for="home_tel">家庭电话:</label></td>   
        <td><input class="easyui-validatebox textbox" data-options="invalidMessage:'请输入有效家庭电话'" name='user_tel' value='${userInfo.user_tel}' ></td>   
	</tr>
    <tr>    
        <td><label for="mobile">移动电话:</label></td>   
        <td><input class="easyui-validatebox textbox" data-options="invalidMessage:'请输入有效移动电话'" name='user_mobile' value='${userInfo.user_mobile}' ></td>   
	</tr>
    <tr>
        <td><label for="address">家庭住址:</label></td>   
        <td><input class="textbox" name="user_address" value='${userInfo.user_address}' ></td>   
	</tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td style="float:left;"><a href="javascript:void(0)" onclick="subForm()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存修改</a></td></tr>
   </table>
</form>  
	<script type="text/javascript">
	$(function(){
		/**设置男女性别选中*/
		if($("#judge_sex").val()==0){
			$("input[name=user_sex]:eq(0)").attr("checked",'checked');
		}else{
			$("input[name=user_sex]:eq(1)").attr("checked",'checked');
		} 
	});
		function subForm(){
			$('#person_modify_form').form('submit', {    
				url:'person_info/submit.do',   
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