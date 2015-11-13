package com.njqs.controller.user.register;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.formmodel.personInfo.RegUserForm;
import com.njqs.service.user.register.inter.RegisterServiceInter;

/**新用户注册*/
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{
	@Resource(name="registerService")
	private RegisterServiceInter registerService;
	/**提交注册信息*/
	@RequestMapping("/submit.do")
	public void submitInfo(HttpServletResponse response,RegUserForm  module){
		this.writeJson(response, registerService.saveNewUser(module));
	}
}
