package com.njqs.service.waterReportService.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.waterreport.inter.WaterReportDaoInter;
import com.njqs.service.waterReportService.inter.WaterReportServiceInter;
import com.njqs.utils.PageInfo;

@Service("waterReportService")
public class WaterReportServiceImpl implements WaterReportServiceInter {
	@Resource(name = "waterReportDao")
	private WaterReportDaoInter waterReportDao;

	/** 获取时段水位报表信息 */
	@Override
	public List<Map<String, Object>> getHourData(String stcds, String start,
			String end, String space, PageInfo pager) {
		start = start + " 08:00:00";
		end = end + " 08:00:00";
		List<Map<String, Object>> list = this.getStartAndEnd(start, end, space);
		List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
		/** 将所得到的时间段list来进行截取，从而达到分页功能 */
		/** 获得一共所得到的段数 */
		int len = list.size();
		/** 一共有多少页 */
		int pages = 0;
		if (len % pager.getRows() > 0) {
			pages = len / pager.getRows() + 1;
		} else {
			pages = len / pager.getRows();
		}

		List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
		int first = (pager.getPage() - 1) * pager.getRows();
		int last = 0;
		if (pager.getPage() == pages) {
			last = len - 1;
		} else {
			/** 如果选取的不是最后一页 */
			last = (pager.getPage()) * pager.getRows() - 1;
		}
		for (int i = first; i <= last; i++) {
			pageList.add(list.get(i));
		}
		/** 遍历所有的时间段来进行查询，达到获取所有时间段的目的 */
		for (Map<String, Object> map : pageList) {
			Map<String, Object> obj = waterReportDao
					.getHourData(stcds, map.get("first").toString(),
							map.get("last").toString(), space);
			if (obj != null) {
				infoList.add(obj);
			}
		}
		return infoList;
	}

	/** 去除分页功能，作为水情图形查询多站查询用 */
	@Override
	public List<Map<String, Object>> getHourData2(String stcds, String start,
			String end, String space) {
		start = start + " 08:00:00";
		end = end + " 08:00:00";
		List<Map<String, Object>> list = this.getStartAndEnd(start, end, space);
		List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
		/** 遍历所有的时间段来进行查询，达到获取所有时间段的目的 */
		for (Map<String, Object> map : list) {
			Map<String, Object> obj = waterReportDao
					.getHourData(stcds, map.get("first").toString(),
							map.get("last").toString(), space);
			infoList.add(obj);
		}
		return infoList;
	}

	/** 获取水情图形查询信息，以单站形式逐个进行查询 */
	@Override
	public List<Map<String, Object>> getWaterChartData(String stcd,
			String start, String end) {
		start = start + " 08:00:00";
		end = end + " 08:00:00";
		return waterReportDao.getWaterChartData(stcd, start, end);
	}

	/** 获取日水位报表信息 */
	@Override
	public List<Map<String, Object>> getDayData(String stcds, String date) {
		/** 从选择的日期的早8点到该日期第二天早8点之间的时间 */
		date = date + " 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.DATE, 1);
			List<Map<String, Object>> list = this.getStartAndEnd(date, df
					.format(c.getTime()).toString(), "sixty");
			List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : list) {
				Map<String, Object> obj = waterReportDao.getHourData(stcds, map
						.get("first").toString(), map.get("last").toString(),
						"");
				if (obj != null) {
					infoList.add(obj);
				}
			}
			return infoList;
		} catch (ParseException e) {
			System.out.println("日水位获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}

	/** 获取所分的时间段 */
	@Override
	public List<Map<String, Object>> getStartAndEnd(String start, String end,
			String space) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String first = start;
		String last = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			while (df.parse(first).before(df.parse(end))) {
				last = this.getEndTime(first, space);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("first", first);
				map.put("last", last);
				list.add(map);
				first = last;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;

	}

	/** 根据开始时间获取结束时间 */
	@Override
	public String getEndTime(String start, String space) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Calendar c = Calendar.getInstance();
			Date newDate = df.parse(start);
			c.setTime(newDate);
			if ("five".equals(space)) {
				c.add(Calendar.MINUTE, 5);
			}
			if ("thirty".equals(space)) {
				c.add(Calendar.MINUTE, 30);
			}
			if ("sixty".equals(space)) {
				c.add(Calendar.HOUR, 1);
			}
			if ("day".equals(space)) {
				c.add(Calendar.DATE, 1);
			}
			if ("month".equals(space)) {
				c.add(Calendar.MONTH, 1);
			}

			return df.format(c.getTime()).toString();
		} catch (ParseException e) {
			System.out.println("获取时间出错！");
			e.printStackTrace();
			return null;
		}
	}

	/** 获取月水位报表信息，取所选中月的所有天数，计算每天最后一刻的的水位 */
	@Override
	public List<Map<String, Object>> getMonthData(String stcds, String date) {
		/** 从选择的日期的早8点到下一个月1号的早8点 */
		date = date + "-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.MONTH, 1);
			String month = String.valueOf(c.get(Calendar.MONTH));
			String year = String.valueOf(c.get(Calendar.YEAR));

			List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
			String stcd[] = stcds.split(",");
			int index = 0;
			for (String num : stcd) {
				List<Map<String, Object>> list = waterReportDao.getMonthData(
						num, year, month);
				if (index == 0) {
					for (Map<String, Object> map : list) {
						Map<String, Object> obj = new HashMap<String, Object>();
						obj.put("year", map.get("years"));
						obj.put("month", map.get("months"));
						obj.put("day", map.get("dayss"));
						obj.put(num, map.get("z"));
						infoList.add(obj);
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (i >= infoList.size()) {
							Map<String, Object> map = list.get(i);
							Map<String, Object> obj = new HashMap<String, Object>();
							obj.put("year", map.get("years"));
							obj.put("month", map.get("months"));
							obj.put("day", map.get("dayss"));
							obj.put(num, map.get("z"));
							infoList.add(obj);
						} else {
							Map<String, Object> map = infoList.get(i);
							Map<String, Object> obj = list.get(i);
							map.put(num, obj.get("z"));
						}
					}
				}
				index++;
			}
			return infoList;
		} catch (ParseException e) {
			System.out.println("月水位获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}

	/** 获取年水位报表信息（单站） */
	@Override
	public List<Map<String, Object>> getYearData(String stcd, String date) {
		List<Map<String, Object>> list = waterReportDao.getYearData(stcd, date);
		List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("month", map.get("months"));
			obj.put("day", map.get("dayss"));
			obj.put("z", map.get("z"));
			infoList.add(obj);
		}
		return infoList;
	}
}
