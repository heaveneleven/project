package com.njqs.controller.exportExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.njqs.controller.base.BaseController;
import com.njqs.dao.dayinfo.inter.DayInfoDaoInter;
import com.njqs.dao.rainreport.inter.RainReportDaoInter;
import com.njqs.export.DayRainExcel;
import com.njqs.export.DayRainReportExcel;
import com.njqs.export.DayWaterExcel;
import com.njqs.export.DayWaterReportExcel;
import com.njqs.export.HourRainReportExcel;
import com.njqs.export.MonthRainReportExcel;
import com.njqs.export.MonthWaterReportExcel;
import com.njqs.export.YearRainReportExcel;
import com.njqs.export.YearWaterReportExcel;
import com.njqs.service.exportExcel.inter.ExcelServiceInter;
import com.njqs.service.rainReportService.inter.RainReportServiceInter;
import com.njqs.service.realTime.inter.RealTimeServiceInter;
import com.njqs.service.waterReportService.inter.WaterReportServiceInter;
import com.njqs.utils.PageInfo;
import com.njqs.utils.ViewExcel;

/**
 * @author heaven 导出Excel表格Controller
 */
@Controller
@RequestMapping("/export")
public class ExportExcelController extends BaseController {
	@Resource(name = "excelService")
	ExcelServiceInter excelService;
	@Resource(name="realtimeService")
	private RealTimeServiceInter realtimeService;
	@Resource(name="dayinfoDao")
	private DayInfoDaoInter dayinfoDao;
	@Resource(name="rainReportDao")
	private RainReportDaoInter rainReportDao;
	@Resource(name="rainReportService")
	private RainReportServiceInter rainReportService;
	@Resource(name="waterReportService")
	private WaterReportServiceInter waterReportService;
	/**
	 * 导出实时列表Excel
	 * 
	 */
	@RequestMapping(value="/table.do",method=RequestMethod.GET)
	public ModelAndView exportTable(ModelMap model,HttpServletRequest request,
			HttpServletResponse response) {
		    model.put("map",realtimeService.getRealTimeExcelInfo());		    
	       ViewExcel viewExcel = new ViewExcel();    
	       return new ModelAndView(viewExcel, model);   
	
	 } 
	/**
	 * 导出日雨情Excel
	 */
	@RequestMapping("/dayrain.do")
	public ModelAndView exportDayRain(ModelMap model,String date){
		List<Map<String,Object>> list=dayinfoDao.getAllRainInfo(date);
		model.put("date", date);
		model.put("map", list);
		DayRainExcel dayRainExcel=new DayRainExcel();
		return new ModelAndView(dayRainExcel,model);
	}
	/**
	 * 导出日水情Excel
	 */
	@RequestMapping("/daywater.do")
	public ModelAndView exportDayWater(ModelMap model,String date){
		List<Map<String,Object>> list=dayinfoDao.getAllWaterInfo(date);
		model.put("date", date);
		model.put("map", list);
		DayWaterExcel dayWaterExcel=new DayWaterExcel();
		return new ModelAndView(dayWaterExcel,model);
	}
	/**导出时段降水量报表Excel*/
	@RequestMapping("/hourRainReport.do")
	public ModelAndView exportHourRainReport(ModelMap model,String stcds,String start,String end,String space,PageInfo pager){
		/**获取时段降水量信息*/
		List<Map<String,Object>> list=rainReportService.getHourData(stcds, start, end, space, pager);
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("date",start+"至"+end+"时段降水报表.xls");
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("hourInfo",list);
		HourRainReportExcel hourExcel=new HourRainReportExcel();
		return new ModelAndView(hourExcel,model);
	}
	/**导出日降水量报表Excel*/
	@RequestMapping("/dayRainReport.do")
	public ModelAndView exportDayRainReport(ModelMap model,String stcds,String date){
		/**获取日降水量各站的时段降水信息*/
		List<Map<String,Object>> dayInfo=rainReportService.getDayData(stcds, date);
		/**获取日降水量各站的累计降水信息*/
		List<Map<String,Object>> glist=rainReportService.getDayDrp(stcds, date);
		/**获取各站的名字*/
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("dayInfo", dayInfo);
		model.put("glist", glist);
		model.put("date", date);
		DayRainReportExcel dayExcel=new DayRainReportExcel();
		return new ModelAndView(dayExcel,model);
	}
	/**导出月降水报表Excel*/
	@RequestMapping("/monthRainReport.do")
	public ModelAndView exportMonthRainReport(ModelMap model,String stcds,String date){
		/**获取月降水量各站的日降水信息*/
		List<Map<String,Object>> monthInfo=rainReportService.getMonthData(stcds, date);
		/**获取月降水量各站的累计降水信息*/
		List<Map<String,Object>> glist=rainReportService.getMonthDrpCount(stcds, date);
		/**获取月降水量各站的月降水天数*/
		List<Map<String,Object>> dayCount=rainReportService.getMonthDayCount(stcds, date);
		/**获取各站的名字*/
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("monthInfo", monthInfo);
		model.put("dayCount", dayCount);
		model.put("glist", glist);
		model.put("date", date);
		MonthRainReportExcel monthExcel=new MonthRainReportExcel();
		return new ModelAndView(monthExcel,model);
	}
	/**导出年降水报表Excel*/
	@RequestMapping("/yearRainReport")
	public ModelAndView exportYearRainReport(ModelMap model,String stcd,String date){
		/**获取年报表每一天的降雨量*/
		List<Map<String,Object>> dayInfo=rainReportService.getYearData(stcd, date);
		/**获取月累计降水量及降水天数*/
		List<Map<String,Object>> monthInfo=rainReportService.getMonthDrp(stcd, date);
		/**获取全年降雨量以及天数*/
		List<Map<String,Object>> allyearInfo=rainReportService.getYearDrp(stcd, date);
		/**获取最大日降雨量*/
		List<Map<String,Object>> maxInfo=rainReportService.getMaxForYear(stcd, date);
		/**取得当前所选基站名*/
		String name=rainReportService.getStationName(stcd);
		model.put("date", date);
		model.put("name", name);
		model.put("dayInfo", dayInfo);
		model.put("monthInfo", monthInfo);
		model.put("allyearInfo", allyearInfo);
		model.put("maxInfo", maxInfo);
		YearRainReportExcel yearExcel=new YearRainReportExcel();
		return new ModelAndView(yearExcel,model);
	}
	/**导出时段水位报表Excel*/
	@RequestMapping("/hourwaterReport.do")
	public ModelAndView exportHourWaterReport(ModelMap model,String stcds,String start,String end,String space,PageInfo pager){
		/**获取时段降水量信息*/
		List<Map<String,Object>> list=waterReportService.getHourData(stcds, start, end, space, pager);
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("date",start+"至"+end+"时段水位报表.xls");
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("hourInfo",list);
		HourRainReportExcel hourExcel=new HourRainReportExcel();
		return new ModelAndView(hourExcel,model);
	}
	/**导出日降水量报表Excel*/
	@RequestMapping("/dayWaterReport.do")
	public ModelAndView exportDayWaterReport(ModelMap model,String stcds,String date){
		/**获取日降水量各站的时段降水信息*/
		List<Map<String,Object>> dayInfo=waterReportService.getDayData(stcds, date);
		
		/**获取各站的名字*/
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("dayInfo", dayInfo);
		model.put("date", date);
		DayWaterReportExcel dayExcel=new DayWaterReportExcel();
		return new ModelAndView(dayExcel,model);
	}
	/**导出月水位报表Excel*/
	@RequestMapping("/monthWaterReport.do")
	public ModelAndView exportMonthWaterReport(ModelMap model,String stcds,String date){
		/**获取月降水量各站的日降水信息*/
		List<Map<String,Object>> monthInfo=waterReportService.getMonthData(stcds, date);
		/**获取各站的名字*/
		String []stcd=stcds.split(",");
		List<String> names=new ArrayList<String>();
		List<String> stcdlist=new ArrayList<String>();
		for(int i=0;i<stcd.length;i++){
			names.add(rainReportDao.getStationName(stcd[i]));
			stcdlist.add(stcd[i]);
		}
		model.put("names", names);
		model.put("stcdlist", stcdlist);
		model.put("monthInfo", monthInfo);
		model.put("date", date);
		MonthWaterReportExcel monthExcel=new MonthWaterReportExcel();
		return new ModelAndView(monthExcel,model);
	}
	/**导出年降水报表Excel*/
	@RequestMapping("/yearWaterReport")
	public ModelAndView exportYearWaterReport(ModelMap model,String stcd,String date){
		/**获取年报表每一天的降雨量*/
		List<Map<String,Object>> dayInfo=waterReportService.getYearData(stcd, date);
		/**取得当前所选基站名*/
		String name=rainReportService.getStationName(stcd);
		model.put("date", date);
		model.put("name", name);
		model.put("dayInfo", dayInfo);
		YearWaterReportExcel yearExcel=new YearWaterReportExcel();
		return new ModelAndView(yearExcel,model);
	}
}

