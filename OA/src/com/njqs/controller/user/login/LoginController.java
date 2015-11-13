package com.njqs.controller.user.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.user.login.inter.LoginServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.EcodeByMD5;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserSessionInfo;

/***
 * @author heaven
 * 用户登录Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Resource(name="loginService")
	private LoginServiceInter loginService;
	/**用户登录验证，ajax提交*/
	@RequestMapping("/validate.do")
	public void userValidate(HttpServletResponse response,HttpServletRequest request,String login_name,String login_password){
		AjaxMsg msg=new AjaxMsg();
		String MD5password=EcodeByMD5.ecodeByMD5(login_password);
		try{
		UserSessionInfo sessionInfo=loginService.getUser(login_name, MD5password);
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
	/**跳转到用户主页面*/
	@RequestMapping("/index")
	public String toHome(){
		return "home/index";
	}
	/**跳转登录页面*/
	@RequestMapping("/welcome")
	public String toLoginPage(){
		return "login/index";
	} 
	/**跳转注册页面*/
	@RequestMapping("/register")
	public String toRegisterPage(){
		return "login/register";
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
