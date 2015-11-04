package com.njqs.dao.setting.impl;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.setting.inter.warnDaoInter;
import com.njqs.domain.query.ST_STBPRP.warn;
import com.njqs.utils.AjaxMsg;
@Repository("warnDao")
public class warnDaoImpl extends BaseDaoImpl<warn, Integer> implements warnDaoInter {
	/**保存水位预警值*/
	@Override
	public AjaxMsg saveWarn(String stcd, String warn, boolean valid) {
		AjaxMsg msg=new AjaxMsg();
		/**先判断当前是否已有该预警记录*/
		if(this.count("select * from warn where stcd='"+stcd+"'")>0){
			String sql="update dbo.warn set max='"+warn+"' , valid='"+(valid?1:0)+"' where stcd='"+stcd+"'";
			try{
				this.update(sql);
				msg.setSuccess(true);
				msg.setMsg("设置成功！");
				return msg;
			}catch(Exception e){
				msg.setSuccess(false);
				msg.setMsg("设置水位预警失败！");
				return msg;
			}
		}else{//如果不存在该预警记录则进行插入
			warn w=new warn();
			w.setStcd(stcd);
			w.setValid(valid?1:0);
			w.setMax(warn);
			try{
				this.save(w);
				msg.setSuccess(true);
				msg.setMsg("设置成功！");
				return msg;
			}catch(Exception e){
				msg.setSuccess(false);
				msg.setMsg("设置水位预警失败！");
				return msg;
			}
		}
	}
}
