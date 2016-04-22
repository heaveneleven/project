package com.nuaa.controller.stu.info;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.controller.base.BaseController;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.service.stu.inter.StuInfoServiceInter;
import com.nuaa.utils.SessionKey;
import com.nuaa.utils.StuSessionInfo;

/**
 * 学员信息管理部分
 * @author heaven
 *
 */
@Controller
@RequestMapping("/stu/info")
public class InfoController extends BaseController{
	@Resource(name="stuInfoService")
	private StuInfoServiceInter stuInfoService;
	@RequestMapping("/person_detail")
	public String showInfo(HttpServletRequest request,int id){
		/**显示个人信息*/
		if(id==1){
			/**从session中获取stuno*/
			StuSessionInfo sessionInfo=(StuSessionInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
			request.setAttribute("userInfo",stuInfoService.getStuInfo(sessionInfo.getStuno()));
			return "/stu/info/person_detail";
		}else{//显示成绩信息
			return "/stu/info/score";
		}
	}
	/**提交用户修改信息*/
	@RequestMapping("/person_info/submit.do")
	public void personInfoSub(HttpServletResponse response,FStuInfo stuInfo){
		this.writeJson(response, stuInfoService.personInfoSub(stuInfo));
	}
}
