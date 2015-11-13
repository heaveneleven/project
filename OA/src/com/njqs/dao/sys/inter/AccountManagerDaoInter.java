package com.njqs.dao.sys.inter;

import java.util.Map;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.user.UserLogin;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface AccountManagerDaoInter extends BaseDaoInter<UserLogin, Integer>{
	public DataGrid<Map<String,Object>> getAccountInfo(PageInfo pager);
}
