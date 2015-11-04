$(function(){
	$("#query").button({
		icons: {
			secondary: "ui-icon-search"
	      }
	}).click(function(){	
		var stcd = $("#chose").jqGrid('getGridParam','selrow'); 
		var start = $("#from").datetimepicker('getDate');
		start=Date.parse(start);
	//	start=getRealDate(start);
		var end = $("#to").datetimepicker('getDate');
	//	end=getRealDate(end);
		end=Date.parse(end);
		if(stcd==null || stcd==""){
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
		if(start=='1970-01-01' || start==null || isNaN(start)){
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
		if(end=='1970-01-01' || end==null || isNaN(end)){
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
        window.location.href="/Ms/message/query/show?stcd="+stcd+"&start="+start+"&end="+end;
	});
	 $("#from").datetimepicker({
	     // defaultDate: "+1w",
		 closeText:"关闭",//关闭选择框的按钮名称  
	     currentText:"今天",
	    // timeOnlyTitle: '时间',
	 	  timeText: '<font style="color:#3B5888;font-weight:bold;">时间</font>',
	 	  hourText: '<font style="color:#3B5888;font-weight:bold;">小时</font>',
	 	  minuteText: '<font style="color:#3B5888;font-weight:bold;">分钟</font>',
	 	  secondText:'<font style="color:#3B5888;font-weight:bold;">秒</font>',
		  monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
          dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
          dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
          dayNamesMin: ['日','一','二','三','四','五','六'],  
          timeFormat: 'HH:mm:ss',//格式化时间
          dateFormat : 'yy-mm-dd',//格式化选择的日期格式
	      onClose: function( selectedDate ) {
	       // $( "#to" ).datetimepicker( "option", "minDate", selectedDate );
	      }
	    });
	    $("#to").datetimepicker({
	     // defaultDate: "+1w",
	      closeText:"关闭",//关闭选择框的按钮名称  
		  currentText:"今天",
		  timeText: '<font style="color:#3B5888;font-weight:bold;">时间</font>',
	 	  hourText: '<font style="color:#3B5888;font-weight:bold;">小时</font>',
	 	  minuteText: '<font style="color:#3B5888;font-weight:bold;">分钟</font>',
	 	 secondText:'<font style="color:#3B5888;font-weight:bold;">秒</font>',
	      monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
          dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
          dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
          dayNamesMin: ['日','一','二','三','四','五','六'],  
          timeFormat: 'HH:mm:ss',//格式化时间
          dateFormat : 'yy-mm-dd',//格式化选择的日期格式
	      onClose: function( selectedDate ) {
	      //  $( "#from" ).datetimepicker( "option", "maxDate", selectedDate );
	      }
	    });
$("#chose").jqGrid({ 
		url:"rainreport/chose.do?sttp=all",
		caption: "请选择要查询的站点",
		height : 'auto',
		datatype: "json", 
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
	var hour=date.getHours();
	if(hour<10)
		hour="0"+hour;
	var minute=date.getMinutes();
	if(minute<10)
		minute="0"+minute;
	var seconds=date.getSeconds();
	var newDate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+seconds;
	return newDate;
}