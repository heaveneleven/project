package com.njqs.service.rainReportService.inter;

import java.util.List;
import java.util.Map;

import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
public interface RainReportServiceInter {
	public DataGrid<Map<String,Object>> getStations(String sttp,PageInfo pager);
	public String getStationName(String stcd);
	public List<Map<String,Object>> getHourData(String stcds,String start,String end,String space,PageInfo pager);
	public String getPages(String start,String end,String space);
	public List<Map<String,Object>> getDayData(String stcds,String date);
	public List<Map<String,Object>> getDayDrp(String stcds,String date);
	public List<Map<String,Object>> getMonthData(String stcds,String date);
	public List<Map<String,Object>> getMonthTotal(String stcds,String date);
	public List<Map<String,Object>> getMonthDayCount(String stcds,String date);
	public List<Map<String,Object>> getMonthDrpCount(String stcds,String date);
	public List<Map<String,Object>> getYearData(String stcd,String date);
	public List<Map<String,Object>> getStartAndEnd(String start,String end,String space);
	public String getEndTime(String start,String space);
	public List<Map<String,Object>> getMonthDrp(String stcd,String date);
	public List<Map<String,Object>> getYearDrp(String stcd,String date);
	public List<Map<String,Object>> getMaxForYear(String stcd,String date);
}
