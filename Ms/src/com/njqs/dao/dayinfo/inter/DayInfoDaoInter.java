package com.njqs.dao.dayinfo.inter;
import java.util.List;
import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface DayInfoDaoInter extends BaseDaoInter<ST_STBPRP_B, Integer>{
	public DataGrid<Map<String,Object>> getRainInfo(String date,PageInfo pager);
	public List<Map<String,Object>> getAllRainInfo(String date);
	public DataGrid<Map<String,Object>> getWaterInfo(String date,PageInfo pager);
	public List<Map<String,Object>> getAllWaterInfo(String date);
	public String getMinAndMaxDate(int flag,String date);
	
}
