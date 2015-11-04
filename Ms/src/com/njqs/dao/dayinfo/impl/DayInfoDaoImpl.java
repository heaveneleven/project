package com.njqs.dao.dayinfo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.dayinfo.inter.DayInfoDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

@Repository("dayinfoDao")
public class DayInfoDaoImpl extends BaseDaoImpl<ST_STBPRP_B, Integer> implements
		DayInfoDaoInter {
	/**
	 * 获得日雨情表信息
	 */
	@Override
	public DataGrid<Map<String, Object>> getRainInfo(String date, PageInfo pager) {
		/** 数据库SQL语句 */
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select rtrim(b.stcd) as stcd,rtrim(b.stnm) as stnm,rtrim(p.type) as type,isnull(q4.todaydrp,0) as todaydrp,isnull(q2.counts,0) as counts from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append(" (select sttp,type from dbo.ST_STBPRP_STTP ) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(*) as counts from dbo.ORIG_MSG where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 获得当日雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as todaydrp from dbo.ST_PPIN_R where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q4 on q4.stcd=b.stcd  ");
		sqlBuilder.append(" where 1=1 ");
		/** 只选取雨量站 */
		sqlBuilder.append(" and b.sttp='PP'or b.sttp='RR' or b.sttp='ZQ'");
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
		long total = this.count(sqlBuilder.toString());
		if (total % 16 == 0) {
			total = total / 16;
		} else {
			total = total / 16 + 1;
		}
		// long total=18;
		/** 获取分页数组 */
		int f[] = pager.getFirstindexAndMaxindex();
		try {
			List<Map<String, Object>> list = this.searchForMap(
					sqlBuilder.toString(), f[0], f[1], " stcd asc ");
			return new DataGrid<Map<String, Object>>(total, list);
		} catch (Exception e) {
			System.out.println("数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取所有日雨情信息，去除分页，作为导出Excel的数据
	 */
	@Override
	public List<Map<String, Object>> getAllRainInfo(String date) {
		/** 数据库SQL语句 */
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select rtrim(b.stcd) as stcd,rtrim(b.stnm) as stnm,rtrim(p.type) as type,isnull(q4.todaydrp,0) as todaydrp,isnull(q2.counts,0) as counts from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append(" (select sttp,type from dbo.ST_STBPRP_STTP ) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(*) as counts from dbo.ORIG_MSG where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 获得当日雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as todaydrp from dbo.ST_PPIN_R where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q4 on q4.stcd=b.stcd  ");
		sqlBuilder.append(" where 1=1 ");
		/** 只选取雨量站 */
		sqlBuilder.append(" and b.sttp='PP'or b.sttp='RR' or b.sttp='ZQ'");
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
		/** 获取分页数组 */
		try {
			List<Map<String, Object>> list = this.searchForMap(sqlBuilder
					.toString());
			return list;
		} catch (Exception e) {
			System.out.println("数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/** 获得日水情列表信息 */
	@Override
	public DataGrid<Map<String, Object>> getWaterInfo(String date,
			PageInfo pager) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select rtrim(b.stcd) as stcd,rtrim(b.stnm) as stnm,rtrim(p.type) as type,q5.z as z,q4.rz as rz,isnull(q2.counts,0) as counts,b.sttp as sttp from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append(" (select sttp,type from dbo.ST_STBPRP_STTP ) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(*) as counts from dbo.ORIG_MSG where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 添加水库表中的水位信息 */
		sqlBuilder
				.append(" (select r.stcd,r.rz,r.tm from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q4 on q4.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得该日水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z,r.tm from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q5 on q5.stcd=b.stcd");
		sqlBuilder.append(" where 1=1 ");
		/** 只选取水位站,包括了水库和河道，然后在前台判断，对于水库站类型则水位用水库表中的 */
		sqlBuilder.append(" and b.sttp='ZZ'or b.sttp='RR' or b.sttp='ZQ'");
		/*********************************************/

		/** 后台输出SQL */
		System.out.println(sqlBuilder.toString());
		long total = this.count(sqlBuilder.toString());
		if (total % 16 == 0) {
			total = total / 16;
		} else {
			total = total / 16 + 1;
		}
		/** 获取分页数组 */
		int f[] = pager.getFirstindexAndMaxindex();
		try {
			List<Map<String, Object>> list = this.searchForMap(
					sqlBuilder.toString(), f[0], f[1], " stcd asc ");
			return new DataGrid<Map<String, Object>>(total, list);
		} catch (Exception e) {
			System.out.println("数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取所有日水情信息，去除分页，作为导出Excel的数据
	 */
	@Override
	public List<Map<String, Object>> getAllWaterInfo(String date) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select rtrim(b.stcd) as stcd,rtrim(b.stnm) as stnm,b.sttp,rtrim(p.type) as type,(isnull(q5.z,0)) as z,(isnull(q4.rz,0)) as rz,isnull(q2.counts,0) as counts from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append(" (select sttp,type from dbo.ST_STBPRP_STTP ) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(*) as counts from dbo.ORIG_MSG where ");
		sqlBuilder.append(" tm >= '" + this.getMinAndMaxDate(-1, date)
				+ "' and tm < '" + this.getMinAndMaxDate(1, date));
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 添加水库表中的水位信息 */
		sqlBuilder
				.append(" (select r.stcd,r.rz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where ");
		sqlBuilder.append(" tm < '" + this.getMinAndMaxDate(-1, date));
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q4 on q4.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得该日水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where ");
		sqlBuilder.append(" tm < '" + this.getMinAndMaxDate(-1, date));
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q5 on q5.stcd=b.stcd");
		sqlBuilder.append(" where 1=1 ");
		/** 只选取水位站,包括了水库和河道，然后在前台判断，对于水库站类型则水位用水库表中的 */
		sqlBuilder.append(" and b.sttp='ZZ'or b.sttp='RR' or b.sttp='ZQ'");
		/*********************************************/
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
		try {
			List<Map<String, Object>> list = this.searchForMap(sqlBuilder
					.toString());
			return list;
		} catch (Exception e) {
			System.out.println("数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取选择的日期所对应的前后两个时间段日期 1表示后一个日期 -1表示前一个日期
	 */
	public String getMinAndMaxDate(int flag, String date) {
		String nowDate = date + " 08:00:00";
		if (flag == -1) {
			return nowDate;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date nextDate = df.parse(nowDate);
				Calendar c = Calendar.getInstance();
				c.setTime(nextDate);
				c.add(Calendar.DATE, 1);
				return df.format(c.getTime()).toString() + " 08:00:00";
			} catch (ParseException e) {
				System.out.println("getMinAndMaxDate()时间转换出错");
				e.printStackTrace();
				return null;
			}
		}
	}
}
