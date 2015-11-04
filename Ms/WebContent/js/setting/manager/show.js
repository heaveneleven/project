$(function(){
	$("#list").jqGrid({ 
		url:"setting/manager/getManager.do",
		caption: "管理人员设置",
		height : 'auto',
		datatype: "json", 
		colNames:['基站名','管理员','部门','职位','性别','联系电话','备注','操作'],
		colModel:[
		           {name:'stnm',index:'stnm', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'name',index:'name', width:'90',align:'center',editable:true,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           
		           {name:'depart',index:'depart', width:'90',align:'center',editable:true,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'position',index:'position', width:'90',align:'center',editable:true,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		        	   }
		           },
		           {name:'sex',index:'sex', width:'60',align:'center',editable: true,edittype:"select",editoptions:{value:"m:男;f:女"},
		        	   formatter:function(value,options,row){
		        		   if(value=='m'){
		        			   return '男';
		        		   }else if(value=='f'){
		        			   return '女';
		        		   }else{
		        			   return '';
		        		   }
		        	   }
		           },
		           {name:'tel',index:'tel', width:'120',align:'center',editable:true,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		        	   }
		           },
		           {name:'remark',index:'remark', width:'180',align:'center',editable:true,edittype:"textarea", editoptions:{rows:"2",cols:"10"},
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		        	   }
		           },
		           {name:'act',index:'act', width:'105',sortable:false}
		         ],
		rownumbers:true,//添加左侧行号
		viewrecords: true, //是否显示行数 
		sortable:false,
		jsonReader:{
            id: "stcd",//设置返回参数中，表格ID的名字为blackId
            repeatitems : false,
        },
        pager:$('#gridPager'),
        emptyrecords: "无数据",
        rowNum : 16,	//rowList:[10,20,30]//可调整每页显示的记录数 
        gridComplete: function(){ 
        	var ids = jQuery("#list").jqGrid('getDataIDs'); 
        	for(var i=0;i < ids.length;i++){
        		var cl = ids[i]; 
        		be = "&nbsp;<a href='javascript:void(0);' onclick=\"jQuery('#list').editRow('"+cl+"');\">修改</a>|"; 
        		se = "<a href='javascript:void(0);' onclick=\"save('"+cl+"');\">保存</a>|"; 
        		ce = "<a href='javascript:void(0);' onclick=\"jQuery('#list').restoreRow('"+cl+"');\">取消</a>"; 
        		jQuery("#list").jqGrid('setRowData',ids[i],{act:be+se+ce}); } },
        editrules:{number:true, required:true},
        editurl: "setting/manager/save.do"
        
		        });
	
	
});
function save(rowud){
	jQuery("#list").jqGrid('saveRow', rowud,
            {
                successfunc: function (response) {
                	var data=eval('(' + response.responseText + ')');
				    alert(data['msg']);
				    location.reload();
                	return data['success'];
                }
            });
}