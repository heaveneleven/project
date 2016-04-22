package com.nuaa.domain.stu;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 学员信息表
 * @author heaven
 *
 */
@Entity
@Table(name="stu_info")
public class StuInfo {
	private int id;
	/**学号*/
	private String stuno;
	/**学员姓名*/
	private String stuname;
	/**登录密码*/
	private String password;
	/**出生年月*/
	private Date birth;
	/**性别0女1男*/
	private int sex;
	/**学员类别0士兵生1青年生*/
	private int type;
	/**文理科生0文科1理科*/
	private int classification;
	/**电话*/
	private String tel;
	/**邮箱*/
	private String email;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
