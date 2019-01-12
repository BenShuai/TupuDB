package com.it.common.uitl;

public enum ErrorCode {
	
	
	SUCCESS("成功", 200), ERROR("失败", 500),ERROR_PARAM("参数不正确", 401), ERROR_REQUEST("请求失败", 400), ERROR_NOT_LOGIN("未登录", 510), ERROR_PRIVILE_NO("权限不足", 511),
    BLACK_LIST("黑名单", 1010), LOAN_CONFUSE("系统拒绝中", 1014);
    // 成员变量  
    private String msg;  
    private int status;  
    // 构造方法  
    private ErrorCode(String msg, int status) {  
        this.msg = msg;  
        this.status = status;  
    }  
    // 普通方法  
    public static String getName(int status) {  
        for (ErrorCode e : ErrorCode.values()) {  
            if (e.getStatus() == status) {  
                return e.msg;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getMsg() {  
        return msg;  
    }  
    public void setMsg(String msg) {  
        this.msg = msg;  
    }  
    public int getStatus() {  
        return status;  
    }  
    public void setStatus(int status) {  
        this.status = status;  
    } 

	
	
}
