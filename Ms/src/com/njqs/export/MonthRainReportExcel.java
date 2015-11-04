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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.njqs.utils.EcodeFileName;

/** 导出月降水量报表Excel */
public class MonthRainReportExcel extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet sheet = workbook.createSheet("月降水量报表");
		List<String> list = (List<String>) obj.get("names");
		/** 添加头部标题 */
		HSSFRow row0 = sheet.createRow((short) 0);
		// HSSFRow row1 =
		sheet.createRow((short) 1);
		/** 合并单元格 */
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);// 设置填充的背景颜色
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);// 字体
		font.setFontHeightInPoints((short) 18);// 字号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		headStyle.setFont(font);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, list.size()));
		HSSFCell ce = row0.createCell(0);
		String date = (String) obj.get("date");
		ce.setCellValue(date + " 月降水量报表"); // 表格的第一行第一列显示的数据
		ce.setCellStyle(headStyle);
		/** 在sheet中添加表头第0行 */
		HSSFRow row = sheet.createRow(2);
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
		/** 设置列宽度 */
		sheet.setColumnWidth(0, 10000);
		/** 创建第一行 */
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("时间");
		cell.setCellStyle(style);

		int index = 1;
		for (String name : list) {
			cell = row.createCell(index++);
			cell.setCellValue(name.trim());
			cell.setCellStyle(style);
		}

		/** 添加具体信息 */
		List<Map<String, Object>> hourlist = (List<Map<String, Object>>) obj
				.get("monthInfo");
		int rows = 3;
		for (Map<String, Object> map : hourlist) {
			int cols = 0;
			HSSFRow newrow = sheet.createRow(rows++);
			HSSFCell newcell = newrow.createCell(cols++);
			newcell.setCellStyle(style);
			newcell.setCellValue(map.get("first") + "至" + map.get("last"));
			List<String> stcdlist = (List<String>) obj.get("stcdlist");
			for (String stcd : stcdlist) {
				HSSFCell newcell2 = newrow.createCell(cols++);
				newcell2.setCellStyle(style);
				newcell2.setCellValue(String.valueOf(map.get(stcd)));
			}
		}
		/** 添加降水天数累计 */
		List<Map<String, Object>> daylist = (List<Map<String, Object>>) obj
				.get("dayCount");
		int daycols = 0;
		HSSFRow daycountrow = sheet.createRow(rows++);
		HSSFCell daycell = daycountrow.createCell(daycols++);
		daycell.setCellStyle(style);
		daycell.setCellValue("累计降水天数");
		for (Map<String, Object> map : daylist) {
			HSSFCell totalDayCell = daycountrow.createCell(daycols++);
			totalDayCell.setCellStyle(style);
			totalDayCell.setCellValue(String.valueOf(map.get("daycount")));
		}
		/** 添加降水累计值 */
		List<Map<String, Object>> glist = (List<Map<String, Object>>) obj
				.get("glist");
		int sumcols = 0;
		HSSFRow sumrow = sheet.createRow(rows++);
		HSSFCell sumcell = sumrow.createCell(sumcols++);
		sumcell.setCellStyle(style);
		sumcell.setCellValue("累计降水量值");
		for (Map<String, Object> map : glist) {
			HSSFCell totalCell = sumrow.createCell(sumcols++);
			totalCell.setCellStyle(style);
			totalCell.setCellValue(String.valueOf(map.get("drp")));
		}
		String filename = date + "月降水报表.xls";// 设置下载时客户端Excel的名称
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
