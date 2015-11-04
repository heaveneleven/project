package com.njqs.dao.setting.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.setting.inter.managerDaoInter;
import com.njqs.domain.query.ST_STBPRP.Manager;
import com.njqs.model.ManagerModel;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Repository("managerDao")
public class managerDaoImpl extends BaseDaoImpl<Manager,Integer> implements managerDaoInter{
	/**获取管理人员信息*/
	@Override
	public DataGrid<Map<String, Object>> getManager(PageInfo pager) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.stnm,b.stcd,m.id,m.depart,m.name,m.position,m.sex,m.tel,m.remark from ST_STBPRP_B as b ");
		sql.append(" left join ");
		sql.append(" (select * from dbo.manager ) as m  ");
		sql.append(" on b.stcd=m.stcd ");
		try{
			long total=this.count(sql.toString());
			if(total%16==0){
				total=total/16;
			}else{
				total=total/16+1;
			}
			/**获取分页数组*/
			int f[]=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list=this.searchForMap(sql.toString(),f[0],f[1]," stcd asc ");
			return new DataGrid<Map<String,Object>>(total,list);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**提交管理人员信息*/

	@Override
	public AjaxMsg subManager(ManagerModel m) {
		AjaxMsg msg=new AjaxMsg();
		String sql="select * from manager where stcd='"+m.getStcd()+"'";
		try{
			List<Manager> list=this.search(Manager.class, sql);
			/**不存在则进行插入，否则修改*/
			if(list==null || list.size()==0){
				Manager man=new Manager();
				man.setStcd(m.getStcd());
				man.setName(m.getName());
				man.setDepart(m.getDepart());
				man.setPosition(m.getPosition());
				man.setTel(m.getTel());
				man.setSex(m.getSex());
				man.setRemark(m.getRemark());
				this.save(man);
			}else{
				Manager man=list.get(0);
				man.setStcd(m.getStcd());
				man.setName(m.getName());
				man.setDepart(m.getDepart());
				man.setPosition(m.getPosition());
				man.setTel(m.getTel());
				man.setSex(m.getSex());
				man.setRemark(m.getRemark());
				this.update(man);
			}
			msg.setSuccess(true);
			msg.setMsg("设置成功！");
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置失败！");
		}
			return msg;
	}
}
