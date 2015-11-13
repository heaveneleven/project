package com.njqs.utils;
/**作为session中所保存的用户信息*/
public class UserSessionInfo {
	/**用户登录id*/
	private int login_id;
	/**用户信息表id*/
	private int user_info_id;
	/**用户类型表id*/
	private int user_type_id;
	/**用户登录名*/
	private String login_name;
	/**用户登录名*/
	private String user_name;
	/**用户登录类型*/
	private String user_type;
	
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public int getUser_info_id() {
		return user_info_id;
	}
	public void setUser_info_id(int user_info_id) {
		this.user_info_id = user_info_id;
	}
	public int getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
}
