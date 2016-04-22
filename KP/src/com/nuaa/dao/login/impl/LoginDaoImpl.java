package com.nuaa.dao.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nuaa.dao.base.impl.BaseDaoImpl;
import com.nuaa.dao.login.inter.LoginDaoInter;
import com.nuaa.domain.user.customer.CustomerInfo;
import com.nuaa.utils.UserSessionInfo;

/**用户登录Dao*/
@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<CustomerInfo, Integer> implements LoginDaoInter{
	
	/**验证该登录名是否存在*/
	@Override
	public boolean verifyLoginUser(String login_name) {
		    String sql="select * from user_login where login_name = '"+login_name+"' ";
		   boolean exist=!this.searchForMap(sql).isEmpty();	   
		return exist;
	}
	/**获取验证管理员信息*/
	@Override
	public UserSessionInfo getAdminInfo(String login_name, String login_password) {
		StringBuilder sql=new StringBuilder("select * from admin_info where login_name='");
		sql.append(login_name);
		sql.append("' and login_password='");
		sql.append(login_password);
		sql.append("'");
		//System.out.println("用户登录验证:"+sql.toString());
		UserSessionInfo sessionInfo=new UserSessionInfo();
		List<Map<String,Object>> list=this.searchForMap(sql.toString());
		if(list.isEmpty() ||  list==null || list.size()<=0){
			sessionInfo=null;
		}else{
			    Map<String, Object> map = list.get(0);
				sessionInfo.setId(map.get("id").toString());
				sessionInfo.setLogin_name(map.get("login_name").toString());
				sessionInfo.setUser_name(map.get("user_name").toString());
				sessionInfo.setUser_email(map.get("user_email").toString());
				sessionInfo.setUser_mobile(map.get("user_mobile").toString());
				sessionInfo.setUser_address(map.get("user_address").toString());
		}
		return sessionInfo;
	}
	/**获取客户信息*/
	@Override
	public UserSessionInfo getCustomerInfo(String login_name,
			String login_password) {
		StringBuilder sql=new StringBuilder("select * from customer_info where login_name='");
		sql.append(login_name);
		sql.append("' and login_password='");
		sql.append(login_password);
		sql.append("'");
		//System.out.println("用户登录验证:"+sql.toString());
		UserSessionInfo sessionInfo=new UserSessionInfo();
		List<Map<String,Object>> list=this.searchForMap(sql.toString());
		if(list.isEmpty() ||  list==null || list.size()<=0){
			sessionInfo=null;
		}else{
			    Map<String, Object> map = list.get(0);
				sessionInfo.setId(map.get("id").toString());
				sessionInfo.setLogin_name(map.get("login_name").toString());
				sessionInfo.setCustomerNo(map.get("customerNo").toString());
				sessionInfo.setUser_name(map.get("user_name").toString());
				sessionInfo.setUser_email(map.get("user_email").toString());
				sessionInfo.setUser_mobile(map.get("user_mobile").toString());
				sessionInfo.setUser_address(map.get("user_address").toString());
		}
		return sessionInfo;
	}	
}
