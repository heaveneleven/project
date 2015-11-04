package com.njqs.service.realTime.inter;
import java.util.List;
import java.util.Map;

import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface RealTimeServiceInter {
	public List<ST_STBPRP_B> getAllBaseStation();
	public DataGrid<Map<String,Object>> getAllBaseStation2(PageInfo pager);
	public List<Map<String,Object>> getStationPosition();
	public void addItems();
	public List<Map<String, Object>> getRealTimeExcelInfo();
}
