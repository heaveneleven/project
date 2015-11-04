package com.njqs.controller.rainReport;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.njqs.controller.base.BaseController;
import com.njqs.service.rainReportService.inter.RainReportServiceInter;
import com.njqs.utils.PageInfo;
/***
 * @author heaven
 * 雨情报表查询Controller
 */
@Controller
@RequestMapping("/rainreport")
public class RainReportController extends BaseController{
	@Resource(name="rainReportService")
	private RainReportServiceInter rainReportService;
	/**跳转到时段雨情雨量站选择页面*/
	@RequestMapping("/hour/chose")
	public String showHour(){
		return "rainreport/hour/chose";
	}
	/**跳转到时段雨情显示页面*/
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
		return "rainreport/hour/show";
	}
	/**获取时段报表信息*/
	@RequestMapping("/hour/show.do")
	public void getHourData(HttpServletResponse response,String stcds,String start,String end,String space,PageInfo pager){
		this.writeJson(response, rainReportService.getHourData(stcds, start, end, space,pager));
	}
	/**获取雨量站信息*/
	@RequestMapping("/chose.do")
	public void getHourStation(HttpServletResponse response,String sttp,PageInfo pager){
		this.writeJson(response,rainReportService.getStations(sttp,pager));
	}
	/**跳转到日降水量报表基站选择页面*/
	@RequestMapping("/day/chose")
	public String showRainChose(){
		return "rainreport/day/chose";
	}
	/**跳转到日降水量报表显示页面*/
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
		return "rainreport/day/show";
	}
	/**获取日降水量报表信息*/
	@RequestMapping("/day/show.do")
	public void getDayData(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, rainReportService.getDayData(stcds, date));
	}
	/**获取日降水量累计值*/
	@RequestMapping("/day/getDayDrp.do")
	public void getDayDrp(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, rainReportService.getDayDrp(stcds, date));
	}
	/**跳转月降水量报表基站月份选择页面*/
	@RequestMapping("/month/chose")
	public String showMonthChose(){
		return "rainreport/month/chose";
	}
	/**跳转到月降水量报表显示页面*/
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
		return "rainreport/month/show";
	}
	/**获取月降水量报表信息*/
	@RequestMapping("/month/show.do")
	public void getMonthData(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, rainReportService.getMonthData(stcds, date));
	}
//	/**获取月降水报表中的各站的月累计*/
//	@RequestMapping("/month/gettoal.do")
//	public void getMonthTotal(HttpServletResponse response,String stcds,String date){
//		
//	}
	/**获取月降水报表中的各站的天数累计*/
	@RequestMapping("/month/getDayCount.do")
	public void getMonthDayCount(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, rainReportService.getMonthDayCount(stcds, date));
	}
	/**获取月降水报表中的各站的月累计*/
	@RequestMapping("/month/gettotal.do")
	public void getMonthDrpCount(HttpServletResponse response,String stcds,String date){
		this.writeJson(response, rainReportService.getMonthDrpCount(stcds, date));
	}
	/**跳转年降水量报表基站年份选择页面*/
	@RequestMapping("/year/chose")
	public String showYearChose(){
		return "rainreport/year/chose";
	}
	/**跳转到年降水量报表显示页面*/
	@RequestMapping("/year/show")
	public String showYearRainReport(HttpServletRequest request,String stcd,String date){
		String stnm=rainReportService.getStationName(stcd).trim();
		request.setAttribute("stcd", stcd);
		request.setAttribute("stnm", stnm);
		request.setAttribute("date", date);
		return "rainreport/year/show";
	}
	/**获取年降雨量报表信息*/
	@RequestMapping("/year/show.do")
	public void getYearData(HttpServletResponse response,String stcd,String date){
		this.writeJson(response, rainReportService.getYearData(stcd, date));
	}
	/**获取年降雨量报表月累计降水量*/
	@RequestMapping("/year/getMonthInty.do")
	public void getMonthInty(HttpServletResponse response,String stcd,String date){
		this.writeJson(response, rainReportService.getMonthDrp(stcd, date));
	}
	/**获取年降雨量*/
	@RequestMapping("/year/getYearInty.do")
	public void getYearInty(HttpServletResponse response,String stcd,String date){
		this.writeJson(response, rainReportService.getYearDrp(stcd, date));
	}
	/**获取最大日降雨量*/
	@RequestMapping("/year/getMaxForYear.do")
	public void getMaxForYear(HttpServletResponse response,String stcd,String date){
		this.writeJson(response, rainReportService.getMaxForYear(stcd, date));
	}
}
