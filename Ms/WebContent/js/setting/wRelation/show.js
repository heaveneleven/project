$(function(){
	$("#list").jqGrid({ 
		url:"rainreport/chose.do?sttp=RR",
		caption: "设置水库水容关系",
		height : 'auto',
		datatype: "json", 
		colNames:['基站编码', '基站名','测站类型','交换管理单位','导入Excel关系表'],
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
		           {name:'relation',index:'', width:'260',align:'center',
		        	   formatter:function(value,options,row){
		        		   			
		        		            return "<button id='opener' onclick=\"upload('"+row['stcd']+"')\">点击上传</button>";
		        	   }}
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
        
		        });
	
	$( "#dialog" ).dialog({
	      autoOpen: false,
	      height: 180,
	      width:500,
	      modal: true
	      
	    });
});
function upload(stcd){
	var str="<form name='Exform' method='post' enctype='multipart/form-data'>";
		str+="<input type='hidden' name='stcd' value='"+stcd+"'>";
		str+="<input type='file' name='file'/></form>";  
		str+="<br/><button id='opener' onclick='subExc()'>提交</button>";
	  $("#dialog").empty();
	  $("#dialog").append(str);
      $( "#dialog" ).dialog( "open" );
}
function subExc(){
	/**form ajax提交*/
       $("form[name='Exform']").ajaxSubmit({  
           type: 'post',  
           url: "setting/upload.do" ,
           dataType:'json',
           success: function(data){  
               alert(data.msg);  
               $( "#dialog" ).dialog('close');
           },  
           error: function(XmlHttpRequest, textStatus, errorThrown){  
               alert( "error");  
           }  
       });      
}
