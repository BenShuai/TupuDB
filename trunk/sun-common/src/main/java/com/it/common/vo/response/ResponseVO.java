package com.it.common.vo.response;

import com.it.common.uitl.ErrorCode;

/**
 * 前端返回数据类
 *
 */
public class ResponseVO {

	private int code;
	private String message;
	private Object body;

	public static ResponseVO ok() {
		ResponseVO response = new ResponseVO();
		response.setCode(ErrorCode.SUCCESS.getStatus());
		response.setMessage("成功");
		return response;
	}

	public static ResponseVO ok(String message) {
		ResponseVO response = new ResponseVO();
		response.setCode(ErrorCode.SUCCESS.getStatus());
		response.setMessage(message);
		return response;
	}

	public static ResponseVO error(int code, String message) {
		ResponseVO response = new ResponseVO();
		response.setCode(code);
		response.setMessage(message);
		return response;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
