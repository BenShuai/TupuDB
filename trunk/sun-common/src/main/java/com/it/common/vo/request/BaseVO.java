package com.it.common.vo.request;


import java.io.Serializable;

public class BaseVO implements Serializable {
	
	/** 请求编号   kft20130111142400*/
	private String reqNo ;
	/** 接口名称  gbp_collect_from_bank_account */
	private String service="https://merchant.kftpay.com.cn:8443/gateway/nonbatch";
	/** 接口版本号 xingguo */
	private String version;
	/** 参数字符集 xingguo*/
	private String charset ="utf-8";
	/** 语言 zh_CN" */
	private String language = "zh_CN";
	/** 参数签名算法  固定值：RSA*/
	private String signatureAlgorithm ="RSA";
	/** 签名值 */
	private String signatureInfo;
	/** 调用端IP*/
	private String callerIp;
	
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSignatureAlgorithm() {
		return signatureAlgorithm;
	}
	public void setSignatureAlgorithm(String signatureAlgorithm) {
		this.signatureAlgorithm = signatureAlgorithm;
	}
	public String getSignatureInfo() {
		return signatureInfo;
	}
	public void setSignatureInfo(String signatureInfo) {
		this.signatureInfo = signatureInfo;
	}
	public String getCallerIp() {
		return callerIp;
	}
	public void setCallerIp(String callerIp) {
		this.callerIp = callerIp;
	}
    
}
