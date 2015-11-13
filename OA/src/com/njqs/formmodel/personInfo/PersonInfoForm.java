package com.njqs.formmodel.personInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.njqs.domain.user.UserInfo;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class PersonInfoForm {
	/**用户真实名*/
	private String user_name;
	/**性别*/
	private int user_sex;
	/**年龄/生日*/
	private String user_birth; 
	/**邮箱地址*/
	private String user_email;
	/**家庭电话号码*/
	private String user_tel;
	/**移动手机号码*/
	private String user_mobile;
	/**职务*/
	private String post;
	/**所属部门*/
	private String depart;
	/**地址*/
	private String user_address;
	public PersonInfoForm(){}
	/**构造函数，将实体类填充到form模型中*/
	public PersonInfoForm(UserInfo user) {
		this.setUser_name(user.getUser_name());
		this.setUser_sex(user.getUser_sex());
		try {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 if(user.getUser_birth()==null){
				 this.setUser_birth("");
		     }else{
		    	 this.setUser_birth(format.format(user.getUser_birth()));
			 }
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
	
		this.setUser_address(user.getUser_address());
		this.setUser_email(user.getUser_email());
		this.setUser_tel(user.getUser_tel());
		this.setUser_mobile(user.getUser_mobile());
		this.setPost(user.getPost());
		this.setDepart(user.getDepart());
	}
	/***根据form对象获取用户信息实体类一个对象*/
	public UserInfo getUserInfoEntity(PersonInfoForm form){
		UserInfo user=new UserInfo();
		return null;//待续
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	} 
}
