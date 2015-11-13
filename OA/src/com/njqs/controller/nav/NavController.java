package com.njqs.controller.nav;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.nav.navInter.NavServiceInter;

@Controller
@RequestMapping("/nav")
public class NavController extends BaseController{
	@Resource(name="navService")
	private NavServiceInter navService;
	/**获取我的桌面内容树*/
	@RequestMapping("/tree/desktop.do")
	public void getMyDesktopTree(HttpServletResponse response){
		this.writeJson(response,navService.getDesktopTree());
	}
	/**获取系统管理内容树*/
	@RequestMapping("/tree/sys.do")
	public void getSysTree(HttpServletResponse response){
		this.writeJson(response, navService.getSysTree());
	}
}
