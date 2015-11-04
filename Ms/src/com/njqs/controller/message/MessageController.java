package com.njqs.controller.message;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.message.inter.MessageServiceInter;
import com.njqs.service.rainReportService.inter.RainReportServiceInter;
import com.njqs.utils.PageInfo;
/**
 * @author heaven
 * 原始报文查询Controller
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{
	@Resource(name="rainReportService")
	private RainReportServiceInter rainReportService;
	@Resource(name="messageService")
	private MessageServiceInter messageService;
	/**跳转到原始报文查询站点时间选择页面*/
	@RequestMapping("/query/chose")
	public String queryChose(){
		return "message/query/chose";
	}
	/**跳转到报文查询显示页面*/
	@RequestMapping("/query/show")
	public String queryShow(HttpServletRequest request,String stcd,String start,String end){
		String stnm=rainReportService.getStationName(stcd);
		request.setAttribute("stcd", stcd);
		request.setAttribute("stnm",stnm);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		return "message/query/show";
	}
	/**获取报文内容*/
	@RequestMapping("/query/show.do")
	public void getQueryData(HttpServletResponse response,String stcd,String start,String end,PageInfo pager){
		this.writeJson(response, messageService.getMessageData(stcd, start, end, pager));
	}
	/**转至原始报文次数查询*/
	@RequestMapping("/count/chose")
	public String messageCountShow(HttpServletRequest request){
		request.setAttribute("stnms", messageService.getAllStationName());
		request.setAttribute("stcds", messageService.getAllStationNum());
		return "message/count/show";
	}
	/**获取总页数*/
	@RequestMapping("/count/pages.do")
	public void getMessageCount(HttpServletResponse response,String start,String end){
		this.writeJson(response, messageService.getPages(start, end));
	}
	/**获取原始报文次数数据*/
	@RequestMapping("/count/show.do")
	public void getMessageCount(HttpServletResponse response,String start,String end,PageInfo pager){
		this.writeJson(response, messageService.getCountData(start, end, pager));
	}
}
