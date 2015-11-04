$(function(){
	$("#query").button({
		icons: {
			secondary: "ui-icon-search"
	      }
	}).click(function(){	
		var stcds = $("#chose").jqGrid('getGridParam','selarrrow'); 
		var start = $("#from").datepicker('getDate');
		start=getRealDate(start);
		var end = $("#to").datepicker('getDate');
		end=getRealDate(end);
		if(stcds==null || stcds==""){
			$("#msg").text("请选择站点！");
			var dlg = $("#dialog").dialog({
				resizable : false,
				modal : true,
				buttons:{
					"确定" : function(){
						$(this).dialog('close');
					}
				}
			});
			dlg.dialog();
			return false;
		}
		if(start=='1970-01-01' || start==null){
			$("#msg").text("请选择起始时间！");
			var dlg = $("#dialog").dialog({
				resizable : false,
				modal : true,
				buttons:{
					"确定" : function(){
						$(this).dialog('close');
					}
				}
			});
			dlg.dialog();
			return false;
		}	
		if(end=='1970-01-01' || end==null){
			$("#msg").text("请选择结束时间！");
			var dlg = $("#dialog").dialog({
				resizable : false,
				modal : true,
				buttons:{
					"确定" : function(){
						$(this).dialog('close');
					}
				}
			});
			dlg.dialog();
			return false;
		}			
        window.location.href="/Ms/chart/water/show?stcds="+stcds+"&start="+start+"&end="+end;
	});
	 $("#from").datepicker({
	     // defaultDate: "+1w",
	      monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
          dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
          dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
          dayNamesMin: ['日','一','二','三','四','五','六'],  
          dateFormat : 'yy-mm-dd',//格式化选择的日期格式
	      onClose: function( selectedDate ) {
	        $( "#to" ).datepicker( "option", "minDate", selectedDate );
	      }
	    });
	    $("#to").datepicker({
	     // defaultDate: "+1w",
	      monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
          dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
          dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
          dayNamesMin: ['日','一','二','三','四','五','六'],  
          dateFormat : 'yy-mm-dd',//格式化选择的日期格式
	      onClose: function( selectedDate ) {
	        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
	      }
	    });
$("#chose").jqGrid({ 
		url:"rainreport/chose.do?sttp=ZZ",
		caption: "请选择要查询的站点",
		height : 'auto',
		datatype: "json", 
		multiselect: true,
		colNames:['基站编码', '基站名','测站类型','交换管理单位'],
		colModel:[
		           {name:'stcd',index:'stcd', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           {name:'stnm',index:'stnm', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }},
		           {name:'type',index:'type', width:'90',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
		           }}, 
		           
		           {name:'locality',index:'locality', width:'77',align:'center',
		        	   formatter:function(value,options,row){
		        		   return $.trim(value);
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
	
	jQuery("#list")
	.navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
});
/**转换日期格式*/
function getRealDate(old){
	var date=new Date(old);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<10)
		month="0"+month;
	var day=date.getDate();
	if(day<10)
		day="0"+day;
	var newDate=year+"-"+month+"-"+day;
	return newDate;
}