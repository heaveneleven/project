package com.njqs.controller.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heaven
 * 异步加载easyUI layout
 */
@Controller
@RequestMapping("/layout")
public class layoutController {
	/**获取layout中间*/
	@RequestMapping("/center")
	public String getCenter(){
		return "content/index";
	}
	/**获取layout顶部*/
	@RequestMapping("/north")
	public String getNorth(){
		return "layout/north";
	}
	/**获取layout底部*/
	@RequestMapping("/south")
	public String getSouth(){
		return "layout/south";
	}
	/**获取左部:我的桌面*/
	@RequestMapping("/west/my_office")
	public String getOffice(){
		return "layout/west/my_office";
	}
	/**获取左部:系统管理*/
	@RequestMapping("/west/sys")
	public String getSys(){
		return "layout/west/sys";
	}
	/**获取右部*/
	@RequestMapping("/east")
	public String getEast(){
		return "layout/east";
	}
}
