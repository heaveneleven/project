package com.njqs.controller.nav.desktop.person_log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.formmodel.person_log.PersonLogForm;
import com.njqs.service.desktop.log_info.inter.LogInfoServiceInter;
import com.njqs.utils.PageInfo;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserSessionInfo;
/**
 * 我的日志Controller
 * @author heaven
 *
 */
@Controller
@RequestMapping("/person_log")
public class PersonLogController extends BaseController{
	@Resource(name="logInfoService")
	private LogInfoServiceInter logInfoService;
	/**获取日志信息*/
	@RequestMapping("/log_info.do")
	public void getLogInfo(HttpServletRequest request,HttpServletResponse response,PageInfo pager){
		UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		this.writeJson(response, logInfoService.getLogInfo(pager,sessionInfo.getLogin_id()));
	}
	/**添加日志*/
	@RequestMapping("/add_log")
	public String getAddLogPage(){
		return "nav/desktop/person_log/add_log";
	}
	@RequestMapping("/add_log/submit.do")
	public void subAddLog(HttpServletResponse response,HttpServletRequest request,PersonLogForm form){
		UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		this.writeJson(response, logInfoService.saveLog(form, sessionInfo.getLogin_id()));
	}
	/**刪除日志*/
	@RequestMapping("/delete.do")
	public void delLogs(HttpServletResponse response,String ids){
		this.writeJson(response,logInfoService.delLogs(ids));
	}
	/**修改日志*/
	@RequestMapping("/edit_log")
	public String getModifyLogPage(HttpServletRequest request,int log_id){
		request.setAttribute("log", logInfoService.getLogForm(log_id));
		return "nav/desktop/person_log/edit_log";
	}
	/**保存修改日志*/
	@RequestMapping("/edit_log/submit.do")
	public void subEditLog(HttpServletResponse response,PersonLogForm form){
		this.writeJson(response, logInfoService.saveModifyLog(form));
	}
	/**日志查看*/
	@RequestMapping("/check_log")
	public String getCheckLogPage(HttpServletRequest request,int log_id){
		PersonLogForm log = logInfoService.getLogForm(log_id);
		String log_type=log.getLog_type()==0?"个人日志":"工作日志";
		request.setAttribute("log", log);
		request.setAttribute("log_type", log_type);
		return "nav/desktop/person_log/check_log";
	}
}
