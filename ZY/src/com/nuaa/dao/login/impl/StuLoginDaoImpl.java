package com.nuaa.dao.login.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nuaa.dao.base.impl.BaseDaoImpl;
import com.nuaa.dao.login.inter.StuLoginDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.utils.StuSessionInfo;
/**学员登录Dao*/
@Repository("stuLoginDao")
public class StuLoginDaoImpl extends BaseDaoImpl<StuInfo, Integer> implements StuLoginDaoInter{
	
	/**验证该登录名是否存在*/
	@Override
	public boolean verifyLoginUser(String stuno) {
		    String sql="select * from stu_info where stuno = '"+stuno+"' ";
		   boolean exist=!this.searchForMap(sql).isEmpty();	   
		return exist;
	}
	/**根据学员用户名密码获取应存入的session对象*/
	@Override
	public StuSessionInfo getUser(String login_name, String login_password) {
		StuSessionInfo sessionInfo = new StuSessionInfo();
		String sql = "select * from stu_info where stuno = '"+login_name+"' and password = '"+login_password+"'";
		List<Map<String,Object>> list = this.searchForMap(sql);
		if(list.size()<=0){
			sessionInfo = null;
		}else{
			Map<String, Object> map = list.get(0);
			sessionInfo.setId(map.get("id").toString());
			sessionInfo.setStuno(map.get("stuno").toString());
			sessionInfo.setStuname(map.get("stuname").toString());
			sessionInfo.setSex(map.get("sex").toString());
			sessionInfo.setType(map.get("type").toString());
			sessionInfo.setBirth(map.get("birth").toString());
		}
		
		return sessionInfo;
	}	
}
