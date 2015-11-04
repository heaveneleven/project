package com.njqs.dao.setting.inter;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.utils.AjaxMsg;
/**设置地图中心经纬度*/
public interface mapDaoInter extends BaseDaoInter<location, Integer>{
	public AjaxMsg subCenter(String lgtd,String lttd);
}
