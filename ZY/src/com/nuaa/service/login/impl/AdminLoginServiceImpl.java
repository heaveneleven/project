package com.nuaa.service.login.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuaa.dao.admin.inter.AdminLoginDaoInter;
import com.nuaa.service.login.inter.AdminLoginServiceInter;
import com.nuaa.utils.AdminSessionInfo;
/**学员登录Service*/
@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginServiceInter{
	@Resource(name="adminLoginDao")
	private AdminLoginDaoInter loginDao;
	/**根据用户名密码获取应保存入session中的用户信息*/
	@Override
	public AdminSessionInfo getUser(String login_name, String login_password) {
		return this.loginDao.getUser(login_name, login_password);
	}
}
