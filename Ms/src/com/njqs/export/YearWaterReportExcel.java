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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.njqs.utils.EcodeFileName;

/** 导出年降雨报表Excel */
public class YearWaterReportExcel extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet sheet = workbook.createSheet("年水位报表");
		HSSFRow row0 = sheet.createRow((short) 0);
		/** 合并单元格 */
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);// 字体
		font.setFontHeightInPoints((short) 20);// 字号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		headStyle.setFont(font);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
		HSSFCell ce = row0.createCell(0);
		String date = (String) obj.get("date");
		String name = (String) obj.get("name");
		ce.setCellValue(name + " " + date + " 年水位报表"); // 表格的第一行第一列显示的数据
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

		/** 定义二维数组来承载一年所有日期 */
		String arr[][] = new String[31][12];
		for (int i = 0; i < 31; i++)
			for (int j = 0; j < 12; j++) {
				arr[i][j] = "-";
			}
		/** 将存在降水量的记录存入数组中 */
		for (Map<String, Object> d : dayInfo) {
			int r = Integer.parseInt(d.get("day").toString()) - 1;
			int c = Integer.parseInt(d.get("month").toString()) - 1;
			arr[r][c] = d.get("z").toString();
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

		String filename = date + name.trim() + "年水位报表.xls";// 设置下载时客户端Excel的名称
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
