package com.njqs.dao.login.inter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.user.TUser;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface SysLoginDaoInter extends BaseDaoInter<TUser, Integer>{
	/**验证用户是否存在*/
	public boolean verifyUser(String username,String password);
	/**根据用户名密码得到用户*/
	public TUser getUser(String username,String password);
	/**获取所有登陆用户信息*/
	public DataGrid<Map<String,Object>> getUsers(HttpServletRequest request,PageInfo pager);
}
