package com.njqs.formmodel.person_log;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.njqs.domain.log.PersonLog;

/**我的日志form*/
public class PersonLogForm {
	private int log_id;
	/**用户登录id*/
	private int login_id;
	/**日志主题*/
	private String log_title;
	/**日志类型 0:个人日志 1:工作日志*/
	private int log_type;
	/**日志创建日期*/
	private String tm;
	/**日志内容*/
	private String log_content;
	public PersonLogForm(){}
	public PersonLogForm(PersonLog log){
		this.setLog_id(log.getId());
		this.setLogin_id(log.getLogin_id());
		this.setLog_title(log.getLog_title());
		this.setLog_type(log.getLog_type());
		this.setTm(DateFormatUtils.format(log.getTm(), "yyyy-MM-dd"));
		this.setLog_content(log.getLog_content());
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
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
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
}
