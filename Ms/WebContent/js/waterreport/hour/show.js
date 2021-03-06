$(function() {
	getHourData();
	$("#export").button({
		icons : {
			secondary : "ui-icon-disk"
		}
	}).click(
			function() {
				window.location.href = "export/hourwaterReport.do?stcds="
						+ $("#stcds").val() + "&start=" + $("#start").val()
						+ "&end=" + $("#end").val() + "&space="
						+ $("#space").val() + "&page=" + $("#page").val()
						+ "&rows=16";
			});
	/** 上一页 */
	$("#preText").click(function() {
		if ($("#page").val() == '1') {
			alert("已经是第一页！");
		} else {
			$("#chosepage").val("");
			$("#page").val(parseInt($("#page").val()) - 1);
			getHourData();
		}
	});
	/** 下一页 */
	$("#nextText").click(function() {
		if ($("#page").val() == $("#totalpages").val()) {
			alert("已经是最后一页！");
		} else {
			$("#chosepage").val("");
			$("#page").val(parseInt($("#page").val()) + 1);
			getHourData();
		}
	});
	/** 首页 */
	$("#first").click(function() {
		$("#chosepage").val("");
		$("#page").val('1');
		getHourData();
	});
	/** 尾页 */
	$("#last").click(function() {
		$("#chosepage").val("");
		$("#page").val($("#totalpages").val());
		getHourData();
	});
	/** 页跳转 */
	$("#chosepage").click(function() {
		var pagenum = $("#pagenum").val();
		var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test(pagenum) || parseInt(pagenum > $("#totalpages").val())) {
			alert("请输入正确页码");
			$("#pagenum").val("");
			return false;
		}

		$("#page").val($("#pagenum").val());
		$("#chosepage").val("");
		getHourData();
	});
});

function getHourData() {
	/** 将基站名字符串分离转换为数组 */
	var stnms = $("#stnms").val().split(",");
	var stcds = $("#stcds").val().split(",");
	$("#hourlist").empty();
	var head = "<tr><th style='background:#7DCDFF;width:220px;'>时间</th>";
	for ( var index in stnms) {
		head += "<th>" + stnms[index] + "</th>";
	}
	head += "</tr>";
	$("#hourlist").append(head);
	$.ajax({
		url : "waterreport/hour/show.do?stcds=" + $("#stcds").val() + "&start="
				+ $("#start").val() + "&end=" + $("#end").val() + "&space="
				+ $("#space").val() + "&page=" + $("#page").val() + "&rows=16",
		dataType : 'json',
		success : function(data) {
			var row = "";
			$.each(data, function(index, item) {
				row = "<tr><td>" + item['first'] + "</td>";
				for ( var i in stcds) {
					if (item[stcds[i]] != "null" && item[stcds[i]] != null) {
						row += "<td>" + parseFloat(item[stcds[i]]).toFixed(2)
								+ "</td>";
					} else {
						row += "<td><font style='color:red;'>-</font></td>";
					}
				}
				row += "</tr>";
				$("#hourlist").append(row);
			});
		}
	});
	$("#nowpage").text($("#page").val());
}
