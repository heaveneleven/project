package com.nuaa.controller.desktop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/***
 * 用于桌面布局，分别返回各个位置的页面视图
 * @author heaven
 *
 */
@Controller
@RequestMapping("/desktop/layout")
public class DesktopController {
	/**返回客户上部视图*/
	@RequestMapping("/customer/north")
	public String getNorthPage(){
		return "customer/layout/north";
	}
	/**返回客户左边视图（west部分）这里为nav导航页面*/
	@RequestMapping("/customer/nav")
	public String getWestPage(){
		return "customer/layout/nav";
	}
	/**返回客户底部部分视图*/
	@RequestMapping("/customer/south")
	public String getCenterPage(){
		return "customer/layout/south";
	}
	/**返回管理员上部视图*/
	@RequestMapping("/admin/north")
	public String getAdminNorthPage(){
		return "admin/layout/north";
	}
	/**返回管理员左边视图（west部分）这里为nav导航页面*/
	@RequestMapping("/admin/nav")
	public String getAdminWestPage(){
		return "admin/layout/nav";
	}
	/**返回管理员底部部分视图*/
	@RequestMapping("/admin/south")
	public String getAdminCenterPage(){
		return "admin/layout/south";
	}
}
