package com.njqs.service.message.inter;

import java.util.List;
import java.util.Map;

import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

/**
 * @author heaven
 *	原始报文查询service
 */
public interface MessageServiceInter {
	public DataGrid<Map<String,Object>> getMessageData(String stcd,String start,String end,PageInfo pager);
	public String getAllStationName();
	public String getAllStationNum();
	public List<Map<String, Object>> getCountData(String start,
			String end,PageInfo pager);
	public String getPages(String start, String end);
	public List<Map<String, Object>> getStartAndEnd(String start, String end,String space);
	public String getEndTime(String start, String space);
}
