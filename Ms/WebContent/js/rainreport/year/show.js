$(function(){
	$("#yearlist").empty();
	var head="<tr><th style='background:#7DCDFF;width:220px;'>日期</th>";
	for(var index=1;index<=12;index++){
		head+="<th>"+index+"月</th>";
	}
	head+="</tr>";
	$("#yearlist").append(head);
	$.ajax({
		url : "rainreport/year/show.do?stcd="+$("#stcd").val()+"&date="+$("#date").val(),
		dataType :'json',
		success : function(data){
			/**定义二维数组来保存月日表格，初始化里面数据为0*/
			var arr=new Array();
			for(var i=1;i<=32;i++){
				arr[i]=new Array();
				for(var j=1;j<=12;j++){
					arr[i][j]=0;
				}
			}
			/**将后台的Json对象中含有的雨量及其日期对号入座*/
			$.each(data,function(index,item){
				arr[item['day']][item['month']]=parseFloat(item['drp']).toFixed(1);
			});
			/**将日历中不存在的日期去掉，表示不存在*/
			arr[30][2]="--";
			arr[31][2]="--";
			//润年2月29天，非润年28天
			date=parseInt($("#date").val());
			if(!((date%4==0) && (date%100==0 || date%400!=0)))
			arr[29][2]="--";
			arr[31][4]="--";
			arr[31][6]="--";
			arr[31][9]="--";
			arr[31][11]="--";
			var iyear = $("#date").val();
			nowTime = new Date();
			/**将数组还原为table*/	
			for(var d=1;d<=31;d++){
				var row="<tr><td><b>"+d+"日</b></td>";
				for(var m=1;m<=12;m++){
					if(arr[d][m]=='--'){
						row+="<td>"+arr[d][m]+"</td>";
					}else if(nowTime.getFullYear() > iyear
							|| (nowTime.getFullYear() == iyear && nowTime.getMonth() + 1 > m)
							||(nowTime.getMonth() + 1 == m && nowTime.getDate() >= d)){
						row+="<td>"+parseFloat(arr[d][m]).toFixed(1)+"</td>";
					}else{
						row+="<td><font style='color: red;'>-</font></td>";
					}
				}
				row+="</tr>";
					$("#yearlist").append(row);	
			}
			monthCal();
		}
	});
	
		$("#export").button({
			icons: {
				secondary: "ui-icon-disk"
		      }
		}).click(function(){
			window.location.href="export/yearRainReport.do?stcd="+$("#stcd").val()+"&date="+$("#date").val();
		});
});
/**获取月降水累计*/
function monthCal(){
	$.ajax({
		url : "rainreport/year/getMonthInty.do?stcd="+$("#stcd").val()+"&date="+$("#date").val(),
		dataType :'json',
		success : function(data){
			var row="<tr><td>月降水累计</td>";
			var row2="<tr><td>月降水天数</td>";
			var marr=new Array();
			var marr2=new Array();
			for(var i=1;i<=12;i++){
				marr[i]=0.00;
				marr2[i]=0.00;
			}
			$.each(data,function(index,item){
				marr[item['months']]=parseFloat(item['drp']).toFixed(1);
				marr2[item['months']]=item['daysformonth'];
			});
			for(var j=1;j<=12;j++){
			    row+="<td>"+parseFloat(marr[j]).toFixed(1)+"</td>";
			    row2+="<td>"+parseFloat(marr2[j]).toFixed(0)+"</td>";
			}
				row+="</tr>"
				row2+="</tr>"
				$("#yearlist").append(row);	
				$("#yearlist").append(row2);	
				yearTotal();
		}});
}
/**获取降水最值*/
function getMax(){
	$.ajax({
		url : "rainreport/year/getMaxForYear.do?stcd="+$("#stcd").val()+"&date="+$("#date").val(),
		dataType :'json',
		success : function(data){
			var row1="<tr><td>最大日降雨量</td>";
			var row2="<tr><td>最大降雨量日</td>"
				$.each(data,function(index,item){
					var alldrp=parseFloat(item['drp']).toFixed(1);
					var date;
					if(alldrp==0)
						date='--';
					else
						date=item['years']+"-"+item['months']+"-"+item['dayss'];	
					row1+="<td colspan='12'>"+alldrp+"</td>"
					row2+="<td colspan='12'>"+date+"</td>";
				});
			row1+="</tr>";
			row2+="</tr>";
			$("#yearlist").append(row1);	
			$("#yearlist").append(row2);	
		}});
}
function yearTotal(){
	$.ajax({
		url : "rainreport/year/getYearInty.do?stcd="+$("#stcd").val()+"&date="+$("#date").val(),
		dataType :'json',
		success : function(data){
			var row1="<tr><td>全年降雨量</td>";
			var row2="<tr><td>全年降雨天数</td>"
				$.each(data,function(index,item){
					row1+="<td colspan='12'>"+parseFloat(item['yeardrp']).toFixed(1)+"</td>";
					row2+="<td colspan='12'>"+item['daysforyear']+"</td>"
				});
			row1+="</tr>";
			row2+="</tr>";
			$("#yearlist").append(row1);	
			$("#yearlist").append(row2);	
			getMax();
		}});
}