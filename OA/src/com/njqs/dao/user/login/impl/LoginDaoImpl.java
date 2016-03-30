package com.njqs.dao.user.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.user.login.inter.LoginDaoInter;
import com.njqs.domain.user.UserLogin;
import com.njqs.utils.UserSessionInfo;
/**用户登录Dao*/
@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<UserLogin, Integer> implements LoginDaoInter{
	/**根据用户名和密码获取应存入session中的信息*/
	@Override
	public UserSessionInfo getUser(String login_name, String login_password) {
		StringBuilder sql=new StringBuilder("select l.id as login_id,l.login_name,l.user_type_id,i.user_name,i.id as user_info_id, t.type_name from dbo.user_login as l ");
		sql.append(" left join ");
		sql.append(" (select * from dbo.user_info ) as i on l.id=i.login_id ");
		sql.append(" left join ");
		sql.append(" (select * from dbo.user_type ) as t on l.user_type_id=t.id ");
		sql.append(" where l.login_name = '");
		sql.append(login_name);
		sql.append("' and l.login_password='");
		sql.append(login_password);
		sql.append("'");
		//System.out.println("用户登录验证:"+sql.toString());
		UserSessionInfo sessionInfo=new UserSessionInfo();
		List<Map<String,Object>> list=this.searchForMap(sql.toString());
		if(list.isEmpty() ||  list==null || list.size()<=0){
			sessionInfo=null;
		}else{
			    Map<String, Object> map = list.get(0);
				sessionInfo.setLogin_id(Integer.parseInt(map.get("login_id").toString()));
				sessionInfo.setUser_info_id(Integer.parseInt(map.get("user_info_id").toString()));
				sessionInfo.setUser_type_id(Integer.parseInt(map.get("user_type_id").toString()));
				sessionInfo.setLogin_name(map.get("login_name").toString());
				sessionInfo.setUser_name(map.get("user_name").toString());
				sessionInfo.setUser_type(map.get("type_name").toString());
		}
		return sessionInfo;
	}
	/**验证该登录名是否存在*/
	@Override
	public boolean verifyLoginUser(String login_name) {
		    String sql="select * from user_login where login_name = '"+login_name+"' ";
		   boolean exist=!this.searchForMap(sql).isEmpty();	   
		return exist;
	}	
}
