package com.njqs.controller.dayinfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.dayinfo.inter.DayInfoServiceInter;
import com.njqs.utils.PageInfo;

/**
 * @author heaven
 * 日水雨情Controller
 */
@Controller
@RequestMapping("/day")
public class DayController extends BaseController{
	@Resource(name="dayinfoService")
	private DayInfoServiceInter dayinfoService;
	/**跳转日雨情页面*/
	@RequestMapping("/rain")
	public String showDayRain(){
		return "dayinfo/raininfo";
	}
	/**获取日雨情表信息*/
	@RequestMapping("/rain.do")
	public void getRainData(HttpServletResponse response,String date,PageInfo pager){
		this.writeJson(response, dayinfoService.getRainData(date,pager));
	}
	/**跳转日水情页面*/
	@RequestMapping("/water")
	public String showDayWater(){
		return "dayinfo/waterinfo";
	}/**获取日水情信息*/
	@RequestMapping("/water.do")
	public void getWaterData(HttpServletResponse response,String date,PageInfo pager){
		this.writeJson(response, dayinfoService.getWaterData(date,pager));
	}
}
