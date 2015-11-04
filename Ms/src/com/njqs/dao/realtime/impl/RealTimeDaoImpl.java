package com.njqs.dao.realtime.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.realtime.inter.RealTimeDaoInter;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

@Repository("realtimeDao")
public class RealTimeDaoImpl extends BaseDaoImpl<ST_STBPRP_B, Integer>
		implements RealTimeDaoInter {
	/** 获得所有的基站信息 该函数暂时有问题出现列名不能用！ */
	@Override
	public List<ST_STBPRP_B> getAllBaseStation() {
		String sql = "SELECT [id],[stcd],[stnm] FROM [qs].[dbo].[ST_STBPRP_B]";
		try {
			List<ST_STBPRP_B> list = this.search(ST_STBPRP_B.class, sql);
			return list;
		} catch (Exception e) {
			System.out.println("获取基站信息失败！");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DataGrid<Map<String, Object>> getAllBaseStation2(PageInfo pager) {

		/** 数据库SQL语句 */
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select distinct rtrim(b.stcd) as stcd ,rtrim(b.stnm) as stnm,b.sttp,rtrim(p.type) as type ,isnull(q3.hourdrp,0) as hourdrp,isnull(q4.todaydrp,0) as todaydrp,q5.z as eightz,q6.nowz as nowz,q7.rz as keightz,q8.nowz as knowz ,isnull(q2.counts,0) as counts,q.tm,w.max as warn,w.valid ");
		sqlBuilder.append("  from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获取最后通讯时间 */
		sqlBuilder
				.append(" (select m.stcd,max(m.tm) as tm from dbo.ORIG_MSG as m group by m.stcd) as q on q.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append("(select type,sttp from dbo.ST_STBPRP_STTP) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(stcd) as counts from dbo.ORIG_MSG where tm >'");
		sqlBuilder.append(this.getTime());
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 获得一小时雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as hourdrp from dbo.ST_PPIN_R where tm > (select convert(varchar(13),getdate(),120)+':00:00:00' ) group by stcd) as q3 on q3.stcd=b.stcd  ");
		sqlBuilder.append(" left join ");
		/** 获得当日雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as todaydrp from dbo.ST_PPIN_R where tm > '");
		sqlBuilder.append(this.getTime());
		sqlBuilder.append("' group by stcd) as q4 on q4.stcd=b.stcd  ");
		sqlBuilder.append(" left join ");
		/** 获得早8时水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm < '");
		sqlBuilder.append(this.getTime());
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q5 on q5.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得当前水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z as nowz from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm < (select DATEADD(MINUTE,5, convert(varchar(20),getdate(),120))) group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q6 on q6.stcd=b.stcd ");

		/** 获得水库早8时水位 */
		sqlBuilder
				.append(" left join  (select r.stcd,r.rz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where tm < '");
		sqlBuilder.append(this.getTime());
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q7 on q7.stcd=b.stcd ");
		/** 获得水库当前水位 */
		sqlBuilder
				.append(" left join  (select r.stcd,r.rz as nowz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where tm < (select DATEADD(MINUTE,5, convert(varchar(20),getdate(),120))) group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q8 on q8.stcd=b.stcd ");
		/** 获取水位告警信息 */
		sqlBuilder
				.append(" left join  (select stcd,max,valid from dbo.warn) as w on w.stcd=b.stcd ");
		sqlBuilder.append(" where 1=1 ");
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
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
			/** 将水库的水位进行变换换为knowz和keightz并根据每个站点的类型以及水位添加库容信息 */
			for (Map<String, Object> map : list) {
				if ("RR".equals(map.get("sttp").toString())) {
					map.remove("nowz");
					map.remove("eightz");
					map.put("nowz", map.get("knowz"));
					map.put("eightz", map.get("keightz"));
				}
				String w = null;
				String q = null;
				if (map.get("nowz") != null) {
					w = this.getW(map.get("stcd").toString(), map.get("nowz")
							.toString(), map.get("sttp").toString());
					q = this.getQ(map.get("stcd").toString(), map.get("nowz")
							.toString(), map.get("sttp").toString());
				} else {
					w = getWForOther(map.get("stcd").toString(), map
							.get("sttp").toString());
					q = getQForOther(map.get("stcd").toString(), map
							.get("sttp").toString());
				}
				map.put("w", w);
				map.remove("knowz");
				map.remove("keightz");
				/** 添加水位所对应的流量 */
				map.put("q", q);
			}
			return new DataGrid<Map<String, Object>>(total, list);
		} catch (Exception e) {
			System.out.println("获取实时数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/** 在水位未获取的情况之下调用此方法 */
	private String getWForOther(String stcd, String sttp) {
		/** 如果不是水库水容站则直接返回0 */
		if (!"RR".equals(sttp.trim()))
			return "--";
		/** 判断当前的水库站是否已导入了水库关系表 */
		String exsitSql = "select * from dbo.ST_ZVARL_B where stcd='" + stcd
				+ "'";
		if (this.count(exsitSql) <= 0) {
			return "empty";
		}
		return null;
	}

	/** 根据水位获取当前库容 */
	@Override
	public String getW(String stcd, String rz, String sttp) {
		/** 如果有对库容对应的水位的直接匹配则返回精确库容 */
		String sql0 = "select stcd,rz,w from dbo.ST_ZVARL_B where rz='" + rz
				+ "' and stcd='" + stcd + "'";
		List<Map<String, Object>> list0 = this.searchForMap(sql0);
		if (list0.size() > 0)
			return list0.get(0).get("w").toString();

		float z = Float.parseFloat(rz);
		/** 获取库容的最大上限以及相应的水位 */
		String sql1 = "select stcd,rz,w from dbo.ST_ZVARL_B as z1 ,(select distinct MAX(w) as maxw from dbo.ST_ZVARL_B)as z2 where  z1.w=z2.maxw and z1.stcd='"
				+ stcd + "'";

		// System.out.println("获取库容的最大上限以及相应的水位"+sql1);
		try {
			List<Map<String, Object>> list1 = this.searchForMap(sql1);
			if (list1.size() == 0)
				return "0";
			/** 如果当前水位已经超过最大水位则返回最大库容 */
			if (z >= Float.parseFloat(list1.get(0).get("rz").toString()))
				return list1.get(0).get("w").toString();
		} catch (Exception e) {
			System.out.println("水库水容表中数据出错！");
			e.printStackTrace();
		}
		/** 获取库容的最小下限以及相应的水位 */
		String sql2 = "select stcd,rz,w from dbo.ST_ZVARL_B as z1 ,(select distinct MIN(w) as minw from dbo.ST_ZVARL_B)as z2 where  z1.w=z2.minw and z1.stcd='"
				+ stcd + "'";
		// System.out.println("获取库容的最小下限以及相应的水位"+sql2);
		try {
			List<Map<String, Object>> list2 = this.searchForMap(sql2);
			/** 如果当前水位小于最大水位则返回最小库容 */
			if (z <= Float.parseFloat(list2.get(0).get("rz").toString()))
				return list2.get(0).get("w").toString();
		} catch (Exception e) {
			System.out.println("水库水容表中数据出错！");
			e.printStackTrace();
			return null;
		}
		/** 若上述没有匹配则用线性插值 */
		/** 下面进行插值计算 */
		/** 获取当前水位的前一条和后一条 */
		String lastSql = "select top 1* from(select stcd,rz,w from dbo.ST_ZVARL_B where rz>='"
				+ rz + "' and stcd='" + stcd + "') as  l  order by rz asc";
		String nextSql = "select top 1* from(select rz,w from dbo.ST_ZVARL_B where rz<='"
				+ rz + "' and stcd='" + stcd + "') as  l  order by rz desc";
		List<Map<String, Object>> llist = this.searchForMap(lastSql);
		List<Map<String, Object>> nlist = this.searchForMap(nextSql);
		Float z1 = Float.parseFloat(llist.get(0).get("rz").toString());
		Float w1 = Float.parseFloat(llist.get(0).get("w").toString());
		Float z2 = Float.parseFloat(nlist.get(0).get("rz").toString());
		Float w2 = Float.parseFloat(nlist.get(0).get("w").toString());

		/** 斜率 */
		float k = (w2 - w1) / (z2 - z1);
		float b = w2 - k * z2;
		// System.out.println("z1="+z1+"~~z2="+z2+"~~w1="+w1+"~~w2="+w2+"~~k="+k+"~~b"+b);
		/** 函数表达式w=kz+b */
		float wvalue = k * z + b;
		return String.valueOf(wvalue);
	}

	/** 在水位未获取的情况之下调用此方法 */
	private String getQForOther(String stcd, String sttp) {
		/** 如果是雨量站则直接返回0 */
		if ("PP".equals(sttp.trim()))
			return "--";
		/** 判断当前是否已导入了水库关系表 */
		String exsitSql = "select * from dbo.ST_ZQRL_B where stcd='" + stcd
				+ "'";
		if (this.count(exsitSql) <= 0) {
			return "empty";
		}
		return null;
	}

	/** 根据水位获取流量 */
	@Override
	public String getQ(String stcd, String rz, String sttp) {
		/** 如果是雨量站则直接返回0 */
		if ("PP".equals(sttp.trim()))
			return "--";
		/** 判断当前是否已导入了水库关系表 */
		String exsitSql = "select * from dbo.ST_ZQRL_B where stcd='" + stcd
				+ "'";
		if (this.count(exsitSql) <= 0) {
			return "empty";
		}
		/** 如果有对库容对应的水位的直接匹配则返回精确库容 */
		String sql0 = "select stcd,z,q from dbo.ST_ZQRL_B where z='" + rz
				+ "' and stcd='" + stcd + "'";
		List<Map<String, Object>> list0 = this.searchForMap(sql0);
		if (list0.size() > 0)
			return list0.get(0).get("q").toString();

		float z = Float.parseFloat(rz);
		/** 获取库容的最大上限以及相应的水位 */
		String sql1 = "select stcd,z,q from dbo.ST_ZQRL_B as z1 ,(select distinct MAX(q) as maxq from dbo.ST_ZQRL_B)as z2 where  z1.q=z2.maxq and z1.stcd='"
				+ stcd + "'";

		// System.out.println("获取库容的最大上限以及相应的水位"+sql1);
		try {
			List<Map<String, Object>> list1 = this.searchForMap(sql1);
			if (list1.size() == 0)
				return "0";
			/** 如果当前水位已经超过最大水位则返回最大库容 */
			if (z >= Float.parseFloat(list1.get(0).get("z").toString()))
				return list1.get(0).get("q").toString();
		} catch (Exception e) {
			System.out.println("水位流量表中数据出错！");
			e.printStackTrace();
		}
		/** 获取库容的最小下限以及相应的水位 */
		String sql2 = "select stcd,z,q from dbo.ST_ZQRL_B as z1 ,(select distinct MIN(q) as minq from dbo.ST_ZQRL_B)as z2 where  z1.q=z2.minq and z1.stcd='"
				+ stcd + "'";
		// System.out.println("获取库容的最小下限以及相应的水位"+sql2);
		try {
			List<Map<String, Object>> list2 = this.searchForMap(sql2);
			/** 如果当前水位小于最大水位则返回最小库容 */
			if (z <= Float.parseFloat(list2.get(0).get("z").toString()))
				return list2.get(0).get("q").toString();
		} catch (Exception e) {
			System.out.println("水库水容表中数据出错！");
			e.printStackTrace();
			return null;
		}
		/** 若上述没有匹配则用线性插值 */
		/** 下面进行插值计算 */
		/** 获取当前水位的前一条和后一条 */
		String lastSql = "select top 1* from(select stcd,z,q from dbo.ST_ZQRL_B where z>='"
				+ rz + "' and stcd='" + stcd + "') as  l  order by z asc";
		String nextSql = "select top 1* from(select z,q from dbo.ST_ZQRL_B where z<='"
				+ rz + "' and stcd='" + stcd + "') as  l  order by z desc";
		List<Map<String, Object>> llist = this.searchForMap(lastSql);
		List<Map<String, Object>> nlist = this.searchForMap(nextSql);
		Float z1 = Float.parseFloat(llist.get(0).get("z").toString());
		Float q1 = Float.parseFloat(llist.get(0).get("q").toString());
		Float z2 = Float.parseFloat(nlist.get(0).get("z").toString());
		Float q2 = Float.parseFloat(nlist.get(0).get("q").toString());

		/** 斜率 */
		float k = (q2 - q1) / (z2 - z1);
		float b = q2 - k * z2;
		// System.out.println("z1="+z1+"~~z2="+z2+"~~w1="+w1+"~~w2="+w2+"~~k="+k+"~~b"+b);
		/** 函数表达式w=kz+b */
		float value = k * z + b;
		return String.valueOf(value);
	}

	/** 获取所应的时间若当前为当日早8时以后则选择当日的早8时，否则就选取前一天的早8时 */
	@Override
	public String getTime() {
		/** 获取当前日期 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = df.format(date).toString();
		/** 获得当日早8时日期 */
		String eightTm = nowDate + " 08:00:00";
		Calendar c0 = Calendar.getInstance();
		Date mydate = new Date();
		try {
			Date newEight = df.parse(eightTm);
			c0.setTime(newEight);
			c0.add(Calendar.HOUR, 8);
			mydate = c0.getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			/** 判断当前时间是否在当日8时之前若是则减一天 */
			if (date.before(mydate)) {
				Calendar c = Calendar.getInstance();
				Date newDate = df.parse(eightTm);
				c.setTime(newDate);
				c.add(Calendar.DATE, -1);
				return df.format(c.getTime()).toString() + " 08:00:00";
			}
			return eightTm;
		} catch (ParseException e) {
			System.out.println("获取时间出错！");
			e.printStackTrace();
			return null;
		}
	}

	/** 获得站点的相应坐标以及对应的水雨情信息 */
	@Override
	public List<Map<String, Object>> getMapPosition() {
		/** 数据库SQL语句 */
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select b.stcd,b.stnm,b.sttp,b.lgtd,b.lttd,q4.todaydrp,q6.nowz from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获得当日雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as todaydrp from dbo.ST_PPIN_R where tm > '");
		sqlBuilder.append(this.getTime());
		sqlBuilder.append("' group by stcd) as q4 on q4.stcd=b.stcd  ");
		sqlBuilder.append(" left join ");
		/** 获得当前水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z as nowz from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm < (select convert(varchar(20),getdate(),120)) group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q6 on q6.stcd=b.stcd ");
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
		try {
			List<Map<String, Object>> list = this.searchForMap(sqlBuilder
					.toString());
			return list;
		} catch (Exception e) {
			System.out.println("获取站点数据失败!");
			e.printStackTrace();
			return null;
		}
	}

	/** 获取实时列表导出Excel中的数据 */
	@Override
	public List<Map<String, Object>> getRealTimeExcelInfo() {

		/** 数据库SQL语句 */
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" select distinct rtrim(b.stcd) as stcd ,rtrim(b.stnm) as stnm,rtrim(p.type) as type ,p.sttp,isnull(q3.hourdrp,0) as hourdrp,isnull(q4.todaydrp,0) as todaydrp,(isnull(q5.z,0)+isnull(b.dtmel,0)) as eightz,(isnull(q6.nowz,0)+isnull(b.dtmel,0)) as nowz,(isnull(q7.rz,0)+isnull(b.dtmel,0)) as keightz,(isnull(q8.nowz,0)+isnull(b.dtmel,0)) as knowz ,isnull(q2.counts,0) as counts,q.tm ");
		sqlBuilder.append("  from dbo.ST_STBPRP_B as b ");
		sqlBuilder.append(" left join ");
		/** 获取最后通讯时间 */
		sqlBuilder
				.append(" (select m.stcd,max(m.tm) as tm from dbo.ORIG_MSG as m group by m.stcd) as q on q.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得基站类型 */
		sqlBuilder
				.append("(select type,sttp from dbo.ST_STBPRP_STTP) as p on p.sttp=b.sttp ");
		sqlBuilder.append(" left join ");
		/** 获得来报次数 */
		sqlBuilder
				.append(" (select stcd,count(stcd) as counts from dbo.ORIG_MSG where tm >'");
		sqlBuilder.append(this.getTime());
		sqlBuilder.append("' group by stcd) as q2 on q2.stcd=b.stcd ");
		sqlBuilder.append(" left join ");
		/** 获得一小时雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as hourdrp from dbo.ST_PPIN_R where tm > (select convert(varchar(13),getdate(),120)+':00:00:00' ) group by stcd) as q3 on q3.stcd=b.stcd  ");
		sqlBuilder.append(" left join ");
		/** 获得当日雨量 */
		sqlBuilder
				.append(" (select stcd ,sum(drp) as todaydrp from dbo.ST_PPIN_R where tm > '");
		sqlBuilder.append(this.getTime());
		sqlBuilder.append("' group by stcd) as q4 on q4.stcd=b.stcd  ");
		sqlBuilder.append(" left join ");
		/** 获得早8时水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm < '");
		sqlBuilder.append(this.getTime());
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q5 on q5.stcd=b.stcd");
		sqlBuilder.append(" left join ");
		/** 获得当前水位 */
		sqlBuilder
				.append(" (select r.stcd,r.z as nowz from dbo.ST_RIVER_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RIVER_R where tm < (select convert(varchar(20),getdate(),120)) group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q6 on q6.stcd=b.stcd ");

		/** 获得水库早8时水位 */
		sqlBuilder
				.append(" left join  (select r.stcd,r.rz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where tm < '");
		sqlBuilder.append(this.getTime());
		sqlBuilder
				.append("' group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q7 on q7.stcd=b.stcd ");
		/** 获得水库当前水位 */
		sqlBuilder
				.append(" left join  (select r.stcd,r.rz as nowz from dbo.ST_RSVR_R as r ,(select stcd,max(tm) as maxtm from dbo.ST_RSVR_R where tm < (select convert(varchar(20),getdate(),120)) group by stcd) as r2 where r.stcd=r2.stcd and r.tm=r2.maxtm) as q8 on q8.stcd=b.stcd ");

		sqlBuilder.append(" where 1=1 ");
		/** 后台输出SQL */
		// System.out.println(sqlBuilder.toString());
		try {
			List<Map<String, Object>> list = this.searchForMap(sqlBuilder
					.toString());
			/** 水库水位以及库容的处理 */
			for (Map<String, Object> map : list) {
				map.put("q", this.getQ(map.get("stcd").toString(),
						map.get("nowz").toString(), map.get("sttp").toString()));
				if ("RR".equals(map.get("sttp").toString())) {
					map.remove("nowz");
					map.remove("eightz");
					map.put("nowz", map.get("knowz").toString());
					map.put("eightz", map.get("keightz").toString());
					map.put("w", this.getW(map.get("stcd").toString(),
							map.get("nowz").toString(), map.get("sttp")
									.toString()));
				} else {
					map.put("w", "--");
				}
				/** 水位站没有雨量 */
				if ("ZZ".equals(map.get("sttp").toString())) {
					map.remove("hourdrp");
					map.remove("todaydrp");
					map.put("hourdrp", "--");
					map.put("todaydrp", "--");
				}
				/** 雨量站没有水位 */
				if ("PP".equals(map.get("sttp").toString())) {
					map.remove("nowz");
					map.remove("eightz");
					map.put("nowz", "--");
					map.put("eightz", "--");
				}
				map.remove("knowz");
				map.remove("keightz");
				map.remove("sttp");
			}
			return list;
		} catch (Exception e) {
			System.out.println("数据失败!");
			e.printStackTrace();
			return null;
		}
	}
}
