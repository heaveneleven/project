package com.njqs.dao.setting.inter;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.warn;
import com.njqs.utils.AjaxMsg;
/**水位预警设置Dao*/
public interface warnDaoInter extends BaseDaoInter<warn, Integer>{
	/**保存水位预警值*/
	public AjaxMsg saveWarn(String stcd,String warn,boolean valid);
}
