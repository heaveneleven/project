$(function() {
	var date = new Date();
	var counts = date.getTime();
	$("#hide").val(parseInt(counts) - 8 * 60 * 60 * 1000);
	/** 刚打开界面时显示当前日期 */
	$("#choseDate").val(getTime(0));
	/** 保存当前日期 */
	var tempTime = getTime(0);
	$("#preDay").button({
		icons : {
			primary : " ui-icon-circle-arrow-w"
		}
	}).click(function() {
		var realdate = getTime(-1);
		$("#choseDate").val(realdate);

		$("#list").jqGrid('setGridParam', {
			url : "day/water.do?date=" + realdate,
		}).trigger("reloadGrid");
	});
	$("#nextDay").button({
		icons : {
			secondary : " ui-icon-circle-arrow-e"
		}
	}).click(function() {
		if (tempTime == $("#choseDate").val())
			return false;
		var realdate = getTime(1);
		$("#choseDate").val(realdate);
		$("#list").jqGrid('setGridParam', {
			url : "day/water.do?date=" + realdate,
		}).trigger("reloadGrid");
	});
	/** 启用jqueryUI 日期控件 */
	$("#choseDate").datepicker(
			{
				// changeYear:true,
				// changeMonth:true,
				showOn : 'both',
				buttonImage : 'images/date.png', // 按钮图标
				buttonImageOnly : true,
				showButtonPanel : true,// 是否显示按钮面板
				closeText : "关闭",// 关闭选择框的按钮名称
				currentText : "今天",
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
				defaultDate : +0,// 默认日期，当前
				// minDate:'2015-02-05',//最小日期
				maxDate : getTime(0),// 最大日期 设置为当日
				monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一月', '十二月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				dateFormat : 'yy-mm-dd',// 格式化选择的日期格式
				onSelect : function(selectedDate) {// 选择日期后执行的操作
					var date = new Date(selectedDate);
					var counts = date.getTime();
					$("#hide").val(counts);
					$("#choseDate").val(getTime(0));
					$("#list").jqGrid('setGridParam', {
						url : "day/water.do?date=" + $("#choseDate").val()
					}).trigger("reloadGrid");
				}
			});
	$("#list").jqGrid({
		url : "day/water.do?date=" + getTime(0) + "&position=0",
		caption : "日水情",
		height : 'auto',
		datatype : "json",
		colNames : [ '基站编码', '基站名', '基站类型', '当日水位', '来报次数' ],
		colModel : [ {
			name : 'stcd',
			index : 'stcd',
			width : '90',
			align : 'center',
			formatter : function(value, options, row) {
				return $.trim(value);
			}
		}, {
			name : 'stnm',
			index : 'stnm',
			width : '90',
			align : 'center',
			formatter : function(value, options, row) {
				return $.trim(value);
			}
		}, {
			name : 'type',
			index : 'type',
			width : '90',
			align : 'center',
			formatter : function(value, options, row) {
				return $.trim(value);
			}
		}, {
			name : 'z',
			index : 'z',
			width : '90',
			align : 'center',
			formatter : function(value, options, row) {
				if (row['sttp'] == 'RR') {
					if (row['rz'] != null) {
						return parseFloat($.trim(row['rz'])).toFixed(2);
					} else {
						return '<font style="color:red;">-</font>';
					}
				}
				if (value == null)
					return '<font style="color:red;">-</font>';
				else {
					if (row['rz'] != null) {
						return parseFloat($.trim(row['z'])).toFixed(2);
					} else {
						return '<font style="color:red;">-</font>';
					}
				}

			}
		},

		{
			name : 'counts',
			index : 'counts',
			width : '77',
			align : 'center',
			formatter : function(value, options, row) {
				if (value == null)
					return '0';
				else
					return $.trim(value);
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
		search : false,
		refresh : false
	}).navButtonAdd(
			'#gridPager',
			{
				caption : "导出Excel",
				buttonicon : "ui-icon-disk",
				onClickButton : function() {
					// 导出Excel表格
					/** 注意了：用jquery ajax提交是无法达到目的的！！！ */
					window.location.href = "export/daywater.do?date="
							+ $("#choseDate").val();
				},
				position : "last"
			});

});
/** 获取日期（格式转换后的），0代表当前日期，-1代表前一天，1代表后一天 */
function getTime(position) {
	var counts = parseInt($("#hide").val());
	var real;
	if (position == -1) {// 前一天日期
		real = counts - 1 * 24 * 60 * 60 * 1000;
	} else if (position == 1) {// 后一天日期
		real = counts + 1 * 24 * 60 * 60 * 1000;
	} else {
		real = counts;
	}
	$("#hide").val(real);
	var date = new Date(real);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var time = year + "-" + (month < 10 ? '0' + month : month) + "-"
			+ (day < 10 ? '0' + day : day);
	return time;
}
