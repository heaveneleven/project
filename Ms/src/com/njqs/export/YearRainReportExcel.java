package com.njqs.export;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.njqs.utils.EcodeFileName;

/** 导出年降雨报表Excel */
public class YearRainReportExcel extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet sheet = workbook.createSheet("年降水报表");
		/** 添加头部标题 */
		HSSFRow row0 = sheet.createRow((short) 0);
		/** 合并单元格 */
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);// 设置填充的背景颜色
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);// 字体
		font.setFontHeightInPoints((short) 20);// 字号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		headStyle.setFont(font);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
		HSSFCell ce = row0.createCell(0);
		String date = (String) obj.get("date");
		String name = (String) obj.get("name");
		ce.setCellValue(name + " " + date + " 年降水报表"); // 表格的第一行第一列显示的数据
		ce.setCellStyle(headStyle);

		sheet.setColumnWidth(0, 6000);
		/** 在sheet中添加表头第1行 */
		HSSFRow row = sheet.createRow(1);
		/** 创建单元格，并设置值表头，设置头居中 */
		HSSFCellStyle style = workbook.createCellStyle();
		/** 创建居中格式 */
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		HSSFCell head = row.createCell(0);
		head.setCellStyle(style);
		head.setCellValue("日期");
		for (int i = 1; i <= 12; i++) {
			HSSFCell monthcell = row.createCell(i);
			monthcell.setCellStyle(style);
			monthcell.setCellValue(i + "月");
		}
		/** 年报表的日降水，只提取出存在降水量的 */
		List<Map<String, Object>> dayInfo = (List<Map<String, Object>>) obj
				.get("dayInfo");
		/** 获取月累计降水 */
		List<Map<String, Object>> monthInfo = (List<Map<String, Object>>) obj
				.get("monthInfo");
		/** 获取全年降雨以及天数 */
		List<Map<String, Object>> allyearInfo = (List<Map<String, Object>>) obj
				.get("allyearInfo");
		/** 获取年报表最大降水情况，量及日期 */
		List<Map<String, Object>> maxInfo = (List<Map<String, Object>>) obj
				.get("maxInfo");
		/** 定义二维数组来承载一年所有日期 */
		float arr[][] = new float[31][12];
		for (int i = 0; i < 31; i++)
			for (int j = 0; j < 12; j++) {
				arr[i][j] = 0;
			}
		/** 将存在降水量的记录存入数组中 */
		for (Map<String, Object> d : dayInfo) {
			int r = Integer.parseInt(d.get("day").toString()) - 1;
			int c = Integer.parseInt(d.get("month").toString()) - 1;
			arr[r][c] = Float.parseFloat(d.get("drp").toString());
		}
		int rows = 2;
		for (int d = 0; d < 31; d++) {
			int cols = 0;
			HSSFRow dayrow = sheet.createRow(rows++);
			HSSFCell dayRain = dayrow.createCell(cols++);
			dayRain.setCellStyle(style);
			dayRain.setCellValue(d + 1 + "日");
			for (int m = 0; m < 12; m++) {
				HSSFCell rainInfo = dayrow.createCell(cols++);
				rainInfo.setCellStyle(style);
				rainInfo.setCellValue(arr[d][m]);
			}
		}
		/** 添加月累计降水量，及天数 */
		float mr[] = new float[12];
		int md[] = new int[12];
		for (int i = 0; i < 12; i++) {
			mr[i] = 0;
			md[i] = 0;
		}
		for (Map<String, Object> d : monthInfo) {
			int r = Integer.parseInt(d.get("months").toString()) - 1;
			mr[r] = Float.parseFloat(d.get("drp").toString());
			md[r] = Integer.parseInt(d.get("daysformonth").toString());
		}
		HSSFRow monthrow = sheet.createRow(rows++);
		HSSFRow monthDayCountRow = sheet.createRow(rows++);
		int rcols = 0;
		int dcols = 0;
		HSSFCell dayRain = monthrow.createCell(rcols++);
		dayRain.setCellStyle(style);
		dayRain.setCellValue("月降水累计");
		for (int r = 0; r < 12; r++) {
			HSSFCell info = monthrow.createCell(rcols++);
			info.setCellStyle(style);
			info.setCellValue(mr[r]);
		}

		HSSFCell dayCount = monthDayCountRow.createCell(dcols++);
		dayCount.setCellStyle(style);
		dayCount.setCellValue("月降水天数");
		for (int r = 0; r < 12; r++) {
			HSSFCell info = monthDayCountRow.createCell(dcols++);
			info.setCellStyle(style);
			info.setCellValue(md[r]);
		}
		/** 获取全年降雨以及天数 */
		HSSFRow allyearrow = sheet.createRow(rows++);
		HSSFRow allyearrow2 = sheet.createRow(rows++);
		HSSFCell allyear = allyearrow.createCell(0);
		HSSFCell allyear2 = allyearrow2.createCell(0);

		CellRangeAddress range1 = new CellRangeAddress(35, 35, 1, 12);
		CellRangeAddress range2 = new CellRangeAddress(36, 36, 1, 12);

		sheet.addMergedRegion(range1);
		sheet.addMergedRegion(range2);

		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, range1, sheet,
				workbook);
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN, range1, sheet,
				workbook);
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, range2, sheet,
				workbook);
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN, range2, sheet,
				workbook);

		allyear.setCellValue("全年降雨量");
		allyear.setCellStyle(style);
		allyear2.setCellValue("全年降雨天数");
		allyear2.setCellStyle(style);
		HSSFCell allyeardata = allyearrow.createCell(1);
		HSSFCell allyeardata2 = allyearrow2.createCell(1);

		allyeardata.setCellStyle(style);
		allyeardata2.setCellStyle(style);
		if (allyearInfo != null) {
			allyeardata.setCellValue(allyearInfo.get(0).get("yeardrp")
					.toString());
			allyeardata2.setCellValue(allyearInfo.get(0).get("daysforyear")
					.toString());
		}
		/** 获取全年降雨最值 */
		HSSFRow maxrow = sheet.createRow(rows++);
		HSSFRow maxrow2 = sheet.createRow(rows++);
		HSSFCell yearmax = maxrow.createCell(0);
		HSSFCell yearmax2 = maxrow2.createCell(0);

		CellRangeAddress range3 = new CellRangeAddress(37, 37, 1, 12);
		CellRangeAddress range4 = new CellRangeAddress(38, 38, 1, 12);
		sheet.addMergedRegion(range3);
		sheet.addMergedRegion(range4);
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, range3, sheet,
				workbook);
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN, range3, sheet,
				workbook);
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, range4, sheet,
				workbook);
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN, range4, sheet,
				workbook);
		yearmax.setCellValue("最大日降雨量");
		yearmax.setCellStyle(style);
		yearmax2.setCellValue("最大降雨量日");
		yearmax2.setCellStyle(style);
		HSSFCell yearmaxdata = maxrow.createCell(1);
		HSSFCell yearmaxdata2 = maxrow2.createCell(1);
		yearmaxdata.setCellStyle(style);
		yearmaxdata2.setCellStyle(style);
		if (maxInfo != null) {
			yearmaxdata.setCellValue(maxInfo.get(0).get("drp").toString());
			yearmaxdata2.setCellValue(maxInfo.get(0).get("years") + "-"
					+ maxInfo.get(0).get("months") + "-"
					+ maxInfo.get(0).get("dayss"));
		}

		String filename = date + name.trim() + "年降水报表.xls";// 设置下载时客户端Excel的名称
		filename = EcodeFileName.encodeFilename(filename, request);// 处理中文文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
