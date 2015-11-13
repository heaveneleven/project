package com.njqs.controller.nav.desktop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/desktop")
public class DesktopController {
	/**为相应的选项卡加载相应的视图内容*/
	@RequestMapping("/view")
	public String toTabs(String id){
		if("1".equals(id)){
			return "nav/desktop/person_info";
		}
		if("2".equals(id)){
			return "nav/desktop/log_info";
		}
		if("3".equals(id)){
			return "nav/desktop/calendar_info";
		}
		if("4".equals(id)){
			return "nav/desktop/message_info";
		}
		if("5".equals(id)){
			return "nav/desktop/email_info";
		}
		if("6".equals(id)){
			return "nav/desktop/readytodo";
		}
		return "exception/404";
	}
}
