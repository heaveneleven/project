package com.njqs.service.dayinfo.inter;
import java.util.Map;

import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface DayInfoServiceInter {
	public DataGrid<Map<String,Object>> getRainData(String date,PageInfo pager);
	public DataGrid<Map<String,Object>> getWaterData(String date,PageInfo pager);
}
