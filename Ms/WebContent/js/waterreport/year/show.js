$(function(){
	$("#yearlist").empty();
	var head="<tr><th style='background:#7DCDFF;width:220px;'>日期</th>";
	for(var index=1;index<=12;index++){
		head+="<th>"+index+"月</th>";
	}
	head+="</tr>";
	$("#yearlist").append(head);
	$.ajax({
		url : "waterreport/year/show.do?stcd="+$("#stcd").val()+"&date="+$("#date").val(),
		dataType :'json',
		success : function(data){
			/**定义二维数组来保存月日表格，初始化里面数据为0*/
			var arr=new Array();
			for(var i=1;i<=32;i++){
				arr[i]=new Array();
				for(var j=1;j<=12;j++){
					arr[i][j]='<font style="color:red;">-</font>';
				}
			}
			/**将后台的Json对象中含有的雨量及其日期对号入座*/
			$.each(data,function(index,item){
				arr[item['day']][item['month']]=item['z'];
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
			/**将数组还原为table*/	
			for(var d=1;d<=31;d++){
				var row="<tr><td><b>"+d+"日</b></td>";
				for(var m=1;m<=12;m++){
					if(arr[d][m]>=0)
						row+="<td>"+parseFloat(arr[d][m]).toFixed(2)+"</td>";
					else
					row+="<td>"+arr[d][m]+"</td>";
				}
				row+="</tr>";
					$("#yearlist").append(row);	
			}
		}
	});
	
		$("#export").button({
			icons: {
				secondary: "ui-icon-disk"
		      }
		}).click(function(){
			window.location.href="export/yearWaterReport.do?stcd="+$("#stcd").val()+"&date="+$("#date").val();
		});
});
