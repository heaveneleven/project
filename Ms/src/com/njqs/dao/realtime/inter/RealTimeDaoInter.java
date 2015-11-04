package com.njqs.dao.realtime.inter;

import java.util.List;
import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
public interface RealTimeDaoInter extends BaseDaoInter<ST_STBPRP_B, Integer> {
	public List<ST_STBPRP_B> getAllBaseStation();
	public DataGrid<Map<String,Object>> getAllBaseStation2(PageInfo pager);
	public String getW(String stcd,String rz,String sttp);
	public String getQ(String stcd,String rz,String sttp);
	public List<Map<String,Object>> getMapPosition();
	public List<Map<String,Object>> getRealTimeExcelInfo();
	public String getTime();
}
