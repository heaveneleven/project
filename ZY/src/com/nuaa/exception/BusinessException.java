package com.nuaa.exception;
/***
 * 自定义业务异常
 * @author heaven
 */
public class BusinessException extends RuntimeException{
	private static  final long serialVersionUID = 7687159893577342188L;
	private String code;
	public BusinessException(){
		super();
	}
	public BusinessException(String msg,Throwable cause){
		super(msg,cause);
	}
	public BusinessException(String msg){
		super(msg);
	}
	public BusinessException(Throwable cause){
		super(cause);
	}
	public BusinessException(String code,String msg){
		super(msg);
		this.code=code;
	}
	public BusinessException(String code,String msg,Throwable cause){
		super(msg,cause);
		this.code=code;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code=code;
	}
}
