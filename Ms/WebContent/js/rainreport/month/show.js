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
	/** 获取月降雨报表每日降雨量 */
	$.ajax({
		url : "rainreport/month/show.do?stcds=" + $("#stcds").val() + "&date="
				+ $("#date").val(),
		dataType : 'json',
		success : function(data) {
			var row = "";
			$.each(data, function(index, item) {
				var itime = item['first'];
				row = "<tr><td>" + itime + "</td>";
				nowTime = new Date();
				itemTime = new Date(Date.parse(itime.replace(/-/g, "/")));
				for ( var i in stcds) {
					if (itemTime > nowTime) {
						row += "<td><font style='color: red;'>-</font></td>";
					} else {
						row += "<td>" + parseFloat(item[stcds[i]]).toFixed(1)
								+ "</td>";
					}
				}
				row += "</tr>";
				$("#monthlist").append(row);
			});
			getDayCount();
		}
	});

	$("#export").button({
		icons : {
			secondary : "ui-icon-disk"
		}
	}).click(
			function() {
				window.location.href = "export/monthRainReport.do?stcds="
						+ $("#stcds").val() + "&date=" + $("#date").val();
			});
});
/** 获取每个站点该月累计降水天数 */
function getDayCount() {
	$.ajax({
		url : "rainreport/month/getDayCount.do?stcds=" + $("#stcds").val()
				+ "&date=" + $("#date").val(),
		dataType : 'json',
		success : function(data) {
			var row = "<tr><td>累计降雨天数</td>";
			$.each(data, function(index, item) {
				row += "<td>" + item['daycount'] + "</td>";
			});
			row += "</tr>";
			$("#monthlist").append(row);
			getDrpCount();
		}
	});
}
/** 获取每个站点该月累计降水量 */
function getDrpCount() {
	$.ajax({
		url : "rainreport/month/gettotal.do?stcds=" + $("#stcds").val()
				+ "&date=" + $("#date").val(),
		dataType : 'json',
		success : function(data) {
			var row = "<tr><td>累计降雨量</td>";
			$.each(data, function(index, item) {
				row += "<td>" + parseFloat(item['drp']).toFixed(1) + "</td>";
			});
			row += "</tr>";
			$("#monthlist").append(row);
		}
	});
}