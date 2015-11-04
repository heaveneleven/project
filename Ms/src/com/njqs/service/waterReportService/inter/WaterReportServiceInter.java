package com.njqs.service.waterReportService.inter;

import java.util.List;
import java.util.Map;

import com.njqs.utils.PageInfo;

public interface WaterReportServiceInter {
	public List<Map<String,Object>> getHourData(String stcds,String start,String end,String space,PageInfo pager);
	public List<Map<String,Object>> getHourData2(String stcds,String start,String end,String space);
	public List<Map<String,Object>> getWaterChartData(String stcd,String start,String end);
	public List<Map<String,Object>> getDayData(String stcds,String date);
	public List<Map<String,Object>> getMonthData(String stcds,String date);
	public List<Map<String,Object>> getYearData(String stcd,String date);
	public List<Map<String,Object>> getStartAndEnd(String start,String end,String space);
	public String getEndTime(String start,String space);
}
