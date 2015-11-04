$(function(){
	$("#list").jqGrid({ 
		url:"setting/getUsers.do",
		caption: "登陆用户管理",
		height : 'auto',
		datatype: "json", 
		colNames:['用户名','密码','是否管理员','操作'],
		colModel:[
		           {name:'username',index:'username', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           {name:'repassword',index:'repassword', width:'110',align:'center', editable: true,
		        	   formatter:function(value,options,row){
		        			   return '';
		           }},
		           {name:'authority',index:'authority', width:'60',align:'center', editable: true,edittype:"checkbox",editoptions: {value:"是:否"},
		        	   formatter:function(value,options,row){
		        		   if(value==0)
		        			   return '否';
		        		   else
		        			   return '是';
		           }},
		           {name:'act',index:'act', width:'150',sortable:false}
		         ],
		         rownumbers:true,//添加左侧行号
		 		viewrecords: true, //是否显示行数 
		 		sortable:false,
		 		jsonReader:{
		             id: "id",//设置返回参数中，表格ID的名字为blackId
		             repeatitems : false
		         },
		         pager:$('#gridPager'),
		         emptyrecords: "无数据",
		         rowNum : 16,	//rowList:[10,20,30]//可调整每页显示的记录数 
		         gridComplete: function(){ 
		         	var ids = jQuery("#list").jqGrid('getDataIDs'); 
		         	for(var i=0;i < ids.length;i++){
		         		var cl = ids[i]; 
		         		be = "&nbsp;<a href='javascript:void(0);' onclick=\"edit('"+cl+"');\">修改</a>|"; 
		         		se = "<a href='javascript:void(0);' onclick=\"save('"+cl+"');\">保存</a>|"; 
		         		ce = "<a href='javascript:void(0);' onclick=\"jQuery('#list').restoreRow('"+cl+"');\">取消</a>|"; 
		         		de = "<a href='javascript:void(0);' onclick=\"del('"+cl+"');\">删除</a>";
		         		jQuery("#list").jqGrid('setRowData',ids[i],{act:be+se+ce+de}); } },
		         editrules:{number:true, required:true},
		         editurl: "setting/userManager/save.do"
		         
		 		        });
	$( "#dialog" ).dialog({
	      autoOpen: false,
	      height: 280,
	      width:500,
	      modal: true
	      
	    });
	
	jQuery("#list")
	.navGrid('#gridPager',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#gridPager',{
	   caption:"添加用户", 
	   buttonicon:"ui-icon-plus", 
	   onClickButton: function(){ 
		   $( "#dialog" ).dialog( "open" );
	   }, 
	      position:"last"
	});
		 $("#subadd").click(function(){
			 if($('input[name="username"]').val()==""){
				 	alert("请输入用户名！");
					return false;
				}
				if($('input[name="password"]').val()=="" && $('input[name="repassword"]').val()==""){
					alert("密码不能为空！");
					return false;
				}
				if($('input[name="password"]').val()!=$('input[name="repassword"]').val()){
					alert("两次密码不一致！");
					$('input[name="repassword"]').val("");
					return false;
				}
				$.post("/Ms/setting/subadduser.do",
						{
							username:$('input[name="username"]').val(),
							password:$('input[name="password"]').val(),
							authority:$("input[type='checkbox']").is(':checked') == true ? 1 : 0
						},
						function(result){
							var data = JSON.parse(result);
							alert(data.msg);
							if(data.success==true){
							$('input[name="username"]').val("");
							$('input[name="password"]').val("");
							$('input[name="repassword"]').val("");
							$("input[type='checkbox']").attr("checked",false); 
							}
							$( "#dialog" ).dialog( "close" );
							$("#list").trigger("reloadGrid");
						});
				});
		 });	
		function edit(rowud){
			jQuery('#list').editRow(rowud);
			return true;
		}
		 function save(rowud){
		 	jQuery("#list").jqGrid('saveRow', rowud,
		             {
		                 successfunc: function (response) {
		                 	var data=eval('(' + response.responseText + ')');
		 				    alert(data['msg']);
		 				   $("#list").trigger("reloadGrid");
		                 	return data['success'];
		                 }
		             });
		 }
		 /**删除用户*/
		 function del(rowud){
			 if(confirm("是否确定删除该登陆用户信息？")){
				 $.post(
							"setting/delUser.do/?id="+rowud,
							function(result) {
								var data = JSON.parse(result);
									alert(data['msg']);
									$("#list").trigger("reloadGrid");
								});
			 }
		 }
		 