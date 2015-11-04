package com.njqs.dao.setting.inter;

import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_RSVR.ST_ZVARL_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface wRelationDaoInter extends BaseDaoInter<ST_ZVARL_B, Integer>{
	public DataGrid<Map<String, Object>> getStations(String type,PageInfo pager);
}
