package com.njqs.domain.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author heaven
 *	我的日志实体类
 */
@Entity
@Table(name="person_log")
public class PersonLog {
	private int id;
	/**用户登录id*/
	private int login_id;
	/**日志主题*/
	private String log_title;
	/**日志类型 0:个人日志 1:工作日志*/
	private int log_type;
	/**日志创建日期*/
	private Date tm;
	/**日志内容*/
	private String log_content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public String getLog_title() {
		return log_title;
	}
	public void setLog_title(String log_title) {
		this.log_title = log_title;
	}
	public int getLog_type() {
		return log_type;
	}
	public void setLog_type(int log_type) {
		this.log_type = log_type;
	}
	public Date getTm() {
		return tm;
	}
	public void setTm(Date tm) {
		this.tm = tm;
	}
	@Column(columnDefinition = "text")
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
}
