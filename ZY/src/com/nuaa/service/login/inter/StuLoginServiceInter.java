package com.nuaa.service.login.inter;

import com.nuaa.utils.StuSessionInfo;

public interface StuLoginServiceInter {
	public StuSessionInfo getUser(String login_name,String login_password);
}
