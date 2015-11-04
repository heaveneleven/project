package com.njqs.controller.chart;

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
 * 水雨情图形查询Controller
 */
@Controller
@RequestMapping("/chart")
public class ChartController extends BaseController{
	@Resource(name="rainReportService")
	private RainReportServiceInter rainReportService;
	@Resource(name="waterReportService")
	private WaterReportServiceInter waterReportService;
	/**跳转到单站柱状图月报表站点日期选择页面*/
	@RequestMapping("/singleRain/month/chose")
	public String singleMonthChose(){
		return "chart/singleRain/month/chose";
	}
	/**跳转到单站柱状图月报表*/
	@RequestMapping("/singleRain/month/show")
	public String singleMonthShow(HttpServletRequest request,String stcd,String date){
		String name=rainReportService.getStationName(stcd);
		request.setAttribute("name", name);
		request.setAttribute("stcd", stcd);
		request.setAttribute("date", date);
		return "chart/singleRain/month/show";
	}
	/**跳转到单站时段柱状图站点选择页面*/
	@RequestMapping("/singleRain/chose")
	public String singleChoseRain(){
		return "chart/singleRain/chose";
	}
	/**跳转到单站图形显示页面*/
	@RequestMapping("/singleRain/show")
	public String showSingleRain(HttpServletRequest request,String stcd,String date){
		String name=rainReportService.getStationName(stcd);
		request.setAttribute("name", name);
		request.setAttribute("stcd", stcd);
		request.setAttribute("date", date);
		return "chart/singleRain/show";
	}
	/**跳转到多站降水柱状图站点日期选择*/
	@RequestMapping("/multiRain/month/chose")
	public String multiMonthChose(){
		return "chart/multiRain/month/chose";
	}
	/**显示多站降水量*/
	@RequestMapping("/multiRain/month/show")
	public String multiMonthShow(HttpServletRequest request,String stcds,String date){
		/**将选中的所有基站的名字找到返回前台*/
		StringBuilder stnms=new StringBuilder();
		StringBuilder nums=new StringBuilder();
		int index=0;
		String []stcd=stcds.split(",");
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
		request.setAttribute("date", date);
		return "chart/multiRain/month/show";
	}
	/**跳转到水情图形查询站点选择页面*/
	@RequestMapping("/water/chose")
	public String choseWater(){
		return "chart/water/chose";
	}
	/**跳转到水情图形显示页面*/
	@RequestMapping("/water/show")
	public String showWater(HttpServletRequest request,String stcds,String start,String end,String space){
		request.setAttribute("start", start);
		request.setAttribute("end", end);
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
		return "chart/water/show";
	}
	/**获取水情图形信息*/
	@RequestMapping("/water/show.do")
	public void getWaterChartData(HttpServletResponse response,String stcd,String start,String end){
		this.writeJson(response, waterReportService.getWaterChartData(stcd, start, end));
	}
}
