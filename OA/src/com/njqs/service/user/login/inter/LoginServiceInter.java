package com.njqs.service.user.login.inter;

import com.njqs.utils.UserSessionInfo;

public interface LoginServiceInter {
	public UserSessionInfo getUser(String login_name,String login_password);
}
