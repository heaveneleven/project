package com.njqs.dao.message.inter;

import java.util.List;
import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_ORIG_MSG.ORIG_MSG;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface MessageDaoInter extends BaseDaoInter<ORIG_MSG,Integer> {
	public DataGrid<Map<String, Object>> getMessageData(String stcd,
			String start, String end, PageInfo pager);
	public List<Map<String,Object>> getAllNames();
	public List<Map<String,Object>> getAllNum();
	public Map<String,Object> getCountData(String start,String end);
}
