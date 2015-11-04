package com.njqs.controller.waterReport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.rainReportService.inter.RainReportServiceInter;
import com.njqs.service.waterReportService.inter.WaterReportServiceInter;
import com.njqs.utils.PageInfo;
/**
 * @author heaven
 * 水位报表查询
 */
@Controller
@RequestMapping("/waterreport")
public class WaterReportController extends BaseController{
	@Resource(name="rainReportService")
	private RainReportServiceInter rainReportService;
	@Resource(name="waterReportService")
	private WaterReportServiceInter waterReportService;
	/**跳转到时段水位站选择页面*/
	@RequestMapping("/hour/chose")
	public String showHour(){
		return "waterreport/hour/chose";
	}
	/**跳转到时段水位显示页面*/
	@RequestMapping("/hour/show")
	public String showHourRainReport(HttpServletRequest request,String stcds,String start,String end,String space){	
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("space", space);
		String []stcd=stcds.split(",");
		/**将选中的所有基站的名字找到返回前台*/
		StringBuilder stnms=new StringBuilder();
		StringBuilder nums=new StringBuilder();
		int index=0;
		for(String num : stcd){
			if(index++==0){
			stnms.append(rainReportService.getStationName(num).trim());
			nums.append(num.trim());
			}
			else{
			stnms.append(","+rainReportService.getStationName(num).trim());	
			nums.append(","+num.trim());
			}
		}
		request.setAttribute("stcds", nums);;
		request.setAttribute("stnms", stnms.toString());
		request.setAttribute("totalpages",rainReportService.getPages(start, end, space));
		return "waterreport/hour/show";
	}
	/**获取时段报表信息*/
	@RequestMapping("/hour/show.do")
	public void getHourData(HttpServletResponse response,String stcds,String start,String end,String space,PageInfo pager){
		this.writeJson(response, waterReportService.getHourData(stcds, start, end, space,pager));
	}
	/**跳转到日水位报表基站选择页面*/
	@RequestMapping("/day/chose")
	public String showRainChose(){
		return "waterreport/day/chose";
	}
	/**跳转到日水位报表显示页面*/
	@RequestMapping("/day/show")
	public String showDayRainReport(HttpServletRequest request,String stcds,String date){
		String []stcd=stcds.split(",");
		/**将选中的所有基站的名字找到返回前台*/
		StringBuilder stnms=new StringBuilder();
		StringBuilder nums=new StringBuilder();
		int index=0;
		for(String num : stcd){
			if(index++==0){
			stnms.append(rainReportService.getStationName(num).trim());
			nums.append(num.trim());
			}
			else{
			stnms.append(","+rainReportService.getStationName(num).trim());	
			nums.append(","+num.trim());
			}
		}
		request.setAttribute("date", date);
		request.setAttribute("stcds", nums);;
		request.setAttribute("stnms", stnms.toString());
		return "waterreport/day/show";
	}
	/**获取日水位报表信息*/
	@RequestMapping("/day/show.do")
	public void getDayData(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, waterReportService.getDayData(stcds, date));
	}
	/**跳转月水位报表基站月份选择页面*/
	@RequestMapping("/month/chose")
	public String showMonthChose(){
		return "waterreport/month/chose";
	}
	/**跳转到月水位报表显示页面*/
	@RequestMapping("/month/show")
	public String showMonthRainReport(HttpServletRequest request,String stcds,String date){
		String []stcd=stcds.split(",");
		/**将选中的所有基站的名字找到返回前台*/
		StringBuilder stnms=new StringBuilder();
		StringBuilder nums=new StringBuilder();
		int index=0;
		for(String num : stcd){
			if(index++==0){
			stnms.append(rainReportService.getStationName(num).trim());
			nums.append(num.trim());
			}
			else{
			stnms.append(","+rainReportService.getStationName(num).trim());	
			nums.append(","+num.trim());
			}
		}
		request.setAttribute("date", date);
		request.setAttribute("stcds", nums);;
		request.setAttribute("stnms", stnms.toString());
		return "waterreport/month/show";
	}
	/**获取月水位报表信息*/
	@RequestMapping("/month/show.do")
	public void getMonthData(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, waterReportService.getMonthData(stcds, date));
	}
	/**跳转年水位报表基站年份选择页面*/
	@RequestMapping("/year/chose")
	public String showYearChose(){
		return "waterreport/year/chose";
	}
	/**跳转到年水位报表显示页面*/
	@RequestMapping("/year/show")
	public String showYearRainReport(HttpServletRequest request,String stcd,String date){
		String stnm=rainReportService.getStationName(stcd).trim();
		request.setAttribute("stcd", stcd);
		request.setAttribute("stnm", stnm);
		request.setAttribute("date", date);
		return "waterreport/year/show";
	}
	/**获取年水位报表信息*/
	@RequestMapping("/year/show.do")
	public void getYearData(HttpServletResponse response,String stcd,String date){
		this.writeJson(response, waterReportService.getYearData(stcd, date));
	}
}
