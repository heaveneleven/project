package com.njqs.service.desktop.person_info.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.njqs.dao.desktop.person_info.inter.PersonInfoDaoInter;
import com.njqs.domain.user.UserInfo;
import com.njqs.formmodel.personInfo.PersonInfoForm;
import com.njqs.service.desktop.person_info.inter.PersonInfoServiceInter;
import com.njqs.utils.AjaxMsg;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**用户个人资料Service*/
@Transactional
@Service("personInfoService")
public class PersonInfoServiceImpl implements PersonInfoServiceInter{
	@Resource(name="personInfoDao")
	private PersonInfoDaoInter personInfoDao;
	/**根据id获取信息表记录，再转为from模型类*/
	@Override
	public PersonInfoForm getPersonInfoForm(int id){
		UserInfo tuser=personInfoDao.find(UserInfo.class, id);
		if(tuser==null)
			return null;
		PersonInfoForm fuser=new PersonInfoForm(tuser);
		return fuser;
	}
	/**保存保存用户信息*/
	@Override
	public AjaxMsg savePersonForm(PersonInfoForm form,int id) {
			AjaxMsg msg=new AjaxMsg();
		try{
			UserInfo tuser=personInfoDao.find(UserInfo.class, id);
			tuser.setUser_name(form.getUser_name());
			tuser.setUser_sex(form.getUser_sex());
			try {
				 Date date;
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 if(form.getUser_birth()==null){
					date=null; 
				 }else{
					date = format.parse(form.getUser_birth());
				 }
			     
					tuser.setUser_birth(date);
			   } catch (ParseException e) {
			    e.printStackTrace();
			   }
			tuser.setUser_email(form.getUser_email());
			tuser.setUser_tel(form.getUser_tel());
			tuser.setUser_mobile(form.getUser_mobile());
			tuser.setPost(form.getPost());
			tuser.setDepart(form.getDepart());
			tuser.setUser_address(form.getUser_address());
			this.personInfoDao.update(tuser);
			msg.setSuccess(true);
			msg.setMsg("保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg(e.toString());
			}
			return msg;
	}
}
