package com.it.sun.vo;

import java.io.Serializable;

public class ApiResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6092171586191072000L;
	
	

	private int flag = 500;
	
	//private List<Object> list;
	
	private T data;
	
	
	private String msg;

	public ApiResult() {
	}

	public ApiResult(boolean b, String msg) {
		this.msg =msg;
		if (b) {
			this.flag =200;
		}else{
			this.flag =500;
		}
	}

	public ApiResult(boolean b) {
		this.msg =msg;
		if (b) {
			this.flag =200;
		}else{
			this.flag =500;
		}
	}

	public ApiResult(int flag, String msg, T data) {
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setErrorMsg(int flag, String msg) {
		this.flag=flag;
		this.msg = msg;

	}
	
	

}
