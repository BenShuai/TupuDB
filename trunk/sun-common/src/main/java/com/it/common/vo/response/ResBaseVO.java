package com.it.common.vo.response;

import java.io.Serializable;

/**
 * 公共返回结果
 */
public class ResBaseVO  implements  Serializable{
    private static final long serialVersionUID = -8707203013791429605L;
    /**请求编号*/
    private String reqNo;
    /**订单编号*/
    private String orderNo;
    /**状态
    * 通用状态
    * 0	适用于短信快捷收款业务，调用短信确认接口，短信验证码验证失败时，订单状态置为0，验证三次失败时，订单状态置为2-交易失败。
    * 1	SUCCESS	交易成功
    * 2	FAIL	交易失败
	* 其它自定义
    * */
    private String status;
    /**失败详情*/
    private String failureDetails;
    /**错误码*/
    private String errorCode;
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFailureDetails() {
		return failureDetails;
	}
	public void setFailureDetails(String failureDetails) {
		this.failureDetails = failureDetails;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
