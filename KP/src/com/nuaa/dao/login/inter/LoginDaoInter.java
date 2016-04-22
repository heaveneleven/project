package com.nuaa.dao.login.inter;

import com.nuaa.dao.base.inter.BaseDaoInter;
import com.nuaa.domain.user.customer.CustomerInfo;
import com.nuaa.utils.UserSessionInfo;


public interface LoginDaoInter extends BaseDaoInter<CustomerInfo, Integer>{
	public UserSessionInfo getAdminInfo(String login_name,String login_password);
	public UserSessionInfo getCustomerInfo(String login_name,String login_password);
	public boolean verifyLoginUser(String user_name);
	
}
