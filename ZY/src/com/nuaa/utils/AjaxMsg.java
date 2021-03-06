package com.nuaa.utils;

public class AjaxMsg {
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 提示信息 
	 */
	private String msg;
	/**
	 * 其他信息
	 */
	private Object result;
	
	public AjaxMsg(){
		
	}
	public AjaxMsg(boolean success,String msg){
		this.success=success;
		this.msg=msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
