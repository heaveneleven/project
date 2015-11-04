$(function(){
	
	$("#query").button({
		icons: {
			secondary: "ui-icon-search"
	      }
	}).click(function(){	
		var start = $("#from").datepicker('getDate');
		start=getRealDate(start);
		var end = $("#to").datepicker('getDate');
		end=getRealDate(end);
		
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
		$("#start").val(start);
		$("#end").val(end);
		/**获取总页数*/
		 $.getJSON("message/count/pages.do?start="+$("#start").val()+"&end="+$("#end").val()+"&page="+$("#page").val()+"&rows=16",
	    		 function (data) {
			 	$("#totalpages").text(data);
		 });
		getData();
      //  window.location.href="rainreport/hour/show?stcds="+stcds+"&start="+start+"&end="+end+"&space="+space;
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
	/**上一页*/
	$("#preText").click(function(){
		if($("#page").val()=='1'){
			alert("已经是第一页！");
		}else{
			$("#chosepage").val("");
			$("#page").val(parseInt($("#page").val())-1);
			getData();
		}
	});
	/**下一页*/
	$("#nextText").click(function(){
		if($("#page").val()==$("#totalpages").text()){
			alert("已经是最后一页！");
		}else{
			$("#chosepage").val("");
			$("#page").val(parseInt($("#page").val())+1);
			getData();
		}
	});
	/**首页*/
	$("#first").click(function(){
		$("#chosepage").val("");
		$("#page").val('1');
		getData();
	});
	/**尾页*/
	$("#last").click(function(){
		$("#chosepage").val("");
		$("#page").val($("#totalpages").val());
		getData();
	});
	/**页跳转*/
	$("#chosepage").click(function(){
		var pagenum=$("#pagenum").val().trim();
		
		 var re = /^[1-9]+[0-9]*]*$/;
		 if (!re.test(pagenum) || pagenum>$("#totalpages").text())  
		    {  
		        alert("请输入正确页码"); 
		        $("#pagenum").val("");
		        return false;  
		     }  
		 
		$("#page").val($("#pagenum").val());
		$("#chosepage").val("");
		getData();
	});
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
function getData(){
	/**将基站名字符串分离转换为数组*/
	var stnms=$("#stnms").val().split(",");

	$("#messagecount").empty();
	var head="<tr><th style='background:#7DCDFF;width:220px;'>时间</th>";
	for(var index in stnms){
		head+="<th>"+stnms[index]+"</th>";
	}
	head+="</tr>";
	$("#messagecount").append(head);
	//return false;
	$.ajax({
		url : "message/count/show.do?start="+$("#start").val()+"&end="+$("#end").val()+"&page="+$("#page").val()+"&rows=16",
		dataType :'json',
		success : function(data){
			var stcds=$("#stcds").val().split(",");
			var row="";
			$.each(data,function(index,item){
				row="<tr><td>"+getDayDate(item['date'])+"</td>";
				for(var i in stcds){
					row+="<td>"+item[stcds[i]]+"</td>";
				}
				row+="</tr>";		
				$("#messagecount").append(row);
			});
		}
	});
	$("#nowpage").text($("#page").val());
}
function getDayDate(value){
	var data=value.split(" ");
	return data[0];
	}