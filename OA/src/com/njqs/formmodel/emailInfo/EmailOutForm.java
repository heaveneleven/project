package com.njqs.formmodel.emailInfo;
/**收件箱Form*/
public class EmailOutForm {
	/**收件箱表id*/
	private int email_out_id;
	/**用户登录表id*/
	private int login_id;
	/**邮箱优先级*/
	private int priority;
	/**邮件是否已被阅读*/
	private int is_read;
	/**对方是否已回复*/
	private int is_reply;
	/**邮件标题*/
	private String title;
	/**发件人*/
	private String sender;
	/**收件人*/
	private String recipient;
	/**发件时间*/
	private String tm;
	public int getEmail_out_id() {
		return email_out_id;
	}
	public void setEmail_out_id(int email_out_id) {
		this.email_out_id = email_out_id;
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
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
}
