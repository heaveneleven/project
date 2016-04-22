package com.nuaa.utils;

/***
 * 管理员存入session的内容
 * @author heaven
 *
 */
public class AdminSessionInfo {
	private String id;
	/**工号*/
	private String userno;
	/**学员姓名*/
	private String username;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
