package com.njqs.dao.login.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.login.inter.SysLoginDaoInter;
import com.njqs.domain.user.TUser;
import com.njqs.utils.DataGrid;
import com.njqs.utils.FilePath;
import com.njqs.utils.PageInfo;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserInfo;

@Repository("sysLoginDao")
public class SysLoginDaoImpl extends BaseDaoImpl<TUser, Integer> implements
		SysLoginDaoInter {

	@Override
	public boolean verifyUser(String username, String password) {
		String sql = "select * from tuser where username = '" + username
				+ "' and " + " password = '" + password + "'";
		boolean exist = !this.searchForMap(sql).isEmpty();
		return exist;
	}

	@Override
	public TUser getUser(String username, String password) {
		String sql = "select * from tuser where username ='" + username
				+ "' and " + " password = '" + password + "'";
		List<TUser> list = this.search(TUser.class, sql);
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public DataGrid<Map<String, Object>> getUsers(HttpServletRequest request,
			PageInfo pager) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				SessionKey.UserInfoKey);
		String sql = "select * from tuser where username <> '"
				+ userInfo.getUser().getUsername() + "'";
		try {
			/** 获取分页数组 */
			int f[] = pager.getFirstindexAndMaxindex();
			long total = this.count(sql.toString());
			if (total % 16 == 0) {
				total = total / 16;
			} else {
				total = total / 16 + 1;
			}
			List<Map<String, Object>> list = this.searchForMap(sql.toString(),
					f[0], f[1], " id asc");
			DataGrid<Map<String, Object>> data = new DataGrid<Map<String, Object>>(
					total, list);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
