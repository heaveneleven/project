package com.nuaa.dao.stu.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nuaa.dao.base.impl.BaseDaoImpl;
import com.nuaa.dao.stu.inter.StuInfoDaoInter;
import com.nuaa.domain.stu.StuInfo;
import com.nuaa.formmodel.stu.FStuInfo;
import com.nuaa.utils.AjaxMsg;
@Repository("stuInfoDao")
public class StuInfoDaoImpl extends BaseDaoImpl<StuInfo, Integer> implements StuInfoDaoInter{
	/**根据学号获取学员信息*/
	@Override
	public FStuInfo getStuInfo(String stuno) {
		String sql = "select * from stu_info where stuno = '"+stuno+"' ";
		List<Map<String,Object>> list = this.searchForMap(sql);
		Map<String,Object> obj = list.get(0);
		FStuInfo stuInfo = new FStuInfo();
		stuInfo.setStuname(obj.get("stuname").toString());
		stuInfo.setStuno(obj.get("stuno").toString());
		stuInfo.setSex(obj.get("sex").toString().equals("0")?"女":"男");
		stuInfo.setBirth(obj.get("birth").toString().split(" ")[0]);
		stuInfo.setType(obj.get("type").toString().equals("0")?"士兵生":"青年生");
		stuInfo.setClassification(obj.get("classification").toString().equals("0")?"文科":"理科");
		stuInfo.setTel(obj.get("tel")==null?" ":obj.get("tel").toString());
		stuInfo.setEmail(obj.get("email")==null?" ":obj.get("email").toString());
		return stuInfo;
	}
	/**学员信息修改提交，注意：只能修改email和tel，其余应该由管理员来赋予*/
	@Override
	@Transactional//添加事务
	public AjaxMsg personInfoSub(FStuInfo stuInfo) {
		String stuno = stuInfo.getStuno();
		String email = stuInfo.getEmail();
		String tel = stuInfo.getTel();
		String sql = "update stu_info set email = '"+email+"' , tel = '"+tel+"' where stuno ='"+stuno+"' ";
		System.out.println(sql);
		AjaxMsg msg = new AjaxMsg();
		try{
			 this.update(sql);
			 msg.setSuccess(true);
			 msg.setMsg("修改成功！");
			}catch(Exception e){
				msg.setSuccess(false);
				msg.setMsg("修改失败！");
			}
		    return msg;
	}
}
