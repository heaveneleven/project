$(function(){
	var seriesOptions = [];
    var seriesCounter = 0;
 	var names=$("#stnms").val().split(",");
	var stcds=$("#stcds").val().split(",");
	var stcd2name=[];
	for(var n in names){
		stcd2name[stcds[n]]=names[n];
	}
   
     createChart = function () {

         $('#container').highcharts('StockChart', {

             chart: {
                 type: 'area'
             },
             rangeSelector : {
                 selected : 4
             },
             title: {
                 text: '<b>水情图形查询</b>'
             },

//             subtitle: {
//                 text: ''
//             },
             yAxis: {
                 labels: {
                     formatter: function () {
                         return (this.value+'m');
                     }
                 } 
             /*,
                 plotLines : [{
                     value : 0.7419,
                     color : 'red',
                     dashStyle : 'shortdash',
                     width : 2,
                     label : {
                         text : 'Last quarter maximum'
                     }
                 }]
                 */
             },
             xAxis : {  
            	 tickPixelInterval: 150,//x轴上的间隔
            	 minRange:1000*60*60,
            //	 tickInterval:1000*60*5,
          	    type : 'datetime',  
            	    dateTimeLabelFormats : {  
            	        second : '%Y-%m-%d<br/>%H:%M:%S',  
            	        minute : '%Y-%m-%d<br/>%H:%M',  
            	        hour : '%Y-%m-%d<br/>%H:%M',  
            	        day : '%Y<br/>%m-%d',  
            	        week : '%Y<br/>%m-%d',  
            	        month : '%Y-%m',  
            	        year : '%Y'  
            	       } 
            	    } ,
            	   
             plotOptions: {
                 series: {
                    // compare: 'percent'
                	 cursor: 'pointer'
                 }
             },

             tooltip: {
            	 xDateFormat: '%Y-%m-%d:%H:%M,%A',
                 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}m</b><br/>',
                 valueDecimals: 2
             },
             rangeSelector: {

            	 buttons: [{//定义一组buttons,下标从0开始

            	 type: 'week',

            	 count: 1,

            	 text: '1周'

            	 },{

            	 type: 'month',

            	 count: 1,

            	 text: '1月'

            	 }, {

            	 type: 'month',

            	 count: 3,

            	 text: '3月'

            	 }, {

            	 type: 'month',

            	 count: 6,

            	 text: '6月'

            	 }, {

            	 type: 'ytd',

            	 text: '1季度'

            	 }, {

            	 type: 'year',

            	 count: 1,

            	 text: '1年'

            	 }, {

            	 type: 'all',

            	 text: '全部'

            	 }],

            	 buttonTheme: {

            	 width: 36,

            	 height: 16,

            	 padding: 1,

            	 r: 0,

            	 stroke: '#68A',

            	 zIndex: 7

            	 },

            	 inputDateFormat: '%Y-%m-%d',

            	 inputEditDateFormat: '%Y-%m-%d',

            	 selected: 1//表示以上定义button的index,从0开始

            	 },

             series: seriesOptions
         });
     };
     Highcharts.setOptions({
    	 lang:{
    	 printChart:'打印图表',
	     downloadPNG:'下载JPEG 图片',
	     downloadJPEG:'下载JPEG文档',
	     downloadPDF:'下载PDF 文件',
	     downloadSVG:'下载SVG 矢量图',
	     contextButtonTitle:'下载图片',
	     rangeSelectorFrom:"日期",
	     rangeSelectorTo:"至",
	     rangeSelectorZoom:"范围",
	   //  shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'], 
	     months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
	     weekdays:['星期日',  '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
    	 }

    	 });
     var first=Date.parse($("#start").val());
     var last=Date.parse($("#end").val());
     var virtData=[];
     while(first<last){
     virtData.push([first,0]);
     first+=1000*60*5;
     }
     seriesOptions[0]={
    		 name : '海平面',
    		 data : virtData
     };

     $.each(stcds, function (i, stcd) {

     $.getJSON("chart/water/show.do?stcd="+stcd+"&start="+$("#start").val()+"&end="+$("#end").val(),
    		 function (data) {
    	 var xdata=[];
    	 $.each(data,function(n,item){
    		 xdata.push([item['tm'],item['z']]);
    	 });
         seriesOptions[i+1] = {
             name: stcd2name[stcds[i]],
             data: xdata
         };

         // As we're loading the data asynchronously, we don't know what order it will arrive. So
         // we keep a counter and create the chart when all the data is loaded.
         seriesCounter += 1;

         if (seriesCounter === stcds.length) {
             createChart();
         }
     });
 });
});