package com.nuaa.service.login.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuaa.dao.login.inter.LoginDaoInter;
import com.nuaa.service.login.inter.LoginServiceInter;
import com.nuaa.utils.UserSessionInfo;

/**用户登录Service*/
@Service("loginService")
public class LoginServiceImpl implements LoginServiceInter{
	@Resource(name="loginDao")
	private LoginDaoInter loginDao;
	/**根据用户名密码获取应保存入session中的用户信息*/
	@Override
	public UserSessionInfo getUser(String login_name, String login_password,int type) {
		if(type==1){
			return this.loginDao.getAdminInfo(login_name, login_password);
		}else{
			return this.loginDao.getCustomerInfo(login_name, login_password);
		}
	}
}
