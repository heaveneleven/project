package com.njqs.dao.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.sys.inter.AccountManagerDaoInter;
import com.njqs.domain.user.UserLogin;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
import com.njqs.utils.exception.BusinessException;
/***
 * 
 * @author heaven
 *  账号管理
 */
@Repository("accountManagerDao")
public class AccountManagerDaoImpl extends BaseDaoImpl<UserLogin, Integer> implements AccountManagerDaoInter{
	/**获取登陆账号信息*/
	@Override
	public DataGrid<Map<String, Object>> getAccountInfo(PageInfo pager) {
		StringBuilder sql=new StringBuilder();
		sql.append("select l.id,l.login_name,l.user_type_id,i.user_name,i.depart,i.post,t.type_name from dbo.user_login as l ");
		sql.append(" left join ");
		sql.append(" ( select login_id,user_name,depart,post from dbo.user_info) as i on i.login_id=l.id ");
		sql.append(" left join ");
		sql.append("( select powerId,type_name from dbo.user_type ) as t on t.powerId=l.user_type_id ");
		System.out.println(sql.toString());
		try{
			long total=this.count(sql.toString());
			int []fm=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list=this.searchForMap(sql.toString(),fm[0],fm[1]," id asc ");
			return new DataGrid<Map<String, Object>>(total, list);
		}catch(Exception e){
			throw new BusinessException("获取账号信息出错！",e);
		}
	}
}
