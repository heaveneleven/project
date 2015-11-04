$(function() {
	var map = new BMap.Map("container", {mapType:BMAP_HYBRID_MAP}); // 创建地图实例
	//var point = new BMap.Point(110.926282, 27.436991); // 创建点坐标
	if($("#clgtd").val()==0 && $("#clttd").val()==0){
		map.centerAndZoom("北京",12); 
	}else{
		var point = new BMap.Point($("#clgtd").val(),$("#clttd").val());
		map.centerAndZoom(point,12); 
	}
	//map.addControl(new BMap.NavigationControl()); // 添加控件：缩放地图的控件，默认在左上角；
	map.addControl(new BMap.MapTypeControl()); // 添加控件：地图类型控件，默认在右上方；
	//map.centerAndZoom(point, 12); // 初始化地图，利用城市名来加载
	map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	//map.setDefaultCursor("url('images\icon_gcoding.png')");
	map.setDefaultCursor("crosshair");
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
													height : "20px",
													lineHeight : "20px",
													fontFamily : "微软雅黑"
												});
												map.addOverlay(label);
												/** *********************************** */
												
											});

						} else {
							alert("加载站点位置信息失败！");
						}
					});
					/**单击地图将经纬度设置到jqgrid单元格中*/
					map.addEventListener("click",
							function(e) {
						/**如果当前是要设置中心点经纬度坐标*/
						if($("#center").val()=="noset"){
							$("#clgtd").val(e.point.lng); 
							$("#clttd").val(e.point.lat);
							return;
						}
							 if($("#rowID").val()=='null')
								 return;
						$("#list").jqGrid('setCell',$("#rowID").val(),2,e.point.lng); 
						$("#list").jqGrid('setCell',$("#rowID").val(),3,e.point.lat);
					 });
			 /***站点表格信息*/
					$("#list").jqGrid({ 
						url:"setting/table.do?type=map",
						caption: "实时地图设置",
						height : 'auto',
						datatype: "json", 
						colNames:['基站名','经度','纬度','操作'],
						colModel:[
						           {name:'stnm',index:'stnm', width:'70',align:'center',
						        	   formatter:function(value,options,row){
						        		   return $.trim(value);
						           }},
						           {name:'lgtd',index:'lgtd', width:'78',align:'center',editable:true,
						        	   formatter:function(value,options,row){
						        		   if(value==null)
						        			     return ' ';
						        		    else
						        		    	 return $.trim(value);
						           }},
						           {name:'lttd',index:'lttd', width:'65',align:'center',editable:true,
						        	   formatter:function(value,options,row){
						        		   if(value==null)
						        			     return ' ';
						        		    else
						        		    	 return $.trim(value);
						        	   }
						           },
						           {name:'act',index:'act', width:'105',sortable:false}
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
				        gridComplete: function(){ 
				        	var ids = jQuery("#list").jqGrid('getDataIDs'); 
				        	for(var i=0;i < ids.length;i++){
				        		var cl = ids[i]; 
				        		be = "<a href='javascript:void(0);' onclick=\"edit('"+cl+"');\">修改</a>|"; 
				        		se = "<a href='javascript:void(0);' onclick=\"save('"+cl+"');\">保存</a>|"; 
				        		ce = "<a href='javascript:void(0);' onclick=\"reset('"+cl+"');\">取消</a>"; 
				        		jQuery("#list").jqGrid('setRowData',ids[i],{act:be+se+ce}); } },
				        editrules:{number:true, required:true},
				        editurl: "setting/map/save.do"
				        
						        });
	      /**加载省市下拉框*/
	     $('#pro').combobox({    
				        url:'setting/map/getProvince.do',
					    valueField:'id',    
					    textField:'text',
					    onSelect: function(rec){    
					    	 $('#city').combobox({    
							        url:"setting/map/getCity.do?pid='"+rec['id']+"'", 
							        panelHeight : 'auto',
								    valueField:'id',    
								    textField:'text',
								    onSelect: function(rec){    
								    	//alert(rec['text']);
								    	map.centerAndZoom(rec['text'],12); 
								    }
								});
				        }
					}); 
	     
	     $("#setShow").hide();
	     $("#setC").click(function(){
	    	if($("#center").val()=="set"){
	    		$("#list").hide();
	    		 $("#center").val("noset");
	    		 $("#setC").text("保存");
	    		 $("#setShow").show();
	    	}else{
	    		 $("#center").val("set");
	    		 $("#list").show();
	    		 /**提交中心位置经纬度*/
	    		 $.ajax({
	                 type: "post",
	                 dataType:'json',
	                 url: "setting/map/saveCenter.do?lgtd="+$("#clgtd").val()+"&&lttd="+$("#clttd").val(),
	                 success: function(msg){
	                	 if(msg.success==true){
	                		alert(msg.msg);
	                		location.reload();
	                	 }else{
	                		 $("#setC").text("设置");
	        	    		 $("#setShow").hide();
	                	 }
	                	 
	                	 }
	                }); 
	    	}
	    	
	     }); 
	     $("#cancel").click(function(){
	    	 $("#center").val("set");
	    	 $("#setC").text("设置");
    		 $("#setShow").hide();
    		 $("#list").show();
	     });
	
  });
function edit(rowud){
	$("#list").jqGrid('setSelection',rowud);
	/**保存行ID，作为设置单元格的经纬度值*/
	$("#rowID").val(rowud);
	jQuery('#list').editRow(rowud);
	return true;
}
function save(rowud){
 	jQuery("#list").jqGrid('saveRow', rowud,
             {
                 successfunc: function (response) {
                 	var data=eval('(' + response.responseText + ')');
 				    alert(data['msg']);
 				   $("#rowID").val('null');
 				   location.reload();
                 	return data['success'];
                 },
                 "extraparam": {lgtd:$("#list").jqGrid('getCell',rowud,2),lttd:$("#list").jqGrid('getCell',rowud,3)}
             });
 }
function reset(rowud){
	jQuery('#list').restoreRow(rowud);
	$("#rowID").val('null');
}