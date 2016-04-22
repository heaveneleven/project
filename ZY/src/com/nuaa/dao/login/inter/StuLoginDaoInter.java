package com.nuaa.dao.login.inter;

import com.nuaa.dao.base.inter.BaseDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.utils.StuSessionInfo;
/***
 * 学员登录Dao
 * @author heaven
 *
 */
public interface StuLoginDaoInter extends BaseDaoInter<StuInfo, Integer>{
	public boolean verifyLoginUser(String user_name);

	public StuSessionInfo getUser(String login_name, String login_password);
	
}
