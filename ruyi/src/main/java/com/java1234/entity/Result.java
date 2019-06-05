package com.java1234.entity;

public class Result {

	//{"success":false,"errorcode":-1,"msg":"发送失败!"}
	
	private boolean success;
	private  Integer errorcode ; 
	private String msg ;
	
	
	public Result() {
		super();
	}
	
	
	public Integer getErrorcode() {
		return errorcode;
	}


	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}


	public Result(boolean success, int errorcode, String msg) {
		super();
		this.success = success;
		this.errorcode = errorcode;
		this.msg = msg;
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
	
	
	
}
