package com.njqs.service.sys.inter;

import java.util.Map;

import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

/**
 * @author heaven
 *	账号管理
 */
public interface AccountMangerServiceInter {
	public DataGrid<Map<String,Object>> getAccountInfo(PageInfo pager);
}
