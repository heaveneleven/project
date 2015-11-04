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

/** 导出日水情Excel */
public class DayWaterExcel extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet sheet = workbook.createSheet("日水情");
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
		font.setFontHeightInPoints((short) 20);// 字号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		// font.setColor(HSSFColor.BLUE.index);//颜色
		headStyle.setFont(font);

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 4));
		HSSFCell ce = row0.createCell(0);
		String date = (String) obj.get("date");
		ce.setCellValue(date + "   日水情报表"); // 表格的第一行第一列显示的数据
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
		for (int i = 0; i < 5; i++)
			sheet.setColumnWidth(i, 3000);
		/** 创建第一行 */
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("基站编码");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("基站名称");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("基站类型");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("当日水位");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("来报次数");
		cell.setCellStyle(style);
		/** 添加具体信息 */
		/** 获得基站所有信息 */
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) obj
				.get("map");
		int rows = 3;
		for (Map<String, Object> map : list) {
			int cols = 0;
			HSSFRow newrow = sheet.createRow(rows++);

			HSSFCell newcell = newrow.createCell(cols++);
			newcell.setCellStyle(style);
			newcell.setCellValue(map.get("stcd").toString());

			HSSFCell newcell2 = newrow.createCell(cols++);
			newcell2.setCellStyle(style);
			newcell2.setCellValue(map.get("stnm").toString());

			HSSFCell newcell3 = newrow.createCell(cols++);
			newcell3.setCellStyle(style);
			newcell3.setCellValue(map.get("type").toString());

			HSSFCell newcell4 = newrow.createCell(cols++);
			newcell4.setCellStyle(style);
			if ("RR".equals(map.get("sttp"))) {
				newcell4.setCellValue(map.get("rz").toString());
			} else {
				newcell4.setCellValue(map.get("z").toString());
			}
			HSSFCell newcell5 = newrow.createCell(cols++);
			newcell5.setCellStyle(style);
			newcell5.setCellValue(map.get("counts").toString());
		}
		String filename = date + "日水情表.xls";// 设置下载时客户端Excel的名称
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
