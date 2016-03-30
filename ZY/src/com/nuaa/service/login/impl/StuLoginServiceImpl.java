package com.nuaa.service.login.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuaa.dao.login.inter.StuLoginDaoInter;
import com.nuaa.service.login.inter.StuLoginServiceInter;
import com.nuaa.utils.StuSessionInfo;
/**学员登录Service*/
@Service("stuLoginService")
public class StuLoginServiceImpl implements StuLoginServiceInter{
	@Resource(name="stuLoginDao")
	private StuLoginDaoInter loginDao;
	/**根据用户名密码获取应保存入session中的用户信息*/
	@Override
	public StuSessionInfo getUser(String login_name, String login_password) {
		return this.loginDao.getUser(login_name, login_password);
	}
}
