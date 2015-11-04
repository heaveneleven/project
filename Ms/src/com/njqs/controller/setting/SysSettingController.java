package com.njqs.controller.setting;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.njqs.controller.base.BaseController;
import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.model.ManagerModel;
import com.njqs.service.setting.inter.SysSettingServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.PageInfo;
@Controller
@RequestMapping("/setting")
public class SysSettingController extends BaseController{
	@Resource(name="sysSettingService")
	public SysSettingServiceInter sysSettingService;
	/**返回修改密码页面*/
	@RequestMapping("/resetpassword")
	public String showRestPassword(){
		return "setting/resetpassword";
	}
	/**提交修改密码*/
	@RequestMapping("/subpassword.do")
	public void modifyPassword(HttpServletRequest request,HttpServletResponse response, String newpassword,String origpassword){
		AjaxMsg msg = sysSettingService.restPassword(request, newpassword, origpassword);
		this.writeJson(response, msg);
	}
	/**返回添加用户页面*/
	@RequestMapping("/adduser")
	public String addUser(){
		return "setting/adduser";
	}
	/**提交添加账户*/
	@RequestMapping("/subadduser.do")
	public void submitAddUser(HttpServletResponse response,String username,String password,String authority){
		AjaxMsg msg=sysSettingService.submitAddUser(username, password, authority);
		this.writeJson(response, msg);
	}
	/**水库水容关系表显示页面*/
	@RequestMapping("/wRelation/show")
	public String showWRelation(){
		return "setting/wRelation/show";
	}
	/**水库水容关系表显示页面*/
	@RequestMapping("/vRelation/show")
	public String showVRelation(){
		return "setting/vRelation/show";
	}
	/**上传Excel*/
	@RequestMapping(value = "/upload.do")  
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file,String stcd, HttpServletRequest request,HttpServletResponse response, ModelMap model) {  
		AjaxMsg msg=new AjaxMsg();
		if(file.isEmpty() || file==null){
			msg.setSuccess(false);
			msg.setMsg("请先选择相应的水容关系表");
			this.writeJson(response, msg);
			return;
		}
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs(); 
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
            if(fileName.endsWith("xls")){
            	this.writeJson(response, sysSettingService.uploadExcel2003(stcd, path + "\\" +fileName));
            }else if(fileName.endsWith("xlsx")){
            	this.writeJson(response, sysSettingService.uploadExcel2007(stcd, path + "\\" +fileName));
            }else{
            	msg.setSuccess(false);
    			msg.setMsg("请导入Excel 2003 或 Excel 2007版本文件！");
    			this.writeJson(response, msg);
            }
        } catch (Exception e) {  
            e.printStackTrace();  
            msg.setSuccess(false);
			msg.setMsg("上传失败！");
			this.writeJson(response, msg);
        }  
    }  
	/**上传水位流量关系Excel*/
	@RequestMapping(value = "/vRelation/upload.do")  
    public void vRelationUpload(@RequestParam(value = "file", required = false) MultipartFile file,String stcd, HttpServletRequest request,HttpServletResponse response, ModelMap model) {  
		AjaxMsg msg=new AjaxMsg();
		if(file.isEmpty() || file==null){
			msg.setSuccess(false);
			msg.setMsg("请先选择相应的水位流量关系表");
			this.writeJson(response, msg);
			return;
		}
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs(); 
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
            if(fileName.endsWith("xls")){
            	this.writeJson(response, sysSettingService.uploadVRelationExcel2003(stcd, path + "\\" +fileName));
            }else if(fileName.endsWith("xlsx")){
            	this.writeJson(response, sysSettingService.uploadVRelationExcel2007(stcd, path + "\\" +fileName));
            }else{
            	msg.setSuccess(false);
    			msg.setMsg("请导入Excel 2003 或 Excel 2007版本文件！");
    			this.writeJson(response, msg);
            }
        } catch (Exception e) {  
            e.printStackTrace();  
            msg.setSuccess(false);
			msg.setMsg("上传失败！");
			this.writeJson(response, msg);
        }  
    }  
	/**跳转水位基值设置页面*/
	@RequestMapping("/baseHigh/show")
	public String showBaseH(){
		return "setting/baseHigh/show";
	}
	/**获取水位站点添加水位基值值*/
	@RequestMapping("/table.do")
	public void getHourStation(HttpServletResponse response,String type,PageInfo pager){
		this.writeJson(response,this.sysSettingService.getStations(type,pager));
	}
	/**提交水位基值设置*/
	@RequestMapping("/baseHigh/save.do")
	public void subBaseH(HttpServletResponse response,String id,String base){
		String regEx="^[0-9]+\\.{0,1}[0-9]{0,2}$";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(base);
		boolean result=m.find();
		if(!result){
			AjaxMsg msg=new AjaxMsg();
			msg.setSuccess(false);
			msg.setMsg("请输入有效水位基值！");
			this.writeJson(response,msg);
			return;
		}
		this.writeJson(response,this.sysSettingService.saveBase(id, base));
	}
	/**跳转水位告警设置页面*/
	@RequestMapping("/warn/show")
	public String showWarn(){
		return "setting/warn/show";
	}
	/**保存水位报警设置*/
	@RequestMapping("/warn/save.do")
	public void subWarn(HttpServletResponse response,String id,String valid,String warn){
		boolean isWarn="是".equals(valid);
		String regEx="^[0-9]+\\.{0,1}[0-9]{0,2}$";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(warn);
		boolean result=m.find();
		if(!result){
			AjaxMsg msg=new AjaxMsg();
			msg.setSuccess(false);
			msg.setMsg("请输入有效水位预警值！");
			this.writeJson(response,msg);
			return;
		}
		this.writeJson(response,this.sysSettingService.saveWarn(id, warn, isWarn));
	}
	/**跳转地图设置页面*/
	@RequestMapping("/map/show")
	public String showMap(HttpServletRequest request){
		location loc=this.sysSettingService.getLocation();
		if(loc!=null){
			request.setAttribute("lgtd", loc.getLgtd());
			request.setAttribute("lttd", loc.getLttd());
			request.setAttribute("range", loc.getRange());
		}else{
			request.setAttribute("lgtd", "0");
			request.setAttribute("lttd", "0");
			request.setAttribute("range", 12);
		}
		return "setting/map/show";
	}
	/**地图管理提交*/
	@RequestMapping("/map/save.do")
	public void saveMap(HttpServletResponse response,String id,String lttd,String lgtd){
		String regEx="^[0-9]+\\.{0,1}[0-9]{0,10}$";
		Pattern p=Pattern.compile(regEx);
		Matcher m1=p.matcher(lttd);
		Matcher m2=p.matcher(lgtd);
		boolean result=m1.find()&&m2.find();
		if(!result){
			AjaxMsg msg=new AjaxMsg();
			msg.setSuccess(false);
			msg.setMsg("请输入有效经纬度！");
			this.writeJson(response,msg);
			return;
		}
		this.writeJson(response, this.sysSettingService.saveMap(id, lgtd, lttd));
	}
	/**获取省份信息*/
	@RequestMapping("/map/getProvince.do")
	public void getProvince(HttpServletResponse response){
		this.writeJson(response, sysSettingService.getProvince());
	}
	/**根据省份获取相应所有城市*/
	@RequestMapping("/map/getCity.do")
	public void getCity(HttpServletResponse response,String pid){
		this.writeJson(response, sysSettingService.getCity(pid));
	}
	/**设置中心经纬度提交*/
	@RequestMapping("/map/saveCenter.do")
	public void subMapCenter(HttpServletResponse response,String lgtd,String lttd){
		this.writeJson(response, sysSettingService.subMapCenter(lgtd, lttd));
	}
	/**跳转人员管理设置 页面*/
	@RequestMapping("/manager/show")
	public String showManager(){
		return "setting/manager/show";
	}
	/**获取管理人员信息*/
	@RequestMapping("/manager/getManager.do")
	public void getManager(HttpServletResponse response,PageInfo pager){
		this.writeJson(response, sysSettingService.getManager(pager));
	}
	/**修改管理人员信息提交*/
	@RequestMapping("/manager/save.do")
	public void subManager(HttpServletResponse response,String id,ManagerModel m){
		m.setStcd(id);
		this.writeJson(response,sysSettingService.subManager(m));
	}
	/**跳转账户管理*/
	@RequestMapping("/usermanager")
	public String showUserManager(){
		return "setting/userManager";
	}
	/**获取所有登陆用户，除了当前用户*/
	@RequestMapping("/getUsers.do")
	public void getUsers(HttpServletRequest request,HttpServletResponse response,PageInfo pager){
		this.writeJson(response, sysSettingService.getUsers(request,pager));
	}
	/**删除用户信息*/
	@RequestMapping("/delUser.do")
	public void delUser(HttpServletResponse response,String id){
		this.writeJson(response, sysSettingService.delUser(id));
	}
	/**修改其它用户密码提交*/
	@RequestMapping("/userManager/save.do")
	public void resetPass(HttpServletResponse response,String id,String repassword,String authority){
		this.writeJson(response, sysSettingService.resetPass(id, repassword,"是".equals(authority)?1:0));
	}
}
