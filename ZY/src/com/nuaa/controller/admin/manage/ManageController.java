package com.nuaa.controller.admin.manage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.controller.base.BaseController;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.service.admin.inter.AdminManageServiceInter;
import com.nuaa.service.stu.inter.StuInfoServiceInter;
import com.nuaa.utils.PageInfo;
import com.nuaa.utils.SessionKey;
import com.nuaa.utils.StuSessionInfo;
/**
 * 管理员对学员信息成绩以及待选专业的管理页面
 * @author heaven
 *
 */
@Controller
@RequestMapping("/admin/manage")
public class ManageController extends BaseController{
	@Resource(name="stuInfoService")
	private StuInfoServiceInter stuInfoService;
	@Resource(name="manageService")
	private AdminManageServiceInter manageService;
	/**转至学生信息查看页面*/
	@RequestMapping("/stu_info")
	public String showStuInfo(int id){
		if(id==1){
			return "admin/layout/manage/stu_info";
		}else{
			return "admin/layout/manage/mark_info";
		}
	}
	/**加载学员信息*/
	@RequestMapping("/stu_info.do")
	public void getStuInfo(HttpServletResponse response,PageInfo pager){
		this.writeJson(response,manageService.getStuInfo(pager));
	}
	/**弹出添加学员信息窗口*/
	@RequestMapping("/add_stu")
	public String addStuInfo(){
		return "admin/layout/manage/stuInfo/add_stu";
	}
	@RequestMapping("/add_stu_submit.do")
	public void addStuInfoSubmit(HttpServletResponse response,FStuInfo stuInfo){
		this.writeJson(response, this.manageService.addStuInfo(stuInfo.toEntity()));
	}
	/**删除学员记录*/
	@RequestMapping("/del_stu_info.do")
	public void delStuInfo(HttpServletResponse response,String ids){
		this.writeJson(response, this.manageService.delStuInfo(ids));
	}
	/**弹出修改学员信息窗口*/
	@RequestMapping("/mod_stu")
	public String modStuInfo(HttpServletRequest request,String stuno){
		request.setAttribute("userInfo",stuInfoService.getStuInfo(stuno));
		return "admin/layout/manage/stuInfo/mod_stu";
	}
	@RequestMapping("/mod_stu_submit.do")
	public void modStuInfoSubmit(HttpServletResponse response,StuInfo stuInfo){
		this.writeJson(response, this.manageService.modStuInfo(stuInfo));
	}
	/**获取学员成绩信息**/
	@RequestMapping("/stu_mark.do")
	public void getStuMarks(){
		
	}
}
