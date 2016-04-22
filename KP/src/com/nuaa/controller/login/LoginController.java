package com.nuaa.controller.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.controller.base.BaseController;
import com.nuaa.service.login.inter.LoginServiceInter;
import com.nuaa.utils.AjaxMsg;
import com.nuaa.utils.EcodeByMD5;
import com.nuaa.utils.SessionKey;
import com.nuaa.utils.UserSessionInfo;
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Resource(name="loginService")
	private LoginServiceInter loginService;
	/**跳转登录页面*/
	@RequestMapping("/welcome")
	public String toLoginPage(){
		return "login/index";
	}
	/**客户登录验证，ajax提交*/
	@RequestMapping("/customer/validate.do")
	public void customerValidate(HttpServletResponse response,HttpServletRequest request,String login_name,String login_password){
		AjaxMsg msg=new AjaxMsg();
		String MD5password=EcodeByMD5.ecodeByMD5(login_password);
		try{
		UserSessionInfo sessionInfo=loginService.getUser(login_name, MD5password,0);
		if(sessionInfo!=null){
			request.getSession().setAttribute(SessionKey.UserInfoKey, sessionInfo);	
			msg.setSuccess(true);
		}else{
			msg.setSuccess(false);
			msg.setMsg("用户名或密码错误！");
		}
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("连接数据库失败！");
		}
		
		this.writeJson(response, msg);	
	}
	/**登录成功跳转客户主页面*/
	@RequestMapping("/customer/home/index")
	public String toCustomerHome(){
		return "customer/home/index";
	}
	/**管理员登录验证，ajax提交*/
	@RequestMapping("/admin/validate.do")
	public void adminValidate(HttpServletResponse response,HttpServletRequest request,String login_name,String login_password){
		AjaxMsg msg=new AjaxMsg();
		String MD5password=EcodeByMD5.ecodeByMD5(login_password);
		try{
		UserSessionInfo sessionInfo=loginService.getUser(login_name, MD5password,1);
		if(sessionInfo!=null){
			request.getSession().setAttribute(SessionKey.UserInfoKey, sessionInfo);	
			msg.setSuccess(true);
		}else{
			msg.setSuccess(false);
			msg.setMsg("用户名或密码错误！");
		}
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("连接数据库失败！");
		}
		this.writeJson(response, msg);	
	}
	/**登录成功跳转管理员主页面*/
	@RequestMapping("/admin/home/index")
	public String toAdminHome(){
		return "admin/home/index";
	}
	/**注销用户*/
	@RequestMapping("/logoff.do")
	public void logoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/**去掉登录时存入session中的用户信息*/
		request.getSession().removeAttribute(SessionKey.UserInfoKey);
		/**注销的同时返回到登录页面*/
		request.getRequestDispatcher("/login/welcome").forward(request, response);
	}
}
