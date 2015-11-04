package com.njqs.dao.rainreport.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.rainreport.inter.RainReportDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
/**
 * @author heaven
 * 雨情报表Dao
 */
@Repository("rainReportDao")
public class RainReportDaoImpl extends BaseDaoImpl<ST_STBPRP_B, Integer> implements RainReportDaoInter{
	/**获取所有的基站信息*/
	@Override
	public DataGrid<Map<String, Object>> getStations(String sttp,PageInfo pager) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct b.stcd,b.stnm,b.locality,q.type as type from dbo.ST_STBPRP_B as b  ");
		sql.append(" left join dbo.ST_STBPRP_STTP as q on b.sttp=q.sttp ");
		sql.append(" where 1=1 ");
		if("PP".equals(sttp))
			sql.append(" and b.sttp='PP' or b.sttp='RR' or b.sttp='ZQ'");
		if("ZZ".equals(sttp))
			sql.append(" and b.sttp='ZZ' or b.sttp='RR' or b.sttp='ZQ'");
		if("RR".equals(sttp))
			sql.append(" and b.sttp='RR'");
		if("all".equals(sttp))
			sql.append(" and 1=1 ");
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
	/**根据基站编号查询基站名*/
	@Override
	public String getStationName(String stcd) {
		String sql="select stnm from dbo.ST_STBPRP_B where stcd='"+stcd.trim()+"'";
		try{
		List<Map<String,Object>> list=this.searchForMap(sql);
		if(list.size()==0)
			return null;
		String name=(String) list.get(0).get("stnm");
		return name;
		}catch(Exception e){
			System.out.println("获取基站名称失败！");
			e.printStackTrace();
			return null;
		}
	}
	/**根据时间段获取时段雨量报表信息*/
	@Override
	public Map<String, Object> getHourData(String stcds, String start,
			String end, String space) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct b.stcd,isnull(p.drp,0) as drp from dbo.ST_STBPRP_B as b ");
		sql.append(" left join  ");
		sql.append(" (select stcd,sum(drp) as drp from dbo.ST_PPIN_R where tm >= '");
		sql.append(start);
		sql.append("' and tm < '");
		sql.append(end);
		sql.append("' group by stcd ) as p");
		sql.append(" on b.stcd=p.stcd where 0=1 ");
		String stcd[]=stcds.split(",");
		for(String num : stcd){
			sql.append(" or b.stcd='"+num+"'");
		}
		sql.append(" order by b.stcd asc");
		//System.out.println(start+"--"+end+"时段报表SQl:"+sql.toString());	
	    try{
	    	List<Map<String,Object>> list = this.searchForMap(sql.toString());
	    	if(list.size()==0)
	    		return null;
	    	/**将List<Map<String,Object>>当中的一行行的数据装进一个map中，用基站编号作为key*/
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	int index=0;
	    	for(Map<String,Object> obj : list){
	    		map.put(stcd[index++],obj.get("drp"));
	    	}
	    	map.put("first", start);
	    	map.put("last", end);
	    	return map;
	    }catch(Exception e){
	    	System.out.println("获取时段雨量报表信息失败！");
	    	e.printStackTrace();
	    	return null;
	    }
	}
	/**获取日降水量各站的累计值*/
	@Override
	public List<Map<String, Object>> getDayDrp(String stcds, String start,
			String end) {
		StringBuilder sql=new StringBuilder();
		sql.append("select distinct b.stcd,isnull(drp,0) as drp from dbo.ST_STBPRP_B as b ");
		sql.append("left join (");
		sql.append("select stcd,sum(drp) as drp from dbo.ST_PPIN_R where tm >= '");
		sql.append(start);
		sql.append("' and tm < '");
		sql.append(end);
		sql.append("' group by stcd ) as p on");
		sql.append(" p.stcd=b.stcd where 0=1 ");
		String stcd[]=stcds.split(",");
		for(String num : stcd){
			sql.append(" or b.stcd='"+num+"'");
		}
		sql.append(" order by stcd asc ");
	//	System.out.println("获取日降水量各站的累计值SQl:"+sql.toString());	
	    try{
	    	List<Map<String,Object>> list = this.searchForMap(sql.toString());
	    	return list;
	    }catch(Exception e){
	    	System.out.println("获取日降水量各站的累计值失败！");
	    	e.printStackTrace();
	    	return null;
	    }
	}
	/**获取月降水天数累计*/
	public List<Map<String,Object>> getMonthDayCount(String stcds,String start,String end){
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct b.stcd,isnull(o.daycount,0) as daycount  from dbo.ST_STBPRP_B as b left join (");
		sql.append(" select stcd ,count(dayss) as daycount from  ");
		sql.append(" (select stcd,DATEPART(YYYY,p.tm) as years,DATEPART(MM,p.tm) as months,DATEPART(DD,p.tm) as dayss from  ");
		sql.append(" (select stcd,DATEADD(HOUR,-8,tm) as tm from dbo.ST_PPIN_R where tm>='");
		sql.append(start);
		sql.append("' and tm < '");
		sql.append(end);
		sql.append("' and drp>0 and ( 0=1 ");
		String stcd[]=stcds.split(",");
		for(String num : stcd){
			sql.append(" or stcd='"+num+"'");
		}
		sql.append(")) as p");
		sql.append(" 	group by DATEPART(YYYY,p.tm),DATEPART(MM,p.tm),DATEPART(DD,p.tm),p.stcd) as q group by stcd ");
		sql.append(") as o on o.stcd=b.stcd where 0=1 ");
		for(String num : stcd){
			sql.append(" or b.stcd='"+num+"'");
		}
		sql.append(" order by stcd asc ");
		//System.out.println("月报表天数累计SQl:"+sql.toString());	
	    try{
	    	List<Map<String,Object>> list = this.searchForMap(sql.toString());
	    	return list;
	    }catch(Exception e){
	    	System.out.println("获取月降雨量报表天数累计失败！");
	    	e.printStackTrace();
	    	return null;
	    }
	}
	/**获取月降水报表中每个站点的月 累计降水量*/
	@Override
	public List<Map<String, Object>> getMonthDrpCount(String stcds,
			String start, String end) {
		StringBuilder sql=new StringBuilder();
		sql.append("select distinct b.stcd,isnull(drp,0) as drp from dbo.ST_STBPRP_B as b ");
		sql.append("left join (");
		sql.append("select stcd,sum(drp) as drp from dbo.ST_PPIN_R where tm >= '");
		sql.append(start);
		sql.append("' and tm < '");
		sql.append(end);
		sql.append("' group by stcd ) as p on");
		sql.append(" p.stcd=b.stcd where 0=1 ");
		String stcd[]=stcds.split(",");
		for(String num : stcd){
			sql.append(" or b.stcd='"+num+"'");
		}
		sql.append(" order by stcd asc ");
	//	System.out.println("月报表各站点总降水量累计SQl:"+sql.toString());	
	    try{
	    	List<Map<String,Object>> list = this.searchForMap(sql.toString());
	    	return list;
	    }catch(Exception e){
	    	System.out.println("获取月报表各站点总降水量累计失败！");
	    	e.printStackTrace();
	    	return null;
	    }
	}
	/**根据年份获取所有的日期所对应的降水量，只查询有的*/
	@Override
	public List<Map<String, Object>> getYearData(String stcd,String date) {
				StringBuilder sql=new StringBuilder();
				sql.append("select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,DATEPART(DD,T.newDate) as dayss, ");
				sql.append("isnull(sum(drp),0) as drp,COUNT(*) as ok from (select drp,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_PPIN_R where DATEPART(YYYY,tm)='");
				sql.append(date);
				sql.append("' and stcd='");
				sql.append(stcd);
				sql.append("')as T ");
				sql.append(" group by ");
				sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate) ");
				//System.out.println("SQL:"+sql.toString());
				try{
					
					List<Map<String,Object>> map = this.searchForMap(sql.toString());
					return map;
				}catch(Exception e){
					System.out.println("根据年份获取所有的日期所对应的降水量失败！");
					e.printStackTrace();
					return null;
				}			
	}
	/**获取月累计降雨量*/
	@Override
	public List<Map<String,Object>> getMonthdrp(String stcd,String date) {
		StringBuilder sql=new StringBuilder();
		sql.append("select q.months,sum(q.drp) as drp,count(*) as daysformonth from (select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,");
		sql.append("isnull(sum(drp),0) as drp,DATEPART(DD,T.newDate)as dayss from");
		sql.append("(select drp,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_PPIN_R where DATEPART(YYYY,tm)='");
		sql.append(date);
		sql.append("' and stcd='");
		sql.append(stcd);
		sql.append("' and drp>0)as T ");
		sql.append(" group by ");
		sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate) ) as q group by q.months ");
		//System.out.println("获取月累计降雨量SQL:"+sql.toString());
		try{
		//	List<Map<String,Object>> list=this.searchForMap(sql.toString());
			return this.searchForMap(sql.toString());
		}catch(Exception e){
			System.out.println("获取月累计降雨量失败！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取全年降雨量及天数*/
	@Override
	public List<Map<String, Object>> getYeardrp(String stcd, String date) {
		StringBuilder sql=new StringBuilder();
		sql.append("select isnull(sum(y.drp),0) as yeardrp,isnull(sum(daysformonth),0) as daysforyear from(");
		sql.append("select q.months,sum(q.drp) as drp,count(*) as daysformonth from (select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,");
		sql.append("sum(drp) as drp,DATEPART(DD,T.newDate)as dayss from");
		sql.append("(select drp,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_PPIN_R where DATEPART(YYYY,tm)='");
		sql.append(date);
		sql.append("' and stcd='");
		sql.append(stcd);
		sql.append("' and drp>0)as T ");
		sql.append(" group by ");
		sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate) ) as q group by q.months) as y ");
		//System.out.println("获取全年降雨量SQL:"+sql.toString());
		try{
			//List<Map<String,Object>> list=this.searchForMap(sql.toString());
			return this.searchForMap(sql.toString());
		}catch(Exception e){
			System.out.println("获取全年降雨量失败！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取日降水量最大值以及相应日期*/
	@Override
	public List<Map<String, Object>> getMaxForYear(String stcd, String date) {
		StringBuilder sql=new StringBuilder();
		sql.append("select top 1* from (select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,DATEPART(DD,T.newDate) as dayss, sum(drp) as drp ");
		sql.append(" from (select drp,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_PPIN_R where DATEPART(YYYY,tm)='");
		sql.append(date);
		sql.append("' ");
		sql.append("  and stcd='");
		sql.append(stcd);
		sql.append("')as T  group by ");
		sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate))as m ");
		sql.append(" order by drp desc ");
		//System.out.println("获取日降水量最大值SQL："+sql.toString());
		try{
			//List<Map<String,Object>> list=this.searchForMap(sql.toString());
			return this.searchForMap(sql.toString());
		}catch(Exception e){
			System.out.println("获取日降水量最大值失败！");
			e.printStackTrace();
			return null;
		}
	}
}
