package com.nuaa.controller.admin.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**管理员*/
@Controller
@RequestMapping("/admin/layout")
public class AdminLayoutController {
	
	/**获取layout中间*/
	@RequestMapping("/center")
	public String getCenter(){
		return "content/index";
	}
	/**获取layout顶部*/
	@RequestMapping("/north")
	public String getNorth(){
		return "admin/layout/north";
	}
	/**获取layout底部*/
	@RequestMapping("/south")
	public String getSouth(){
		return "admin/layout/south";
	}
	/**获取左部:主菜单*/
	@RequestMapping("/west/menu")
	public String getMenu(){
		return "/admin/layout/west/menu";
	}
	
	/**获取右部*/
	@RequestMapping("/east")
	public String getEast(){
		return "/admin/layout/east";
	}
}