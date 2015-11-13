package com.njqs.dao.user.login.inter;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.user.UserLogin;
import com.njqs.utils.UserSessionInfo;

public interface LoginDaoInter extends BaseDaoInter<UserLogin, Integer>{
	public UserSessionInfo getUser(String login_name, String login_password);
	public boolean verifyLoginUser(String user_name);
	
}
