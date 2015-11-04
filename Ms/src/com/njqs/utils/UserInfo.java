package com.njqs.utils;

import com.njqs.domain.user.TUser;

/**
 * @author heaven
 *	UserInfo模型，只要登录成功，就将其保存至session中，便于系统使用
 */
public class UserInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private TUser user;
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return user.getUsername();
	}
}
