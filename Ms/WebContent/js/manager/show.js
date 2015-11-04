$(function(){
	$("#list").jqGrid({ 
		url:"setting/manager/getManager.do",
		caption: "管理人员列表",
		height : 'auto',
		datatype: "json", 
		colNames:['基站名','管理员','部门','职位','性别','联系电话','备注'],
		colModel:[
		           {name:'stnm',index:'stnm', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'name',index:'name', width:'90',align:'center' ,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           
		           {name:'depart',index:'depart', width:'90',align:'center' ,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'position',index:'position', width:'90',align:'center' ,
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		        	   }
		           },
		           {name:'sex',index:'sex', width:'60',align:'center',
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
		           {name:'remark',index:'remark', width:'280',align:'center',editable:true,edittype:"textarea", editoptions:{rows:"2",cols:"10"},
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		        	   }
		           }
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
        rowNum : 16
		        });
});
