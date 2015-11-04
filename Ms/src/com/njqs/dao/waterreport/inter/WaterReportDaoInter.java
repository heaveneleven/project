package com.njqs.dao.waterreport.inter;

import java.util.List;
import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;

public interface WaterReportDaoInter extends BaseDaoInter<ST_STBPRP_B, Integer>{
	public Map<String,Object> getHourData(String stcds,String start,String end,String space);
	public List<Map<String,Object>> getWaterChartData(String stcd,String start,String end);
	public List<Map<String,Object>> getMonthData(String stcd,String year,String month);
	public List<Map<String,Object>> getYearData(String stcd,String date);
	public boolean isRR(String stcd);
}
