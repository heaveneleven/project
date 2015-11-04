package com.njqs.service.realTime.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.realtime.inter.CapacityDaoInter;
import com.njqs.dao.realtime.inter.RealTimeDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.service.realTime.inter.RealTimeServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Service("realtimeService")
public class RealTimeServiceImpl implements RealTimeServiceInter{
	@Resource(name="realtimeDao")
	private RealTimeDaoInter realtimeDao;
	@Resource(name="addItems")
	private CapacityDaoInter capacityDao;
	public List<ST_STBPRP_B> getAllBaseStation(){
		return realtimeDao.getAllBaseStation();
	}
	/**获取点信息*/
	@Override
	public DataGrid<Map<String,Object>> getAllBaseStation2(PageInfo pager) {
		return realtimeDao.getAllBaseStation2(pager);
	}
	/**获取站点位置坐标*/
	@Override
	public List<Map<String, Object>> getStationPosition() {
		return realtimeDao.getMapPosition();
	}
	
    public void addItems(){
    	capacityDao.addItems();
    }
	@Override
	public List<Map<String, Object>> getRealTimeExcelInfo() {
		return realtimeDao.getRealTimeExcelInfo() ;
	} 
	
 }
