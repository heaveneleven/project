package com.nuaa.service.login.inter;

import com.nuaa.utils.AdminSessionInfo;


public interface AdminLoginServiceInter {
	public AdminSessionInfo getUser(String login_name,String login_password);
}
