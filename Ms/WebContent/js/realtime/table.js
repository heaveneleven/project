$(function() {
	/** 显示倒计时 */
	$('#countdown').timeTo(180, function() {
		location.reload();
		// $("#list").trigger("reloadGrid");
	});

	$("#list")
			.jqGrid(
					{
						url : 'realtime/tabledata.do',
						caption : "实时列表信息",
						height : 'auto',
						datatype : "json",
						colNames : [ '基站编码', '基站名', '基站类型', '1小时雨量', '当日雨量',
								'早8时水位', '当前水位', '水位预警', '当前库容', '当前流速',
								'来报次数', '最后通讯时间' ],
						colModel : [
								{
									name : 'stcd',
									index : 'stcd',
									width : '70',
									align : 'center',
									formatter : function(value, options, row) {
										return $.trim(value);
									}
								},
								{
									name : 'stnm',
									index : 'stnm',
									width : '80',
									align : 'center',
									formatter : function(value, options, row) {
										return $.trim(value);
									}
								},
								{
									name : 'type',
									index : 'type',
									width : '92',
									align : 'center',
									formatter : function(value, options, row) {
										return $.trim(value);
									}
								},
								{
									name : 'hourdrp',
									index : 'hourdrp',
									width : '65',
									align : 'center',
									formatter : function(value, options, row) {
										if (row['sttp'] == 'PP'
												|| row['sttp'] == 'RR'
												|| row['sttp'] == 'ZQ') {
											if (value == null)
												return '0';
											else
												return parseFloat($.trim(value))
														.toFixed(1);
										} else {
											return "--";
										}
									}
								},
								{
									name : 'todaydrp',
									index : 'todaydrp',
									width : '65',
									align : 'center',
									formatter : function(value, options, row) {
										if (row['sttp'] == 'PP'
												|| row['sttp'] == 'RR'
												|| row['sttp'] == 'ZQ') {
											if (value == null)
												return '0';
											else
												return parseFloat($.trim(value))
														.toFixed(1);
										} else {
											return "--";
										}
									}
								},
								{
									name : 'eightz',
									index : 'eightz',
									width : '65',
									align : 'center',
									formatter : function(value, options, row) {
										if (row['sttp'] == 'ZZ'
												|| row['sttp'] == 'RR'
												|| row['sttp'] == 'ZQ') {
											if (value == null) {
												return '<font style="color:red;">-</font>';
											} else {
												if (row['valid'] == 1
														&& parseFloat($
																.trim(value)) >= parseFloat(row['warn']))
													return "<font style='color:red'>"
															+ parseFloat(
																	$
																			.trim(value))
																	.toFixed(2)
															+ "</font>";
												else
													return parseFloat(
															$.trim(value))
															.toFixed(2);
											}
										} else {
											return "--";
										}
									}
								},
								{
									name : 'nowz',
									index : 'nowz',
									width : '65',
									align : 'center',
									formatter : function(value, options, row) {
										if (row['sttp'] == 'ZZ'
												|| row['sttp'] == 'RR'
												|| row['sttp'] == 'ZQ') {
											if (value == null) {
												return '<font style="color:red;">-</font>';
											} else {
												if (row['valid'] == 1
														&& parseFloat($
																.trim(value)) >= parseFloat(row['warn']))
													return "<font style='color:red'>"
															+ parseFloat(
																	$
																			.trim(value))
																	.toFixed(2)
															+ "</font>";
												else
													return parseFloat(
															$.trim(value))
															.toFixed(2);
											}
										} else {
											return "--";
										}
									}
								},
								{
									name : 'valid',
									index : 'valid',
									width : '50',
									align : 'center',
									formatter : function(value, options, row) {
										if (row['valid'] == 1)
											return '是';
										else if (row['valid'] == 0) {
											return '否';
										} else {
											return '--';
										}
									}
								},
								{
									name : 'w',
									index : 'w',
									width : '70',
									align : 'center',
									formatter : function(value, options, row) {
										if (value == null) {
											return '<font style="color:red;">-</font>';
										} else if ($.trim(value) == '--') {
											return $.trim(value);
										} else if ($.trim(value) == 'empty') {
											return href = '<a style="color:blue" href="setting/wRelation/show">导入</a>';
										} else {
											return parseFloat($.trim(value))
													.toFixed(1);
										}
									}
								},
								{
									name : 'q',
									index : 'q',
									width : '70',
									align : 'center',
									formatter : function(value, options, row) {
										if (value == null) {
											return '<font style="color:red;">-</font>';
										} else if ($.trim(value) == '--') {
											return $.trim(value);
										} else if ($.trim(value) == 'empty') {
											return href = '<a style="color:blue" href="setting/vRelation/show">导入</a>';
										} else {
											return parseFloat($.trim(value))
													.toFixed(1);
										}
									}
								},
								{
									name : 'counts',
									index : 'counts',
									width : '50',
									align : 'center',
									formatter : function(value, options, row) {
										if (value == null)
											return '0';
										else
											return $.trim(value);
									}
								},
								{
									name : 'tm',
									index : 'tm',
									width : '135',
									align : 'center',
									// formatter:"date",formatoptions: {
									// srcformat:'U',newformat:'Y-m-d H:i:s'
									// }
									formatter : function(value, options, row) {
										if (judgeTime(value) == true)
											return "<font style='color:red'>"
													+ getTime(value)
													+ "</font>";
										else
											return getTime(value);
									}
								} ],
						rownumbers : true,// 添加左侧行号
						viewrecords : true, // 是否显示行数
						sortable : false,
						jsonReader : {
							id : "id",// 设置返回参数中，表格ID的名字为blackId
							repeatitems : false
						},
						pager : $('#gridPager'),
						emptyrecords : "无数据",
						rowNum : 16, // rowList:[10,20,30]//可调整每页显示的记录数

					});

	jQuery("#list").navGrid('#gridPager', {
		edit : false,
		add : false,
		del : false,
		search : false
	}).navButtonAdd('#gridPager', {
		caption : "导出Excel",
		buttonicon : "ui-icon-disk",
		onClickButton : function() {
			// 导出Excel表格
			/** 注意了：用jquery ajax提交是无法达到目的的！！！ */
			window.location.href = "export/table.do";
		},
		position : "last"
	});
	/*
	 * .navButtonAdd('#gridPager',{ caption:"Del", buttonicon:"ui-icon-del",
	 * onClickButton: function(){ alert("Deleting Row"); }, position:"last" });
	 */

	/***************************************************************************
	 * jQuery("#list").jqGrid('navGrid','#gridPager',{edit:false,add:false,del:false,view:true},{
	 * 
	 * },{ //这里可以指定add的一些属性和方法，如果不需要配置，可以不要这对大括号 },{
	 * //这里可以指定del的一些属性和方法，如果不需要配置，可以不要这对大括号 },{
	 * //这里可以指定搜索的一些属性，如果不需要配置，可以不要这对大括号 });
	 **************************************************************************/
});
/** 获得真正时间 */
function getTime(old) {
	var date = new Date(old);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	if (month < 10)
		month = "0" + month;
	var day = date.getDate();
	if (day < 10)
		day = "0" + day;
	var hour = date.getHours();
	if (hour < 10)
		hour = "0" + hour;
	var minute = date.getMinutes();
	if (minute < 10)
		minute = "0" + minute;
	var seconds = date.getSeconds();
	if (seconds < 10)
		seconds = "0" + seconds;
	var newDate = year + "-" + month + "-" + day + " " + hour + ":" + minute
			+ ":" + seconds;
	return newDate;
}
/** 判断是否超过两天没有来报文 */
function judgeTime(value) {
	var now = (new Date()).getTime();
	var days = Math.floor((now - value) / (24 * 3600 * 1000));
	if (days >= 1)
		return true;
	else
		return false;
}
