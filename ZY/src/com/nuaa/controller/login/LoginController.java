package com.nuaa.controller.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.controller.base.BaseController;
import com.nuaa.service.login.inter.AdminLoginServiceInter;
import com.nuaa.service.login.inter.StuLoginServiceInter;
import com.nuaa.utils.AdminSessionInfo;
import com.nuaa.utils.AjaxMsg;
import com.nuaa.utils.EcodeByMD5;
import com.nuaa.utils.SessionKey;
import com.nuaa.utils.StuSessionInfo;

/***
 * @author heaven
 * 用户登录Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	/**学员登录service*/
	@Resource(name="stuLoginService")
	private StuLoginServiceInter stuLoginService;
	/**管理员登录*/
	@Resource(name="adminLoginService")
	private AdminLoginServiceInter adminLoginService;
	
	/**跳转登录页面*/
	@RequestMapping("/welcome")
	public String toLoginPage(){
		return "login/index";
	} 
	/**学员登录*/
	@RequestMapping("/stu_login.do")
	public void stuLogin(HttpServletRequest request,HttpServletResponse response,String login_name,String login_password){
		AjaxMsg msg=new AjaxMsg();
		/**对原密码进行md5加密后得出的密文*/
		String MD5password=EcodeByMD5.ecodeByMD5(login_password);
		try{
			StuSessionInfo sessionInfo=stuLoginService.getUser(login_name, MD5password);
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
	/**管理员登录*/
	@RequestMapping("/admin_login.do")
	public void adminLogin(HttpServletRequest request,HttpServletResponse response,String login_name,String login_password){
		AjaxMsg msg=new AjaxMsg();
		/**对原密码进行md5加密后得出的密文*/
		String MD5password=EcodeByMD5.ecodeByMD5(login_password);
		try{
			AdminSessionInfo sessionInfo=adminLoginService.getUser(login_name, MD5password);
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
	/**跳转到学员主页面*/
	@RequestMapping("/stu/index")
	public String toStuIndex(){
		return "stu/index";
	}
	@RequestMapping("/admin/index")
	public String toAdmin(){
		return "admin/index";
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
