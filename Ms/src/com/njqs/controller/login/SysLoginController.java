package com.njqs.controller.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.dao.login.inter.SysLoginDaoInter;
import com.njqs.domain.user.TUser;
import com.njqs.service.setting.inter.SysSettingServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.FilePath;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserInfo;

@Controller
@RequestMapping("/login")
public class SysLoginController extends BaseController{
	@Resource(name="sysLoginDao")
	private SysLoginDaoInter sysLoginDao;
	@Resource(name="sysSettingService")
	public SysSettingServiceInter sysSettingService;
	
	/**跳转到登录页面*/
	@RequestMapping("/welcome.do")
	public String loginHome(){
		return "login/welcome";
	}
	/**
	 *用户登陆验证 
	 */
	@RequestMapping("/submit.do")
	public void loginValidate(HttpServletRequest request,HttpServletResponse response,String username,String password){
		FilePath.PROJECT_NAME = request.getContextPath() + "/";
		AjaxMsg msg=new AjaxMsg();
		String MD5password=sysSettingService.ecodeByMD5(password);
		try{
		TUser user=sysLoginDao.getUser(username, MD5password);
		if(user!=null){
			UserInfo userInfo=new UserInfo();
			userInfo.setUser(user);
			request.getSession().setAttribute(SessionKey.UserInfoKey, userInfo);	
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
	/**
	 * 验证成功返回系统主界面
	 */
	@RequestMapping("/home.do")
	public String loginSuccess(){
		return "login/home";
	}
	/**
	 * 返回系统设置页面
	 */
	@RequestMapping("/setting.do")
	public String showSetting(){
		return "setting/list";
	}
	/**
	 * 注销登录
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/logoff.do")
	public void logoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/**去掉登录时存入session中的用户信息*/
		request.getSession().removeAttribute(SessionKey.UserInfoKey);
		/**注销的同时返回到登录页面*/
		request.getRequestDispatcher("/login/welcome.do").forward(request, response);
	}
	/**
	 * 跳转管理人员列表
	 */
	@RequestMapping("/manager/show")
	public String showManager(){
		return "manager/show";
	}
}
