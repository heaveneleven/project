package com.njqs.service.user.register.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njqs.dao.user.login.inter.LoginDaoInter;
import com.njqs.dao.user.userInfo.inter.UserInfoDaoInter;
import com.njqs.domain.user.UserInfo;
import com.njqs.domain.user.UserLogin;
import com.njqs.formmodel.personInfo.RegUserForm;
import com.njqs.service.user.register.inter.RegisterServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.EcodeByMD5;
/**新用户注册*/
@Transactional
@Service("registerService")
public class RegisterServiceImpl implements RegisterServiceInter{
	@Resource(name="loginDao")
	private LoginDaoInter loginDao;
	@Resource(name="userInfoDao")
	private UserInfoDaoInter userInfoDao;
	/**保存新注册的用户信息*/
	@Override
	public AjaxMsg saveNewUser(RegUserForm  module) {
		AjaxMsg msg=new AjaxMsg();
		boolean exsit=loginDao.verifyLoginUser(module.getLogin_name());
		if(exsit==true){
			msg.setSuccess(false);
			msg.setMsg("该登录名已存在，请重新指定！");
			return msg;
		}
		UserLogin login_user=new UserLogin();
		UserInfo  user_info=new UserInfo();
		/**提取分别保存*/
		try{
			login_user.setLogin_name(module.getLogin_name());
			login_user.setLogin_password(EcodeByMD5.ecodeByMD5(module.getLogin_password()));
			login_user.setUser_type_id(1);
			loginDao.save(login_user);
			user_info.setUser_name(module.getUser_name());
			//System.out.println("~~~~~~~~"+login_user.getId());
			user_info.setLogin_id(login_user.getId());
			userInfoDao.save(user_info);
			msg.setSuccess(true);
			msg.setMsg("注册成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("用户注册失败！");
			msg.setSuccess(false);
			msg.setMsg("注册失败！");
		}
		return msg;
	}
}
