package com.njqs.controller.sysManager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.sysManager.inter.MenuManagerServiceInter;
import com.njqs.utils.JsTree;
import com.njqs.utils.Tree;
@Controller
@RequestMapping("/sysManager")
public class SysManagerController extends BaseController{
	@Resource(name="menuService")
	private MenuManagerServiceInter menuService;
	
	@RequestMapping("/main.do")
	public String showMenu(){
		return "manager/main";
	} 
	/**显示信息服务*/
	@RequestMapping("/list.do")
	public String showMenuList(){
		return "manager/list";
	}
	/**获得EasyUI菜单树之同步加载*/
	@RequestMapping("/getMenu.do")
	public void getMenuTree(HttpServletResponse response){
		List<Tree> list=new ArrayList<Tree>();
		list=menuService.getMenuTree();
		this.writeJson(response, list);
	}
	/**获得EasyUI菜单树之异步加载*/
	@RequestMapping("/getMenu2.do")
	public void getMenuTree2(HttpServletResponse response,String id){
		List<Tree> list=new ArrayList<Tree>();
		if(null==id)
			id="0";
		list=menuService.getAsyMenuTree(id);
		if("0"==id){
			Tree root=new Tree();
			root.setId("0");
			root.setText("信息服务");
			root.setState("open");
			root.setChildren(list);
			List<Tree> first=new ArrayList<Tree>();
			first.add(root);
			this.writeJson(response, first);
		}else{
			this.writeJson(response, list);
		}
	}
	/**获得JStree菜单树之异步加载*/
	@RequestMapping("getMenu3.do")
	public void getMenuTree3(HttpServletResponse response,String id){
		List<JsTree> list=new ArrayList<JsTree>();
		if(null==id)
			id="0";
		list=menuService.getAsyMenuTree2(id);
		this.writeJson(response, list);
	}
	/**显示系统设置*/
	@RequestMapping("/sysSet.do")
	public String showSysSetting(){
		return "manager/sysSet";
	}
	/**显示网页底部*/
	@RequestMapping(("/foot.do"))
	public String showFoot(){
		return "foot";
	}
}
