package com.nuaa.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.controller.base.BaseController;
/**
 * 
 * @author heaven
 *	主页面Controller
 */
@Controller
@RequestMapping("/main")
public class MainPageController extends BaseController{
	@RequestMapping("/index.html")
	public String toMainPage(){
		return "main/index";
	}
}
