package com.njqs.utils;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.njqs.dao.realtime.inter.RealTimeDaoInter;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
/**
 * @author heaven
 * 导出实时列表Excel
 */
public class ViewExcel extends AbstractExcelView{
	@Resource(name="realtimeDao")
	private RealTimeDaoInter realtimeDao;
	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 HSSFSheet sheet=workbook.createSheet("实时列表");
	     HSSFRow row0 = sheet.createRow((short) 0);   
         HSSFRow row1 = sheet.createRow((short) 1);
		/**合并单元格*/
         HSSFCellStyle headStyle=workbook.createCellStyle();
         headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         headStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);//设置填充的背景颜色
//         headStyle.setBorderTop(CellStyle.BORDER_THIN);
//         headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
//         headStyle.setBorderBottom(CellStyle.BORDER_THIN);
//         headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//         headStyle.setBorderLeft(CellStyle.BORDER_THIN);
//         headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());      
//         headStyle.setBorderRight(CellStyle.BORDER_THIN);
//         headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
         HSSFFont font = workbook.createFont();
         font.setFontName(HSSFFont.FONT_ARIAL);//字体
         font.setFontHeightInPoints((short) 20);//字号
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
         // font.setColor(HSSFColor.BLUE.index);//颜色
         headStyle.setFont(font);

        sheet.addMergedRegion(new CellRangeAddress(0,1,0,10));
		HSSFCell ce = row0.createCell(0);   
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date); 
        ce.setCellValue(time+"   实时数据列表"); // 表格的第一行第一列显示的数据   
        ce.setCellStyle(headStyle);
		/**在sheet中添加表头第2行*/
		HSSFRow row=sheet.createRow(2);

		/**创建单元格，并设置值表头，设置头居中*/
		HSSFCellStyle style=workbook.createCellStyle();
		/**创建居中格式*/
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());      
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		/**设置列宽度*/
		for(int i=0;i<10;i++)
		sheet.setColumnWidth(i, 3000);
		sheet.setColumnWidth(10, 5000);
		/**创建第一行*/
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("基站编码");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue("基站名称");
		cell.setCellStyle(style);
		cell = row.createCell(2); 
		cell.setCellValue("基站类型");
		cell.setCellStyle(style);
		cell = row.createCell(3); 
		cell.setCellValue("1小时雨量");
		cell.setCellStyle(style);
		cell = row.createCell(4); 
		cell.setCellValue("当日雨量");
		cell.setCellStyle(style);
		cell = row.createCell(5); 
		cell.setCellValue("早8时水位");
		cell.setCellStyle(style);
		cell = row.createCell(6); 
		cell.setCellValue("当前水位");
		cell.setCellStyle(style);
		cell = row.createCell(7); 
		cell.setCellValue("当前库容");
		cell.setCellStyle(style);
		cell = row.createCell(8); 
		cell.setCellValue("当前流速");
		cell.setCellStyle(style);
		cell = row.createCell(9); 
		cell.setCellValue("当日来报次数");
		cell.setCellStyle(style);
		cell = row.createCell(10); 
		cell.setCellValue("最后通讯时间");
		cell.setCellStyle(style);
        /**添加具体信息*/
		/**获得基站所有信息*/
        @SuppressWarnings("unchecked")
		List<Map<String,Object>> list = (List<Map<String, Object>>) obj.get("map");
        int rows=3;
        for(Map<String,Object> map : list){
        	int cols=0;
//        	HSSFRow newrow=sheet.createRow(rows++);
//        	for(Map.Entry<String,Object> entry : map.entrySet()){
//        	HSSFCell newcell=newrow.createCell(cols++);
//        	newcell.setCellStyle(style);
//        	newcell.setCellValue(String.valueOf(entry.getValue()==null?" ":entry.getValue()).trim());
        	HSSFRow newrow=sheet.createRow(rows++);
        	
        	HSSFCell newcell=newrow.createCell(cols++);
        	newcell.setCellStyle(style);
        	newcell.setCellValue(map.get("stcd").toString());
        	
        	HSSFCell newcell2=newrow.createCell(cols++);
        	newcell2.setCellStyle(style);
        	newcell2.setCellValue(map.get("stnm").toString());
        	
        	HSSFCell newcell3=newrow.createCell(cols++);
        	newcell3.setCellStyle(style);
        	newcell3.setCellValue(map.get("type").toString());
        	
        	HSSFCell newcell4=newrow.createCell(cols++);
        	newcell4.setCellStyle(style);
        	newcell4.setCellValue(map.get("hourdrp").toString());
        	
        	HSSFCell newcell5=newrow.createCell(cols++);
        	newcell5.setCellStyle(style);
        	newcell5.setCellValue(map.get("todaydrp").toString());
        	
        	HSSFCell newcell6=newrow.createCell(cols++);
        	newcell6.setCellStyle(style);
        	newcell6.setCellValue(map.get("eightz").toString());
        	
        	HSSFCell newcell7=newrow.createCell(cols++);
        	newcell7.setCellStyle(style);
        	newcell7.setCellValue(map.get("nowz").toString());
        	
        	HSSFCell newcell8=newrow.createCell(cols++);
        	newcell8.setCellStyle(style);
        	newcell8.setCellValue("empty".equals(map.get("w").toString())?"未设置":map.get("w").toString());
        	
        	HSSFCell newcell9=newrow.createCell(cols++);
        	newcell9.setCellStyle(style);
        	newcell9.setCellValue("empty".equals(map.get("q").toString())?"未设置":map.get("q").toString());
        	
        	HSSFCell newcell10=newrow.createCell(cols++);
        	newcell10.setCellStyle(style);
        	newcell10.setCellValue(map.get("counts").toString());
        	
        	HSSFCell newcell11=newrow.createCell(cols++);
        	newcell11.setCellStyle(style);
        	newcell11.setCellValue(map.get("tm")==null?"--":map.get("tm").toString());
        }      
        String filename = "实时列表.xls";//设置下载时客户端Excel的名称     
        filename = this.encodeFilename(filename, request);//处理中文文件名  
        response.setContentType("application/vnd.ms-excel");     
        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);     
        ouputStream.flush();     
        ouputStream.close();     
    
	}
	
	 public static String encodeFilename(String filename, HttpServletRequest request) {    
	      /**  
	       * 获取客户端浏览器和操作系统信息  
	       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
	       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
	       */    
	      String agent = request.getHeader("USER-AGENT");    
	      try {    
	        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
	          String newFileName = URLEncoder.encode(filename, "UTF-8");    
	          newFileName = StringUtils.replace(newFileName, "+", "%20");    
	          if (newFileName.length() > 150) {    
	            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
	            newFileName = StringUtils.replace(newFileName, " ", "%20");    
	          }    
	          return newFileName;    
	        }    
	        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
	          return MimeUtility.encodeText(filename, "UTF-8", "B");    
	      
	        return filename;    
	      } catch (Exception ex) {    
	        return filename;    
	      }    
	    }   
}
