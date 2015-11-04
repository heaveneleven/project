package com.njqs.dao.setting.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.setting.inter.wRelationDaoInter;
import com.njqs.domain.query.ST_RSVR.ST_ZVARL_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Repository("wRelationDao")
public class wRelationDaoImpl extends BaseDaoImpl<ST_ZVARL_B, Integer> implements wRelationDaoInter{
	/**获取基站信息表，包括水位基值或水位报警值*/
	@Override
	public DataGrid<Map<String, Object>> getStations(String type, PageInfo pager) {
		StringBuilder sql = new StringBuilder();
		/**如果是水位基值设置*/
		if("base".equals(type)){
			sql.append(" select distinct b.stcd,b.stnm,b.locality,q.type as sttp,isnull(dtmel,0) as base from dbo.ST_STBPRP_B as b  ");
			sql.append(" left join dbo.ST_STBPRP_STTP as q on b.sttp=q.sttp ");
			sql.append(" where 1=1 ");
			sql.append(" and b.sttp='ZZ' or b.sttp='RR' or b.sttp='ZQ'");
		}else if("warn".equals(type)){//如果是水位报警设置
			sql.append(" select distinct b.stcd,b.stnm,b.locality,q.type as sttp,w.max as warn,isnull(w.valid,0) as valid from dbo.ST_STBPRP_B as b  ");
			sql.append(" left join dbo.ST_STBPRP_STTP as q on b.sttp=q.sttp ");
			sql.append(" left join dbo.warn as w on b.stcd=w.stcd ");
			sql.append(" where 1=1 ");
			sql.append(" and b.sttp='ZZ' or b.sttp='RR' or b.sttp='ZQ'");
		}else if("map".equals(type)){
			sql.append(" select distinct b.stcd,b.stnm,b.lgtd,b.lttd from dbo.ST_STBPRP_B as b  ");
		}
			
		try{
			/**获取分页数组*/
			int f[]=pager.getFirstindexAndMaxindex();
			long total=this.count(sql.toString());
			if(total%16==0){
				total=total/16;
			}else{
				total=total/16+1;
			}
			List<Map<String,Object>> list=this.searchForMap(sql.toString(),f[0],f[1]," stcd asc");
			DataGrid<Map<String,Object>> data=new DataGrid<Map<String,Object>>(total,list);
			return data;
		}catch(Exception e){
			System.out.println("获取就基站信息失败！");
			e.printStackTrace();
			return null;
		}	
	}
}
