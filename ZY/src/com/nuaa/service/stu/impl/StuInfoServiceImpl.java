package com.nuaa.service.stu.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuaa.dao.stu.inter.StuInfoDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.service.stu.inter.StuInfoServiceInter;
import com.nuaa.utils.AjaxMsg;
@Service("stuInfoService")
public class StuInfoServiceImpl implements StuInfoServiceInter{
	@Resource(name="stuInfoDao")
	private StuInfoDaoInter stuInfoDao;

	@Override
	public FStuInfo getStuInfo(String stuno) {
		return stuInfoDao.getStuInfo(stuno);
	}
	/**学员信息修改提交*/
	@Override
	public AjaxMsg personInfoSub(FStuInfo stuInfo) {
		return stuInfoDao.personInfoSub(stuInfo);
	}
	@Override
	public AjaxMsg modStuInfo(StuInfo stuInfo) {
		return null;
	}
}
