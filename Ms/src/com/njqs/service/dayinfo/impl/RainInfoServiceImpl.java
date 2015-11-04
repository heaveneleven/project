package com.njqs.service.dayinfo.impl;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.dayinfo.inter.DayInfoDaoInter;
import com.njqs.service.dayinfo.inter.DayInfoServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Service("dayinfoService")
public class RainInfoServiceImpl implements DayInfoServiceInter{
	@Resource(name="dayinfoDao")
	private DayInfoDaoInter dayinfoDao;
	/**获得日雨情信息*/
	@Override
	public DataGrid<Map<String, Object>> getRainData(String date,PageInfo pager) {
		return dayinfoDao.getRainInfo(date, pager);
	}
	/**获得日水情信息*/
	@Override
	public DataGrid<Map<String, Object>> getWaterData(String date,PageInfo pager) {
		return dayinfoDao.getWaterInfo(date, pager);
	}
}
