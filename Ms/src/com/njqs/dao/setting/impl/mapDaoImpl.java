package com.njqs.dao.setting.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.setting.inter.mapDaoInter;
import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.utils.AjaxMsg;
@Repository("mapDao")
public class mapDaoImpl extends BaseDaoImpl<location, Integer> implements mapDaoInter{

	/**设置地图中心经纬度坐标，没有则插入，有则修改*/
	@Override
	public AjaxMsg subCenter(String lgtd, String lttd) {
		 AjaxMsg msg=new AjaxMsg();
		 try{
			 List<location> list=this.search(location.class, "select top 1* from dbo.location ");
			 if(list.size()<1){//如果没有，则进行插入
				 location loc=new location();
				 loc.setLgtd(lgtd);
				 loc.setLttd(lttd);
				 loc.setRange(12);
				 this.save(loc);
			 }else{
				 location loc=list.get(0);
				 loc.setLgtd(lgtd);
				 loc.setLttd(lttd);
				 this.update(loc);
			 }
			 msg.setSuccess(true);
			 msg.setMsg("设置成功！");
			 return msg;
		 }catch(Exception e){
			 e.printStackTrace();
			 msg.setSuccess(false);
			 msg.setMsg("保存失败！");
			 return msg;
		 }
	}
}
