$(function(){
	 var chart = new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'column',
	            margin: 75,
	            options3d: {
	                enabled: true,
	                alpha: 15,
	                beta: 15,
	                depth: 50,
	                viewDistance: 25
	            }
	        },
	        title: {
	            text: '单站日降水量柱状图'
	        },
	        subtitle: {
	            text: '<b>'+$("#date").val()+"--"+$("#name").val()+'</b>'
	        },
	        plotOptions: {
	            column: {
	                depth: 25
	            }
	        },
	        xAxis: {
	            categories: [
	                '08:00',
	                '09:00',
	                '10:00',
	                '11:00',
	                '12:00',
	                '13:00',
	                '14:00',
	                '15:00',
	                '16:00',
	                '17:00',
	                '18:00',
	                '19:00',
	                '20:00',
	                '21:00',
	                '22:00',
	                '23:00',
	                '00:00',
	                '01:00',
	                '02:00',
	                '03:00',
	                '04:00',
	                '05:00',
	                '06:00',
	                '07:00'
	            ]
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '降水量(mm)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">'+'降水量'+': </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        series: [{
	           

	        }],
	        
	        lang: {
	        	printChart:'打印图表',
	        	downloadPNG:'下载JPEG 图片',
	        	downloadJPEG:'下载JPEG文档',
	        	downloadPDF:'下载PDF 文件',
	        	downloadSVG:'下载SVG 矢量图',
	        	contextButtonTitle:'下载图片'
	        	}
	        	
	    });
	 /**获取该日每小时的降雨量*/
	 $.ajax({
			url : "rainreport/day/show.do?stcds="+$("#stcd").val()+"&date="+$("#date").val(),
			dataType :'json',
			success : function(data){
				var rd=[];
				$.each(data,function(index,item){
					rd.push(item[$("#stcd").val()]);
				});
				chart.series[0].setData(rd);
				//chart.series[0].setData([129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 29.9, 71.5, 106.4]);  
			}
		});
	 
	    $('#R0').on('change', function(){
	        chart.options.chart.options3d.alpha = this.value;
	        showValues();
	        chart.redraw(false);
	    });
	    $('#R1').on('change', function(){
	        chart.options.chart.options3d.beta = this.value;
	        showValues();
	        chart.redraw(false);
	    });

	    function showValues() {
	        $('#R0-value').html(chart.options.chart.options3d.alpha);
	        $('#R1-value').html(chart.options.chart.options3d.beta);
	    }
	    showValues();
});