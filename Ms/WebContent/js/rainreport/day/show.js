$(function(){
	/**将基站名字符串分离转换为数组*/
	var stnms=$("#stnms").val().split(",");
	var stcds=$("#stcds").val().split(",");
	$("#daylist").empty();
	var head="<tr><th style='background:#7DCDFF;width:220px;'>时间</th>";
	for(var index in stnms){
		head+="<th>"+stnms[index]+"</th>";
	}
	head+="</tr>";
	$("#daylist").append(head);
	$.ajax({
		url : "rainreport/day/show.do?stcds="+$("#stcds").val()+"&date="+$("#date").val(),
		dataType :'json',
		//error : alert("erro"),
		success : function(data){
			var row="";
			$.each(data,function(index,item){
				var itime = item['first'];
				row = "<tr><td>" + itime + "</td>";
				nowTime = new Date();
				itemTime = new Date(Date.parse(itime.replace(/-/g, "/")));
				for(var i in stcds){
					if (itemTime > nowTime) {
						row += "<td><font style='color: red;'>-</font></td>";
					} else {
						row += "<td>" + parseFloat(item[stcds[i]]).toFixed(1)
								+ "</td>";
					}
				}
				row+="</tr>";		
				$("#daylist").append(row);
			});
			getTotal();
		}
	});
	$("#export").button({
		icons: {
			secondary: "ui-icon-disk"
	      }
	}).click(function(){
		window.location.href="export/dayRainReport.do?stcds="+$("#stcds").val()+"&date="+$("#date").val();
	});
});
function getTotal(){
	$.ajax({
		url : "rainreport/day/getDayDrp.do?stcds="+$("#stcds").val()+"&date="+$("#date").val(),
		dataType : 'json',
		success : function(data){
			var row="<tr><td>累计降水量</td>";
			$.each(data,function(index,item){
				row+="<td>"+parseFloat(item['drp']).toFixed(1)+"</td>";
			});
				row+="</tr>"
				$("#daylist").append(row);
		}
	});
}