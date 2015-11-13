package com.njqs.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class EcodeByMD5 {
	/**通过MD5对用户密码进行加密*/
	public static String ecodeByMD5(String password) {
		try {
			/**确定使用MD5进行加密*/
			MessageDigest md5=MessageDigest.getInstance("MD5");
			BASE64Encoder base64en=new BASE64Encoder();
			/**产生加密后的字符串（密码）*/
			String newPassword=base64en.encode(md5.digest(password.getBytes("utf-8")));
			return newPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
