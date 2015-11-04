package com.njqs.service.rainReportService.impl;

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

import com.njqs.dao.rainreport.inter.RainReportDaoInter;
import com.njqs.service.rainReportService.inter.RainReportServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
/**
 * @author heaven
 * 雨情报表service
 */
@Service("rainReportService")
public class RainReportServiceImpl implements RainReportServiceInter{
	@Resource(name="rainReportDao")
	private RainReportDaoInter rainReportDao;
	/**获取基站信息*/
	@Override
	public DataGrid<Map<String, Object>> getStations(String sttp,PageInfo pager) {
		return rainReportDao.getStations(sttp,pager);
	}
	/**根据基站编号查询基站名*/
	@Override
	public String getStationName(String stcd) {
		return rainReportDao.getStationName(stcd);
	}
	/**获取时段降水量表一共有多少页*/
	@Override
	public String getPages(String start, String end, String space) {
		start=start+" 08:00:00";
		end=end+" 08:00:00";
		List<Map<String,Object>> list=this.getStartAndEnd(start, end, space);
		/**获得一共所得到的段数*/
		int len=list.size();
		/**一共有多少页,向上取整*/
		int pages=0;
		if(len%16>0){
			pages=len/16+1;
		}else{
			pages=len/16;
		}
		return String.valueOf(pages);
	}
	/**获取时段降雨量报表信息*/
	@Override
	public List<Map<String, Object>> getHourData(String stcds, String start,
			String end, String space,PageInfo pager) {
		start=start+" 08:00:00";
		end=end+" 08:00:00";
		List<Map<String,Object>> list=this.getStartAndEnd(start, end, space);
		List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
		/**将所得到的时间段list来进行截取，从而达到分页功能*/
		/**获得一共所得到的段数*/
		int len=list.size();
		/**一共有多少页*/
		int pages=0;
		if(len%pager.getRows()>0){
			pages=len/pager.getRows()+1;
		}else{
			pages=len/pager.getRows();
		}
		
		List<Map<String,Object>> pageList=new ArrayList<Map<String,Object>>();
		int first=(pager.getPage()-1)*pager.getRows();
		int last=0;
		if(pager.getPage()==pages){
			last=len-1;
		}else{
			/**如果选取的不是最后一页*/
			last=(pager.getPage())*pager.getRows()-1;
		}
		/*
		System.out.println("~~~len:"+len);
		System.out.println("~~~pages:"+pages);
		System.out.println("~~~first:"+first);
		System.out.println("~~~last:"+last);
		*/
		for(int i=first;i<=last;i++){
			if(i>=list.size())
				return null;
			pageList.add(list.get(i));
		}
		/**遍历所有的时间段来进行查询，达到获取所有时间段的目的*/
		for(Map<String,Object> map : pageList){
			Map<String,Object> obj=rainReportDao.getHourData(stcds,map.get("first").toString(),map.get("last").toString(), space);
			infoList.add(obj);
		}
		return infoList;
	}
	/**获取日降水量报表信息*/
	@Override
	public List<Map<String, Object>> getDayData(String stcds, String date) {
		/**从选择的日期的早8点到该日期第二天早8点之间的时间*/
		date=date+" 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.DATE, 1);
			List<Map<String,Object>> list=this.getStartAndEnd(date, df.format(c.getTime()).toString(), "sixty");
			List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map : list){
				Map<String,Object> obj=rainReportDao.getHourData(stcds,map.get("first").toString(),map.get("last").toString(), "");
				infoList.add(obj);
			}
			return infoList;
		} catch (ParseException e) {
			System.out.println("日降水量获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取日降水量累计值*/
	@Override
	public List<Map<String, Object>> getDayDrp(String stcds, String date) {
		/**从选择的日期的早8点到该日期第二天早8点之间的时间*/
		date=date+" 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.DATE, 1);
			List<Map<String,Object>> list=rainReportDao.getDayDrp(stcds,date, df.format(c.getTime()).toString());
			return list;
		} catch (ParseException e) {
			System.out.println("日降水量获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取月降水量报表信息，取所选中月的所有天数，计算每天的降水量*/
	@Override
	public List<Map<String, Object>> getMonthData(String stcds, String date) {
		/**从选择的日期的早8点到下一个月1号的早8点*/
		date=date+"-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.MONTH, 1);
			List<Map<String,Object>> list=this.getStartAndEnd(date, df.format(c.getTime()).toString(), "day");
			List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map : list){
				Map<String,Object> obj=rainReportDao.getHourData(stcds,map.get("first").toString(),map.get("last").toString(), "");
				infoList.add(obj);
			}
			return infoList;
		} catch (ParseException e) {
			System.out.println("月降水量获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取月降水报表月累计值*/
	@Override
	public List<Map<String, Object>> getMonthTotal(String stcds, String date) {
		/**从选择的日期的早8点到下一个月1号的早8点*/
		date=date+"-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.MONTH, 1);
			List<Map<String,Object>> list=this.getStartAndEnd(date, df.format(c.getTime()).toString(), "day");
			List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
			return infoList;
		} catch (ParseException e) {
			System.out.println("月降水量累计值获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取月降水报表降水天数累计值*/
	public List<Map<String,Object>> getMonthDayCount(String stcds,String date){
		/**从选择的日期的早8点到下一个月1号的早8点*/
		date=date+"-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.MONTH, 1);
			List<Map<String,Object>> list=rainReportDao.getMonthDayCount(stcds,date, df.format(c.getTime()).toString());
			return list;
		} catch (ParseException e) {
			System.out.println("月降水量天数累计值获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取月降水报表中各站点的降水月累计*/
	@Override
	public List<Map<String, Object>> getMonthDrpCount(String stcds, String date) {
		/**从选择的日期的早8点到下一个月1号的早8点*/
		date=date+"-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		Date newDate;
		try {
			newDate = df.parse(date);
			c.setTime(newDate);
			c.add(Calendar.MONTH, 1);
			List<Map<String,Object>> list=rainReportDao.getMonthDrpCount(stcds,date, df.format(c.getTime()).toString());
			return list;
		} catch (ParseException e) {
			System.out.println("月降水量各站点降水量累计值获取日期出错！");
			e.printStackTrace();
			return null;
		}
	}
	/**获取年降水量报表信息（单站）*/
	@Override
	public List<Map<String, Object>> getYearData(String stcd, String date) {
		List<Map<String,Object>> list=rainReportDao.getYearData(stcd, date);
		List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : list){
			Map<String,Object> obj=new HashMap<String,Object>();
			obj.put("month", map.get("months"));
			obj.put("day", map.get("dayss"));
			obj.put("drp", map.get("drp"));
			infoList.add(obj);
		}
		return infoList;
	}
	/**获取所分的时间段*/
	@Override
	public List<Map<String, Object>> getStartAndEnd(String start, String end,String space) {	
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String first=start;
		String last="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			while(df.parse(first).before(df.parse(end))){
				last=this.getEndTime(first, space);
			//	System.out.println("~~~~~~last:"+last+"~~~~~~~~~~~~~~~");
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("first", first);
				map.put("last", last);
				list.add(map);
				first=last;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return list;
				
	}
	/**根据开始时间获取结束时间*/
	@Override
	public String getEndTime(String start, String space) {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
			Calendar c=Calendar.getInstance();
			Date newDate=df.parse(start);
			c.setTime(newDate);
			if("five".equals(space)){
			 c.add(Calendar.MINUTE, 5);
			}
			if("thirty".equals(space)){
			 c.add(Calendar.MINUTE, 30);
			}
			if("sixty".equals(space)){
			 c.add(Calendar.HOUR,1);
			}
			if("day".equals(space)){
				c.add(Calendar.DATE,1);
			}
			if("month".equals(space)){
				c.add(Calendar.MONTH,1);
			}
			
			return df.format(c.getTime()).toString();
	} catch (ParseException e) {
		System.out.println("获取时间出错！");
		e.printStackTrace();
		return null;
	}
 }
	/**获取某一个站点年（所有月）累计降水量*/
	@Override
	public List<Map<String, Object>> getMonthDrp(String stcd, String date) {
		return rainReportDao.getMonthdrp(stcd, date);
	}
	/**获取年降雨量*/
	@Override
	public List<Map<String, Object>> getYearDrp(String stcd, String date) {
		return rainReportDao.getYeardrp(stcd, date);
	}
	/**获取一年最大日降雨量及相应日期*/
	@Override
	public List<Map<String, Object>> getMaxForYear(String stcd, String date) {
		return rainReportDao.getMaxForYear(stcd, date);
	}
}