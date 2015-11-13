package com.njqs.controller.nav.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.sys.inter.AccountMangerServiceInter;
import com.njqs.utils.PageInfo;
@Controller
@RequestMapping("account_manager")
public class AccountManagerController extends BaseController{
	@Resource(name="accountManagerService")
	private AccountMangerServiceInter accountManagerService;
	/**获取登陆账号相关信息**/
	@RequestMapping("/accountInfo.do")
	public void getAccountInfo(HttpServletResponse response, PageInfo pager){
		this.writeJson(response, accountManagerService.getAccountInfo(pager));
	}
}
