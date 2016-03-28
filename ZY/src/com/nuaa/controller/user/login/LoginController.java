package com.nuaa.controller.user.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nuaa.controller.base.BaseController;
import com.nuaa.utils.SessionKey;

/***
 * @author heaven
 * 用户登录Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	/**跳转登录页面*/
	@RequestMapping("/welcome")
	public String toLoginPage(){
		return "login/index";
	} 
	/**学员登录*/
	@RequestMapping("/stu_login.do")
	public void stuLogin(String username,String password){
		
	}
	/**跳转到学员主页面*/
	@RequestMapping("/stu/index")
	public String toStuIndex(){
		return "stu/index";
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
