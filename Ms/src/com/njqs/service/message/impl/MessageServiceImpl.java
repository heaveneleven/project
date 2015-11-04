package com.njqs.service.message.impl;
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

import com.njqs.dao.message.inter.MessageDaoInter;
import com.njqs.service.message.inter.MessageServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Service("messageService")
public class MessageServiceImpl implements MessageServiceInter{
	@Resource(name="messageDao")
	private MessageDaoInter messageDao;
	/**获取原始报文数据*/
	@Override
	public DataGrid<Map<String, Object>> getMessageData(String stcd,
			String start, String end, PageInfo pager) {
			return messageDao.getMessageData(stcd, start, end, pager);
	}
	/**获取所有基站名，所有名字形成name1,name2,···形式的字符串返回*/
	@Override
	public String getAllStationName() {
		List<Map<String,Object>> names=messageDao.getAllNames();
		StringBuilder str=new StringBuilder();
		int count=0;
		for(Map<String,Object> map : names){
			if(count==0){
			str.append(map.get("stnm").toString().trim());
			++count;
			}else{
				str.append(","+map.get("stnm").toString().trim());
			}
		}
		return str.toString();
	}
	
	@Override
	public String getAllStationNum() {
		List<Map<String,Object>> names=messageDao.getAllNum();
		StringBuilder str=new StringBuilder();
		int count=0;
		for(Map<String,Object> map : names){
			if(count==0){
			str.append(map.get("stcd").toString().trim());
			++count;
			}else{
				str.append(","+map.get("stcd").toString().trim());
			}
		}
		return str.toString();
	}
	/**获取来报次数信息*/
	@Override
	public List<Map<String, Object>> getCountData( String start,
			String end,PageInfo pager) {
		start=start+" 08:00:00";
		end=end+" 08:00:00";
		List<Map<String,Object>> list=this.getStartAndEnd(start, end, "day");
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
		
		for(int i=first;i<=last;i++){
			pageList.add(list.get(i));
		}
		/**遍历所有的时间段来进行查询，达到获取所有时间段的目的*/
		for(Map<String,Object> map : pageList){
			Map<String,Object> obj=messageDao.getCountData(map.get("first").toString(),map.get("last").toString());
			infoList.add(obj);
		}
		return infoList;
	}
	/**获取时段降水量表一共有多少页*/
	@Override
	public String getPages(String start, String end){
		start=start+" 08:00:00";
		end=end+" 08:00:00";
		List<Map<String,Object>> list=this.getStartAndEnd(start, end,"day");
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
}
