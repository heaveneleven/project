package com.njqs.dao.message.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.message.inter.MessageDaoInter;
import com.njqs.domain.query.ST_ORIG_MSG.ORIG_MSG;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<ORIG_MSG, Integer> implements MessageDaoInter{
	@Override
	public DataGrid<Map<String, Object>> getMessageData(String stcd,
			String start, String end, PageInfo pager) {
		StringBuilder sql=new StringBuilder();
		sql.append("select tm,msg from dbo.ORIG_MSG where stcd='");
		sql.append(stcd);
		sql.append("' and tm >='");
		sql.append(start);
		sql.append("' and tm<='");
		sql.append(end);
		sql.append("'");
		try{
			long total=this.count(sql.toString());
			if(total%16==0){
				total=total/16;
			}else{
				total=total/16+1;
			}
			/**获取分页数组*/
			int f[]=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list=this.searchForMap(sql.toString(),f[0],f[1]," tm asc ");
			DataGrid<Map<String,Object>> datagrid=new DataGrid<>(total, list);
			return datagrid;
		}catch(Exception e){
			System.out.println("获取原始报文信息失败！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取所有基站名*/
	@Override
	public List<Map<String, Object>> getAllNames() {
		String sql="select stnm from dbo.ST_STBPRP_B ";
		return this.searchForMap(sql);
	}
	 
	@Override
	public List<Map<String, Object>> getAllNum() {
		String sql="select stcd from dbo.ST_STBPRP_B ";
		return this.searchForMap(sql);
	}
	/**获取某一个时间段内所有基站的来报次数*/
	@Override
	public Map<String, Object> getCountData(String start, String end) {
		StringBuilder sql=new StringBuilder();
		sql.append("select b.stcd,isnull(q.counts,0) as counts  from dbo.ST_STBPRP_B as b ");
		sql.append(" left join ");
		sql.append("(select stcd, COUNT(*)as counts from (select * from  dbo.ORIG_MSG  where tm >= '");
		sql.append(start);
		sql.append("' and tm < '");
		sql.append(end);
		sql.append("') as t group by stcd) as q ");
		sql.append("on b.stcd=q.stcd order by stcd asc");
		//System.out.println("SQL:"+sql.toString());
		try{
			List<Map<String,Object>> list=this.searchForMap(sql.toString());
			Map<String,Object> infoMap=new HashMap<String,Object>(); 
			for(Map<String,Object> map : list ){
				infoMap.put(map.get("stcd").toString().trim(),map.get("counts"));
			} 
			infoMap.put("date", start);
			return infoMap;
		}catch(Exception e){
			System.out.println("获取某一个时间段内所有基站的来报次数失败！");
			e.printStackTrace();
			return null;
		}
	}
}
