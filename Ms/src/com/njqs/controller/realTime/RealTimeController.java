package com.njqs.controller.realTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.service.realTime.inter.RealTimeServiceInter;
import com.njqs.service.setting.inter.SysSettingServiceInter;
import com.njqs.utils.PageInfo;
/**
 * @author heaven
 * 实时数据查询controller
 */
@Controller
@RequestMapping("/realtime")
public class RealTimeController extends BaseController{
	@Resource(name="realtimeService")
	private RealTimeServiceInter realtimeService;
	@Resource(name="sysSettingService")
	public SysSettingServiceInter sysSettingService;
	/**跳转到实时列表页面*/
	@RequestMapping("/table")
	public String showTable(){
		return "realtime/table";
	}
	@RequestMapping("/tabledata.do")
	public void getJson(HttpServletResponse response,PageInfo pager){
		this.writeJson(response, realtimeService.getAllBaseStation2(pager));
	}
	/**跳转到实时地图页面*/
	@RequestMapping("/map")
	public String showMap(HttpServletRequest request){
		location loc=this.sysSettingService.getLocation();
		if(loc!=null){
			request.setAttribute("lgtd", loc.getLgtd());
			request.setAttribute("lttd", loc.getLttd());
			request.setAttribute("range", loc.getRange());
		}else{
			request.setAttribute("lgtd", "0");
			request.setAttribute("lttd", "0");
			request.setAttribute("range", 12);
		}
		return "realtime/map";
	}
	/**获得地图上站点坐标信息*/
	@RequestMapping("/position.do")
	public void getMapInfo(HttpServletResponse response){
		this.writeJson(response, realtimeService.getStationPosition());
	}
}
