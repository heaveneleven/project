package com.nuaa.controller.stu.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学员信息管理部分
 * @author heaven
 *
 */
@Controller
@RequestMapping("/stu/info")
public class InfoController {
	/**跳转到学员信息管理页面*/
	public String showInfo(){
		return "stu/info/show";
	}
}
