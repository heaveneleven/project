package com.njqs.controller.nav.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 系统管理Controller
 * @author heaven
 *
 */
@Controller
@RequestMapping("/sys")
public class SysController {
	@RequestMapping("/view")
	public String getSysPage(int id){
		switch (id) {
		case 1:
			return "nav/sys/account_manage";
		case 2:
			return "";
		case 3:
			return "";
		default:
			return "";
		}
	}
}
