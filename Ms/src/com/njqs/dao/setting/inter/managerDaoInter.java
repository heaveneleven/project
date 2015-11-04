package com.njqs.dao.setting.inter;

import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_STBPRP.Manager;
import com.njqs.model.ManagerModel;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface managerDaoInter  extends BaseDaoInter<Manager, Integer>{
	public DataGrid<Map<String, Object>> getManager(PageInfo pager);
	/**提交管理人员信息*/
	public AjaxMsg subManager(ManagerModel m);
}
