$(function() {
	/** 将基站名字符串分离转换为数组 */
	var stnms = $("#stnms").val().split(",");
	var stcds = $("#stcds").val().split(",");
	$("#monthlist").empty();
	var head = "<tr><th style='background:#7DCDFF;width:220px;'>时间</th>";
	for ( var index in stnms) {
		head += "<th>" + stnms[index] + "</th>";
	}
	head += "</tr>";
	$("#monthlist").append(head);
	/** 获取月水位报表每日水位 */
	$
			.ajax({
				url : "waterreport/month/show.do?stcds=" + $("#stcds").val()
						+ "&date=" + $("#date").val(),
				dataType : 'json',
				success : function(data) {
					/** 定义数组来保存月表格，初始化里面数据为- */
					var arr = new Array();
					for ( var j in stcds) {
						arr[j] = new Array();
						arr[j][32] = -99999;
					}
					for (var i = 1; i <= 31; i++) {
						for ( var j in stcds) {
							arr[j][i] = '<font style="color:red;">-</font>';
						}
					}

					/** 将后台的Json对象中含有的水位及其日期对号入座 */
					$.each(data, function(index, item) {
						for ( var i in stcds) {
							if (item[stcds[i]] != "null"
									&& item[stcds[i]] != null) {
								if (arr[i][32] < item[stcds[i]]) {
									arr[i][32] = item[stcds[i]];
								}
								arr[i][item["day"]] = item[stcds[i]];
							}
						}
					});
					for ( var j in stcds) {
						if (arr[j][32] == -99999)
							arr[j][32] = '<font style="color:red;">-</font>';
					}
					/** 将日历中不存在的日期去掉，表示不存在 */
					// 润年2月29天，非润年28天
					year = parseInt($("#date").val().substr(0, 4));
					month = parseInt($("#date").val().substr(5, 2));
					if (month == 2) {
						// 2月没有30号
						for ( var j in stcds) {
							arr[j][30] = "--";
						}
						if (!((year % 4 == 0) && (year % 100 == 0 || year % 400 != 0))) {
							// 非闰年2月没有29号
							for ( var j in stcds) {
								arr[j][29] = "--";
							}
						}
					}

					if (month == 2 || month == 4 || month == 6 || month == 11) {
						for ( var j in stcds) {
							arr[j][31] = "--";
						}
					}

					/** 将数组还原为table */
					for (var d = 1; d <= 32; d++) {
						var row = "<tr><td><b>" + d + "日</b></td>";
						if (d == 32) {
							row = "<tr><td><b>当月最大水位</b></td>";
						}
						for ( var j in stcds) {
							if (arr[j][d] >= 0)
								row += "<td>"
										+ parseFloat(arr[j][d]).toFixed(2)
										+ "</td>";
							else
								row += "<td>" + arr[j][d] + "</td>";
						}
						row += "</tr>";
						$("#monthlist").append(row);
					}
				}
			});

	$("#export").button({
		icons : {
			secondary : "ui-icon-disk"
		}
	}).click(
			function() {
				window.location.href = "export/monthWaterReport.do?stcds="
						+ $("#stcds").val() + "&date=" + $("#date").val();
			});
});
