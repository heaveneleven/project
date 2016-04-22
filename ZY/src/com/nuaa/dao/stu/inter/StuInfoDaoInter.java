package com.nuaa.dao.stu.inter;

import com.nuaa.dao.base.inter.BaseDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.utils.AjaxMsg;

public interface StuInfoDaoInter  extends BaseDaoInter<StuInfo, Integer>{
	public FStuInfo getStuInfo(String stuno);
	public AjaxMsg personInfoSub(FStuInfo stuInfo);
}
