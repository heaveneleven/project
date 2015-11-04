$(function() {
	$(".img").hide();
	$(".head").css("height","35px");
	var map = new BMap.Map("container", {mapType:BMAP_HYBRID_MAP}); // 创建地图实例
	//var point = new BMap.Point(110.926282, 27.436991); // 创建点坐标
	// map.setCurrentCity("北京");
	if($("#lgtd").val()==0 && $("#lttd").val()==0){
		map.centerAndZoom("北京",12); 
	}else{
		var point = new BMap.Point($("#lgtd").val(),$("#lttd").val());
		map.centerAndZoom(point,12); 
	}
	map.addControl(new BMap.NavigationControl()); // 添加控件：缩放地图的控件，默认在左上角；
	map.addControl(new BMap.MapTypeControl()); // 添加控件：地图类型控件，默认在右上方；
	map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放

	/** 从后台加载站点的坐标信息 */
			$.post(
					"/Ms/realtime/position.do",
					function(result) {
						var data = JSON.parse(result);
						if (data) {
									$.each(
											data,
											function(index, item) {
												// console.info(item['lttd']);
												/**如果当前的坐标为null则取消添加位置*/
												if(item['lgtd']==null || item['lttd']==null)
													return false;
												var point = new BMap.Point(
														item['lgtd'],
														item['lttd']);
												var marker = new BMap.Marker(
														point); // 创建标注
												map.addOverlay(marker);// 添加标注

												/** 添加标签 */
												var opts = {
													position : point, // 指定文本标注所在的地理位置
													offset : new BMap.Size(10,
															-30)
												// 设置文本偏移量
												};
												var label = new BMap.Label(
														item['stnm'], opts); // 创建文本标注对象
												label.setStyle({
													color : "red",
													fontSize : "12px",
													height : "15px",
													lineHeight : "16px",
													fontFamily : "微软雅黑"
												});
												map.addOverlay(label);
												/** *********************************** */
												/** 添加点击标注点事件响应 */
												var str="";
												if(item['sttp']=="PP"){
													str="当日雨量："+(item['todaydrp']==null? 0 : item['todaydrp']);
												}else if(item['sttp']=="ZZ"){
													str="当前水位："+(item['nowz']==null? 0 : item['nowz']);
												}else if(item['sttp']=='ZQ' || item['sttp']=='RR'){
													str+="当日雨量："+(item['todaydrp']==null? 0 : item['todaydrp']);
													str+="<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
													str+="当前水位："+(item['nowz']==null? 0 : item['nowz']);
												}
												var sContent = "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color:red'>"
													+ item['stnm']
													+ "</h4>"
													+ "<p style='margin:0;line-height:1.5;font-size:14px;text-indent:2em'>"
													+ str
													+ "</p>"
													+ "</div>";

												marker.addEventListener(
																"click",
																function() {
																	
																	var info = new BMap.InfoWindow(sContent);
																	this.openInfoWindow(
																					info,
																					point);
																});
											});

						} else {
							alert("加载站点位置信息失败！");
						}
					});
			
			/**为水域划定区域边框**/
			/**从数据库中加载坐标*/
			/*
			$.post("",
					function(result) {
						var data = JSON.parse(result);
						var points=[];
						if (data) {
							$.each(data,function(index, item){
								if(item['lgtd']==null || item['lttd']==null)
									return false;
								var point = new BMap.Point(
										item['lgtd'],
										item['lttd']);
								points.push(point);
							});
							var curve = new BMapLib.CurveLine(points, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象
							map.addOverlay(curve); //添加到地图中
						}
			});
			*/	

			
	// 定义点击监听
	// marker.addEventListener("click", function(){
	// alert("您点击了标注");
	// });
	// var opts = {
	// width : 80, // 信息窗口宽度
	// height: 30, // 信息窗口高度
	// title : "XXX实时雨量" // 信息窗口标题
	// }
	// var info=new BMap.InfoWindow("标记此处",opts);

	// 定义鼠标划过事件
	/*
	 * marker.addEventListener("click",function(){ var info=new
	 * BMap.InfoWindow(sContent); this.openInfoWindow(info,point); });
	 */
	// 定义鼠标离开事件
	/*
	 * marker.addEventListener("mouseout",function(){
	 * 
	 * });
	 */
});

