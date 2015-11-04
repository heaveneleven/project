package com.njqs.service.exportExcel.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.njqs.dao.realtime.inter.RealTimeDaoInter;
import com.njqs.service.exportExcel.inter.ExcelServiceInter;
import com.njqs.utils.AjaxMsg;
/**
 * @author heaven
 * 导出excel表格service
 */
@Service("excelService")
public class ExcelServiceImpl implements ExcelServiceInter{
	@Resource(name="realtimeDao")
	private RealTimeDaoInter realtimeDao;
	/**导出实时列表*/
	@Override
	public AjaxMsg exportTable(HttpServletResponse response) {
		AjaxMsg msg=new AjaxMsg();
		/**创建一个webbook，对应一个Excel文件*/
		HSSFWorkbook wb=new HSSFWorkbook();
		/**在webbook中添加一个sheet，对应Excel文件中的sheet*/
		HSSFSheet sheet=wb.createSheet("实时列表");
		/**在sheet中添加表头第0行*/
		HSSFRow row=sheet.createRow(0);
		/**创建单元格，并设置值表头，设置头居中*/
		HSSFCellStyle style=wb.createCellStyle();
		/**创建居中格式*/
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		/**创建第一行*/
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("基站编码");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue("基站名称");
		cell.setCellStyle(style);
		cell = row.createCell(2); 
		cell.setCellValue("1小时雨量");
		cell.setCellStyle(style);
		cell = row.createCell(3); 
		cell.setCellValue("当日雨量");
		cell.setCellStyle(style);
		cell = row.createCell(4); 
		cell.setCellValue("早8时雨量");
		cell.setCellStyle(style);
		cell = row.createCell(5); 
		cell.setCellValue("当前雨量");
		cell.setCellStyle(style);
		cell = row.createCell(6); 
		cell.setCellValue("当前库容");
		cell.setCellStyle(style);
		cell = row.createCell(7); 
		cell.setCellValue("来报次数");
		cell.setCellStyle(style);
		cell = row.createCell(8); 
		cell.setCellValue("最后通讯时间");
		cell.setCellStyle(style);
		/**创建其余所有的行信息*/
		try{
	/*	List<Map<String,Object>> list=realtimeDao.getAllBaseStation2();
			int index=1;
			for(Map<String,Object> map : list){
				HSSFCell newCell=row.createCell(index++);
				Set set=map.keySet();
				Iterator iter=set.iterator();
				while(iter.hasNext()){
					newCell.setCellValue(iter.next().toString());
					newCell.setCellStyle(style);
				}
			}	
			*/
			String filename = "testchao.xls";//设置下载时客户端Excel的名称     
	      //  filename = this.encodeFilename(filename, request);//处理中文文件名  
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
	        OutputStream ouputStream = response.getOutputStream();     
	        wb.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();   
		}catch(Exception e){
			System.out.println("获取基站信息失败！创建Excel失败！");
			msg.setMsg("导出Excel失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}finally{
			return msg;
		}
		
	}
}
