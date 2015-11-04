package com.njqs.service.android.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.android.inter.AndroidDaoInter;
import com.njqs.service.android.inter.AndroidServiceInter;
@Service("androidService")
public class AndroidServiceImpl implements AndroidServiceInter{
	@Resource(name="androidDao")
	private AndroidDaoInter androidDao;
	/**获取原始报文查询选择页面json，添加当日来报次数*/
	@Override
	public List<Map<String, Object>> getOrigMsg() {
		StringBuilder sql=new StringBuilder();
		sql.append("select  b.stcd,b.stnm,s.type,isnull(q.counts,0) as counts from dbo.ST_STBPRP_B as b ");
		sql.append(" left join ");
		sql.append(" (select sttp,type from dbo.ST_STBPRP_STTP) as s on s.sttp=b.sttp ");
		sql.append(" left join ");
	    sql.append(" (select stcd,count(stcd) as counts from dbo.ORIG_MSG where tm >'");
	    sql.append(this.getTime());
	    sql.append("' group by stcd) as q on q.stcd=b.stcd ");
	    try{
	    	List<Map<String,Object>> list=androidDao.searchForMap(sql.toString());
	    	return list;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

/**获取所应的时间若当前为当日早8时以后则选择当日的早8时，否则就选取前一天的早8时*/
@Override
public String getTime() {		
	/**获取当前日期*/
	Date date=new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowDate=df.format(date).toString();
	/**获得当日早8时日期*/
	String eightTm=nowDate+" 08:00:00";
	Calendar c0=Calendar.getInstance();
	Date mydate=new Date();
	try {
	Date newEight = df.parse(eightTm);
	c0.setTime(newEight);
	c0.add(Calendar.HOUR, 8);
	mydate=c0.getTime();
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	try {
		/**判断当前时间是否在当日8时之前若是则减一天*/
		if(date.before(mydate)){
			Calendar c=Calendar.getInstance();
			Date newDate=df.parse(eightTm);
			c.setTime(newDate);
			c.add(Calendar.DATE, -1);
			return df.format(c.getTime()).toString()+" 08:00:00";
		}
		return eightTm;
	} catch (ParseException e) {
		System.out.println("获取时间出错！");
		e.printStackTrace();
		return null;
	}
  }	
}