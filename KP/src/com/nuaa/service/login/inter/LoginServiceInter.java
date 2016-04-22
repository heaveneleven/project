package com.nuaa.service.login.inter;

import com.nuaa.utils.UserSessionInfo;

public interface LoginServiceInter {
	public UserSessionInfo getUser(String login_name,String login_password,int type);
}
