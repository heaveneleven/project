package com.njqs.domain.email;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**收件箱实体类**/
@Entity
@Table(name="mail_in")
public class MailIn {
	private int id;
	/**用户登录表id*/
	private int login_id;
	/**邮件优先级 0：不紧急 1：紧急 2：特别紧急*/
	private int priority;
	/**是否已阅读 0:未阅读 1:已阅读*/
	private int is_read;
	/**是否已回复 0:未回复 1:已回复*/
	private int is_reply;
	/**邮件标题*/
	private String title;
	/**发件人*/
	private String sender;
	/**发件人*/
	private String recipient;
	/**发件时间*/
	private Date tm;
	/**邮件内容*/
	private String content;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getIs_read() {
		return is_read;
	}
	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}
	public int getIs_reply() {
		return is_reply;
	}
	public void setIs_reply(int is_reply) {
		this.is_reply = is_reply;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public Date getTm() {
		return tm;
	}
	public void setTm(Date tm) {
		this.tm = tm;
	}
	@Column(columnDefinition = "text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
