package com.nuaa.formmodel.stu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nuaa.domain.stu.StuInfo;
import com.nuaa.utils.EcodeByMD5;

public class FStuInfo {
	/**学号*/
	private String stuno;
	/**姓名*/
	private String stuname;
	/**类型*/
	private String type;
	/**性别*/
	private String sex;
	/**文理*/
	private String classification;
	/**邮箱*/
	private String email;
	/**电话*/
	private String tel;
	/**生日*/
	private String birth;
	/**转换为Entity类*/
	public StuInfo toEntity(){
		StuInfo entity = new StuInfo();
		entity.setStuname(this.getStuname());
		entity.setStuno(this.getStuno());
		/**新添加的学员默认密码为学号*/
		entity.setPassword(EcodeByMD5.ecodeByMD5(getStuno()));
		entity.setClassification(Integer.parseInt(this.getClassification()));
		entity.setType(Integer.parseInt(this.getType()));
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(this.getBirth());
			entity.setBirth(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return entity;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
