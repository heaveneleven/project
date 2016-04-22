package com.nuaa.dao.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nuaa.dao.admin.inter.AdminLoginDaoInter;
import com.nuaa.dao.base.impl.BaseDaoImpl;
import com.nuaa.domain.admin.AdminInfo;
import com.nuaa.utils.AdminSessionInfo;
/**管理员登录*/
@Repository("adminLoginDao")
public class AdminLoginDaoImpl extends BaseDaoImpl<AdminInfo, Integer> implements AdminLoginDaoInter{
	/**根据学员用户名密码获取应存入的session对象*/
	@Override
	public AdminSessionInfo getUser(String login_name, String login_password) {
		AdminSessionInfo sessionInfo = new AdminSessionInfo();
		String sql = "select * from admin_info where userno = '"+login_name+"' and password = '"+login_password+"'";
		List<Map<String,Object>> list = this.searchForMap(sql);
		if(list.size()<=0){
			sessionInfo = null;
		}else{
			Map<String, Object> map = list.get(0);
			sessionInfo.setId(map.get("id").toString());
			sessionInfo.setUserno(map.get("userno").toString());
			sessionInfo.setUsername(map.get("username").toString());
		}
		return sessionInfo;
	}	
}
