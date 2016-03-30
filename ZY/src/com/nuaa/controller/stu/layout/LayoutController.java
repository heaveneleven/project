package com.nuaa.controller.stu.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 
 * @author heaven
 * 异步加载easyUI layout
 * 学员页面部分
 */
@Controller
@RequestMapping("/stu/layout")
public class LayoutController {
	/**获取layout中间*/
	@RequestMapping("/center")
	public String getCenter(){
		return "content/index";
	}
	/**获取layout顶部*/
	@RequestMapping("/north")
	public String getNorth(){
		return "stu/layout/north";
	}
	/**获取layout底部*/
	@RequestMapping("/south")
	public String getSouth(){
		return "stu/layout/south";
	}
	/**获取左部:我的信息*/
	@RequestMapping("/west/myInfo")
	public String getOffice(){
		return "/stu/layout/west/myInfo";
	}
	/**获取左部:专业选择*/
	@RequestMapping("/west/majorChose")
	public String getSys(){
		return "/stu/layout/west/majorChose";
	}
	/**获取右部*/
	@RequestMapping("/east")
	public String getEast(){
		return "/stu/layout/east";
	}
}
