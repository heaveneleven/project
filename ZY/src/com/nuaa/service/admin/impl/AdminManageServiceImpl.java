package com.nuaa.service.admin.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nuaa.dao.stu.inter.StuInfoDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.exception.BusinessException;
import com.nuaa.service.admin.inter.AdminManageServiceInter;
import com.nuaa.utils.AjaxMsg;
import com.nuaa.utils.DataGrid;
import com.nuaa.utils.PageInfo;
@Service("manageService")
@Transactional
public class AdminManageServiceImpl implements AdminManageServiceInter{
	@Resource(name="stuInfoDao")
	private StuInfoDaoInter stuInfoDao;
	/**获取所有学员的表格信息*/
	@Override
	public DataGrid<Map<String, Object>> getStuInfo(PageInfo pager) {
		StringBuilder sql=new StringBuilder();
		sql.append("select * from stu_info order by id asc ");
	
		try{
			long total=stuInfoDao.count(sql.toString());
			int []fm=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list = stuInfoDao.searchForMap(sql.toString(),fm[0],fm[1]," id asc ");
			return new DataGrid<Map<String, Object>>(total, list);
		}catch(Exception e){
			throw new BusinessException("获取学员信息出错！",e);
		}
	}
	
	/**添加学员信息*/
	@Override
	public AjaxMsg addStuInfo(StuInfo stuInfo) {
		AjaxMsg msg = new AjaxMsg();
		/**首先判断该学员学号是否已经存在*/
		String sql = "select * from stu_info where stuno = '"+stuInfo.getStuno()+"'";
		if(!this.stuInfoDao.searchForMap(sql).isEmpty()){
			msg.setSuccess(false);
			msg.setMsg("该学员学号已存在，请重新指定！");
			return msg;
		}
		try{
			this.stuInfoDao.save(stuInfo);
			msg.setSuccess(true);
			msg.setMsg("添加成功！");
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("添加失败！");
			throw new BusinessException("添加失败！",e);
		}
		return msg;
	}
	/**根据id批量删除学员信息*/
	@Override
	public AjaxMsg delStuInfo(String ids) {
		AjaxMsg msg = new AjaxMsg();
		String [] id = ids.split(",");
		try{
			for (String str : id) {
				stuInfoDao.delete(StuInfo.class, Integer.parseInt(str));
			}
				msg.setSuccess(true);
				msg.setMsg("删除成功！");
			}catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("删除失败！");
			}
			return msg;
	}
	/**提交修改*/
	@Override
	public AjaxMsg modStuInfo(StuInfo stuInfo) {
		AjaxMsg msg = new AjaxMsg();
		StringBuffer sql = new StringBuffer();
		sql.append("update stu_info set stuname = '");
		sql.append(stuInfo.getStuname());
		sql.append("',sex = '");
		sql.append(stuInfo.getSex());
		sql.append("',classification = '");
		sql.append(stuInfo.getClassification());
		sql.append("',type = '");
		sql.append(stuInfo.getType());
		sql.append("' where stuno = '");
		sql.append(stuInfo.getStuno());
		sql.append("' ");
		try{
			stuInfoDao.update(sql.toString());
			msg.setSuccess(true);
			msg.setMsg("修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("修改失败！");
		}
		return msg;
	}
	/**获取学员成绩信息*/
	@Override
	public DataGrid<Map<String, Object>> getStuMarks(PageInfo pager) {
		StringBuffer sql = new StringBuffer();
		sql.append("select r.id,r.stuno,r.stuname,r.type,r.classification,r.mark from ");
		sql.append(" (select s.id,s.stuno,s.stuname,s.classification,s.type,t.mark from stu_info as s left join stu_mark as t on s.stuno=t.stuno ) as r ");
		sql.append(" order by mark desc  ");
		sql.append("");
		sql.append("");
		sql.append("");
		return null;
	}
}
