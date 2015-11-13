package com.njqs.service.user.login.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.user.login.inter.LoginDaoInter;
import com.njqs.service.user.login.inter.LoginServiceInter;
import com.njqs.utils.UserSessionInfo;
/**用户登录Service*/
@Service("loginService")
public class LoginServiceImpl implements LoginServiceInter{
	@Resource(name="loginDao")
	private LoginDaoInter loginDao;
	/**根据用户名密码获取应保存入session中的用户信息*/
	@Override
	public UserSessionInfo getUser(String login_name, String login_password) {
		return this.loginDao.getUser(login_name, login_password);
	}
}
