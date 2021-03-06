$(function(){
	var date=new Date();
	var counts=date.getTime();
	/**转换为水利时间，比正常时间慢8小时，以早晨8点为凌晨时间*/
	/**刚打开界面时显示当前日期*/
	$("#choseDate").val(getNowDate(parseInt(counts)-8*60*60*1000));
	
	$("#query").button({
		icons: {
			secondary: "ui-icon-search"
	      }
	}).click(function(){	
		var stcds = $("#chose").jqGrid('getGridParam','selarrrow'); 
		var date=$("#choseDate").datepicker('getDate');
		date=getRealDate(date);
		
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
		if(date=='1970-01-01' || date==null){
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
        window.location.href="/Ms/rainreport/day/show?stcds="+stcds+"&date="+date;
	});
	
	/**启用jqueryUI 日期控件*/
	$("#choseDate").datepicker({
		 
		  showOn:'both',
		  buttonImage: 'images/date.png',   // 按钮图标 
		  buttonImageOnly: true,  
	      showButtonPanel:true,//是否显示按钮面板 
	      closeText:"关闭",//关闭选择框的按钮名称  
	      currentText:"今天",
	      yearSuffix: '年', //年的后缀  
          showMonthAfterYear:true,//是否把月放在年的后面  
          defaultDate:+0,//默认日期，当前  
        //  minDate:'2015-02-05',//最小日期  
      //    maxDate : getTime(0),//最大日期  设置为当日
          monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
          dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
          dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
          dayNamesMin: ['日','一','二','三','四','五','六'],  
          dateFormat : 'yy-mm-dd'
	});
	
$("#chose").jqGrid({ 
		url:"rainreport/chose.do?sttp=PP",
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
	.navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false})
	.navButtonAdd('#gridPager',{
	   caption:"导出Excel", 
	   buttonicon:"ui-icon-disk", 
	   onClickButton: function(){ 
		   //导出Excel表格
		   /**注意了：用jquery ajax提交是无法达到目的的！！！*/
		   window.location.href="export/table.do";
	   }, 
	      position:"last"
	})
});
/**将系统日期格式转换为yy-mm-dd格式*/
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
/**将毫秒数转换为yy-mm-dd格式日期*/
function getNowDate(counts){
	var date = new Date(counts);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	if(month<10)
		month="0"+month;
	if(day<10)
		day="0"+day;
	return year+"-"+month+"-"+day;
}
