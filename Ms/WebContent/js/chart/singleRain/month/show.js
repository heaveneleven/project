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
	            text: '单站月降水量柱状图'
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
	            categories: []
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
	        	},
	        	//点击事件	
	        plotOptions: {
	        	series: {
	        	cursor: 'pointer',
	        	events: {
	        	click: function(e) {
	        	//alert('X轴的值：'+e.point.category+' 指标的名称:'+this.name);
	        	var day=e.point.category.replace(/[^0-9]/ig,"");;
	        	if(day<10)
	        		day='0'+day;
	        	 window.location.href="chart/singleRain/show?stcd="+$("#stcd").val()+"&date="+$("#date").val()+'-'+day;
	        	}
	        	  }
	        	     }
	        	},
	    });
	 /**获取该日每小时的降雨量*/
	 $.ajax({
			url : "rainreport/month/show.do?stcds="+$("#stcd").val()+"&date="+$("#date").val(),
			dataType :'json',
			success : function(data){
				var rd=[];
				var xdata=[];
				var day=1;
				$.each(data,function(index,item){
					rd.push(item[$("#stcd").val()]);
					xdata.push(day+"日");
					day++;
				});
				chart.series[0].setData(rd);
				chart.xAxis[0].setCategories(xdata); 
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