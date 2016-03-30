package com.nuaa.utils;

/***
 * 学员存入session的内容
 * @author heaven
 *
 */
public class StuSessionInfo {
	private String id;
	/**学号*/
	private String stuno;
	/**学员姓名*/
	private String stuname;
	/**出生年月*/
	private String birth;
	/**性别0女1男*/
	private String sex;
	/**学员类别0士兵生1青年生*/
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
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
}
