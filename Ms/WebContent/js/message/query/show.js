$(function(){
	$("#from").text(getRealDate(parseInt($("#start").val())));
	$("#to").text(getRealDate(parseInt($("#end").val())));
	$("#messagelist").jqGrid({ 
		url : "message/query/show.do?stcd="+$("#stcd").val()+"&start="+getRealDate(parseInt($("#start").val()))+"&end="+getRealDate(parseInt($("#end").val())),
		caption: "实时列表信息",
		height : 'auto',
		datatype: "json", 
		colNames:['时间','原始报文'],
		colModel:[
		          {name:'tm',index:'tm', width:'150',align:'center',
		        	  formatter:function(value,options,row){
		        		  return getRealDate(value);
		        	  }
		          },
		           {name:'msg',index:'msg', width:'500',align:'center',
		        	   formatter:function(value,options,row){
		        		   if(value==null)
		        			   return '0';
		        		   else
		        		   return $.trim(value);
		           }}
		           
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
        
		        });
	
	jQuery("#list")
	.navGrid('#gridPager',{edit:false,add:false,del:false,search:true})
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
	if(seconds<10)
		seconds="0"+seconds;
	var newDate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+seconds;
	return newDate;
}

