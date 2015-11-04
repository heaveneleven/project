$(function() {
	/** 将基站名字符串分离转换为数组 */
	var stnms = $("#stnms").val().split(",");
	var stcds = $("#stcds").val().split(",");
	$("#daylist").empty();
	var head = "<tr><th style='background:#7DCDFF;width:220px;'>时间</th>";
	for ( var index in stnms) {
		head += "<th>" + stnms[index] + "</th>";
	}
	head += "</tr>";
	$("#daylist").append(head);
	$.ajax({
		url : "waterreport/day/show.do?stcds=" + $("#stcds").val() + "&date="
				+ $("#date").val(),
		dataType : 'json',
		success : function(data) {
			/** 定义数组来保存日表格，初始化里面数据为- */
			var arr = new Array();
			for ( var j in stcds) {
				arr[j] = new Array();
				arr[j][25] = -99999;
			}
			var hours = new Array();
			for (var i = 1; i <= 24; i++) {
				for ( var j in stcds) {
					arr[j][i] = '<font style="color:red;">-</font>';
				}
			}
			$.each(data, function(index, item) {
				for ( var i in stcds) {
					if (item[stcds[i]] != "null" && item[stcds[i]] != null) {
						if (arr[i][25] < item[stcds[i]]) {
							arr[i][25] = item[stcds[i]];
						}
						arr[i][index] = item[stcds[i]];
					}
				}
				hours[index + 1] = item['first'];
			});
			for ( var j in stcds) {
				if (arr[j][25] == -99999) {
					arr[j][25] = '<font style="color:red;">-</font>';
				}
			}
			/** 将数组还原为table */
			for (var d = 1; d <= 25; d++) {
				var row = "<tr><td><b>" + hours[d] + "</b></td>";
				if (d == 25) {
					row = "<tr><td><b>当日最大水位</b></td>";
				}
				for ( var i in stcds) {
					if (arr[i][d] >= 0) {
						row += "<td>" + parseFloat(arr[i][d]).toFixed(2)
								+ "</td>";
					} else {
						row += "<td>" + arr[i][d] + "</td>";
					}
				}
				row += "</tr>";
				$("#daylist").append(row);
			}
		}
	});
	$("#export").button({
		icons : {
			secondary : "ui-icon-disk"
		}
	}).click(
			function() {
				window.location.href = "export/dayWaterReport.do?stcds="
						+ $("#stcds").val() + "&date=" + $("#date").val();
			});
});
