package com.njqs.dao.waterreport.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.waterreport.inter.WaterReportDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;

@Repository("waterReportDao")
public class WaterReportDaoImpl extends BaseDaoImpl<ST_STBPRP_B, Integer>
		implements WaterReportDaoInter {
	/** 根据时间段获取时段水位报表信息 */
	@Override
	public Map<String, Object> getHourData(String stcds, String start,
			String end, String space) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct b.stcd,b.sttp,q.z as z,q2.rz as rz from dbo.ST_STBPRP_B as b ");
		sql.append(" left join ");
		sql.append(" (select r.stcd,r.z from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm <= ");
		sql.append("'" + end + "' and tm >= ");
		sql.append("'" + start + "'group by stcd ) ");
		sql.append(" as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q ");
		sql.append(" on q.stcd=b.stcd ");
		sql.append(" left join ");
		sql.append(" (select r.stcd,r.rz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where tm <= ");
		sql.append("'" + end + "' and tm >= ");
		sql.append("'" + start + "'group by stcd ) ");
		sql.append(" as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q2 ");
		sql.append(" on q2.stcd=b.stcd ");
		sql.append(" where 0=1 ");
		String stcd[] = stcds.split(",");
		for (String num : stcd) {
			sql.append(" or b.stcd='" + num + "'");
		}
		sql.append(" order by b.stcd asc");
		// System.out.println(start+"--"+end+"时段报表SQl:"+sql.toString());
		try {
			List<Map<String, Object>> list = this.searchForMap(sql.toString());
			if (list.size() == 0)
				return null;
			/** 将List<Map<String,Object>>当中的一行行的数据装进一个map中，用基站编号作为key */
			Map<String, Object> map = new HashMap<String, Object>();
			int index = 0;
			for (Map<String, Object> obj : list) {
				if ("RR".equals(obj.get("sttp").toString().trim())) {
					map.put(stcd[index++], obj.get("rz"));
				} else {
					map.put(stcd[index++], obj.get("z"));
				}
			}
			map.put("first", start);
			map.put("last", end);
			return map;
		} catch (Exception e) {
			System.out.println("获取时段水位报表信息失败！");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getWaterChartData(String stcd,
			String start, String end) {
		StringBuilder sql = new StringBuilder();
		/** 判断是否为水库站，若是则调出水库中的水位信息 */
		if (this.isRR(stcd)) {
			sql.append("select rz as z,tm from dbo.ST_RSVR_R where tm>='");
		} else {
			sql.append("select z,tm from dbo.ST_RIVER_R where tm>='");
		}
		sql.append(start);
		sql.append("' and tm<'");
		sql.append(end);
		sql.append("' and stcd='");
		sql.append(stcd);
		sql.append("' order by tm asc");

		try {
			// System.out.println(sql.toString());
			List<Map<String, Object>> list = this.searchForMap(sql.toString());
			return list;
		} catch (Exception e) {
			System.out.println("获取水情图形信息失败！");
			e.printStackTrace();
			return null;
		}
	}

	/** 根据基站编码判断当前站是否为水库站 */

	@Override
	public boolean isRR(String stcd) {
		String sql = "select * from dbo.ST_STBPRP_B where stcd='" + stcd + "'";
		List<Map<String, Object>> list = this.searchForMap(sql);
		if (list.size() == 0) {
			return false;
		} else {
			if ("RR".equals(list.get(0).get("sttp").toString().trim()))
				return true;
			else
				return false;
		}
	}

	/** 根据年月获取所有的日期所对应的水位，只查询有的 */
	/** 注：在这里选取了一天当中的最大水位作为当日水位 */
	@Override
	public List<Map<String, Object>> getMonthData(String stcd, String year,
			String month) {
		StringBuilder sql = new StringBuilder();
		sql.append("select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,DATEPART(DD,T.newDate) as dayss, ");
		/** 如果所选的站为水库站则从水库表中选取数据 **/
		if (this.isRR(stcd)) {
			sql.append(" MAX(rz) as z from (select rz,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_RSVR_R where DATEPART(YYYY,tm)='");
		} else {
			sql.append(" MAX(z) as z from (select z,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_RIVER_R where DATEPART(YYYY,tm)='");
		}
		sql.append(year);
		sql.append("' and DATEPART(MM,tm)= '");
		sql.append(month);
		sql.append("' and stcd='");
		sql.append(stcd);
		sql.append("')as T ");
		sql.append(" group by ");
		sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate) order by dayss");
		System.out.println(sql.toString());
		try {
			List<Map<String, Object>> map = this.searchForMap(sql.toString());
			return map;
		} catch (Exception e) {
			System.out.println("根据年月获取所有的日期所对应的水位失败！");
			e.printStackTrace();
			return null;
		}
	}

	/** 根据年份获取所有的日期所对应的水位，只查询有的 */
	/** 注：在这里选取了一天当中的最大水位作为当日水位 */
	@Override
	public List<Map<String, Object>> getYearData(String stcd, String date) {
		StringBuilder sql = new StringBuilder();
		sql.append("select DATEPART(YYYY,T.newDate) as years,DATEPART(MM,T.newDate) as months,DATEPART(DD,T.newDate) as dayss, ");
		/** 如果所选的站为水库站则从水库表中选取数据 **/
		if (this.isRR(stcd)) {
			sql.append(" MAX(rz) as z from (select rz,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_RSVR_R where DATEPART(YYYY,tm)='");
		} else {
			sql.append(" MAX(z) as z from (select z,DATEADD(HOUR,-8,tm) as newDate from dbo.ST_RIVER_R where DATEPART(YYYY,tm)='");
		}
		sql.append(date);
		sql.append("' and stcd='");
		sql.append(stcd);
		sql.append("')as T ");
		sql.append(" group by ");
		sql.append(" DATEPART(YYYY,T.newDate),DATEPART(MM,T.newDate),DATEPART(DD,T.newDate) ");
		try {
			List<Map<String, Object>> map = this.searchForMap(sql.toString());
			return map;
		} catch (Exception e) {
			System.out.println("根据年份获取所有的日期所对应的水位失败！");
			e.printStackTrace();
			return null;
		}
	}
}
