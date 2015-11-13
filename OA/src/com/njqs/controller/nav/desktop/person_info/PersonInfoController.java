package com.njqs.controller.nav.desktop.person_info;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.formmodel.personInfo.PersonInfoForm;
import com.njqs.service.desktop.person_info.inter.PersonInfoServiceInter;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserSessionInfo;
/**用户资料信息Controller*/
@Controller
@RequestMapping("/person_info")
public class PersonInfoController extends BaseController{
	@Resource(name="personInfoService")
	private PersonInfoServiceInter personInfoService;
	/**获取用户头像*/
	@RequestMapping("/person_head")
	public String getPersonHead(){
		return "nav/desktop/person_info/person_head";
	}
	/**获取用户信息*/
	@RequestMapping("/person_detail")
	public String getPersonDetail(HttpServletRequest request){
		UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		request.setAttribute("userInfo",personInfoService.getPersonInfoForm(sessionInfo.getUser_info_id()));
		return "nav/desktop/person_info/person_detail";
	}
	@RequestMapping("/submit.do")
	public void subPersonInfo(HttpServletResponse response,HttpServletRequest request,PersonInfoForm form){
		UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		this.writeJson(response,personInfoService.savePersonForm(form, sessionInfo.getUser_info_id()));
	}
}
