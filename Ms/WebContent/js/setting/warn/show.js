$(function(){
	$("#list").jqGrid({ 
		url:"setting/table.do?type=warn",
		caption: "水位预警设置",
		height : 'auto',
		datatype: "json", 
		colNames:['基站编码', '基站名','测站类型','交换管理单位','水位预警值','是否预警','操作'],
		colModel:[
		           {name:'stcd',index:'stcd', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           {name:'stnm',index:'stnm', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'sttp',index:'sttp', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           
		           {name:'locality',index:'locality', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'warn',index:'warn', width:'70',align:'center',editable:true,
		        	   formatter:function(value,options,row){
		        		   if(value==null)
		        			     return '0.00';
		        		    else
		        		    	 return parseFloat($.trim(value)).toFixed(2);
		        	   }
		           },
		           {name:'valid',index:'valid', width:'55',align:'center', editable: true,edittype:"checkbox",editoptions: {value:"是:否"},
		        	   formatter:function(value,options,row){
		        		   if(value==0)
		        			   return '否';
		        		   else
		        			   return '是';
		           }},
		           {name:'act',index:'act', width:'105',sortable:false}
		         ],
		         rownumbers:true,//添加左侧行号
		 		viewrecords: true, //是否显示行数 
		 		sortable:false,
		 		jsonReader:{
		             id: "stcd",//设置返回参数中，表格ID的名字为blackId
		             repeatitems : false
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
		         editurl: "setting/warn/save.do"
		         
		 		        });
		 	
		 	
		 });
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