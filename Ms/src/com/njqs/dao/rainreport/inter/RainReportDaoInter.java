package com.njqs.dao.rainreport.inter;

import java.util.List;
import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface RainReportDaoInter extends BaseDaoInter<ST_STBPRP_B, Integer>{
	public DataGrid<Map<String,Object>> getStations(String sttp,PageInfo pager);
	public String getStationName(String stcd);
	public Map<String,Object> getHourData(String stcds,String start,String end,String space);
	public List<Map<String,Object>> getDayDrp(String stcds,String start,String end);
	public List<Map<String,Object>> getYearData(String stcd,String date);
	public List<Map<String,Object>> getMonthdrp(String stcd,String date);
	public List<Map<String,Object>> getMonthDayCount(String stcds,String start,String end);
	public List<Map<String,Object>> getMonthDrpCount(String stcds,String start,String end);
	public List<Map<String,Object>> getYeardrp(String stcd,String date);
	public List<Map<String,Object>> getMaxForYear(String stcd,String date);
}
