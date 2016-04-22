package com.nuaa.service.stu.inter;

import com.nuaa.domain.stu.StuInfo;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.utils.AjaxMsg;

public interface StuInfoServiceInter {
	/**根据学号获取学员信息*/
	public FStuInfo getStuInfo(String stuno);
	/**学员信息修改提交*/
	public AjaxMsg personInfoSub(FStuInfo stuInfo);
	/**学员信息修改*/
	public AjaxMsg modStuInfo(StuInfo stuInfo);
}
