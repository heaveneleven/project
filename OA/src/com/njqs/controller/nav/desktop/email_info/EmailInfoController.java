package com.njqs.controller.nav.desktop.email_info;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.desktop.email_info.inter.EmailInfoServiceInter;
import com.njqs.utils.PageInfo;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserSessionInfo;
/**
 * 
 * @author heaven
 * 我的邮箱Controller
 */
@Controller
@RequestMapping("/email_info")
public class EmailInfoController extends BaseController{
	@Resource(name="emailService")
	private EmailInfoServiceInter emailService;
	/**获取邮箱目录树*/
	@RequestMapping("/root.do")
	public void getEmailRoot(HttpServletResponse response){
		this.writeJson(response, emailService.getEmailRootTree());
	}
	/**获取发件箱页面*/
	@RequestMapping("/email_in")
	public String getEmailOutPage(){
		return "nav/desktop/email_info/email_in";
	}
	/**获取邮箱tab页面*/
	@RequestMapping("/view")
	public String getEmailInPage(int id){
		if(id==1){
			return "nav/desktop/email_info/email_in";
		}else if(id==2){
			return "nav/desktop/email_info/email_out";
		}else if(id==3){
			return "nav/desktop/email_info/email_draft";
		}else if(id==4){
			return "nav/desktop/email_info/email_recycle";
		}
		return "exception/404";
	}
	/**获取发件箱内容*/
	@RequestMapping("/email_out.do")
	public void getEmailOutData(HttpServletRequest request,HttpServletResponse response,PageInfo pager){
		UserSessionInfo sessionInfo=(UserSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		this.writeJson(response,this.emailService.getEmailOutInfo(pager,sessionInfo.getLogin_id()));
	}
	/**获取新建邮件页面*/
	@RequestMapping("/add_email")
	public String getAddEmailPage(){
		return "nav/desktop/email_info/email_add";
	}
}
