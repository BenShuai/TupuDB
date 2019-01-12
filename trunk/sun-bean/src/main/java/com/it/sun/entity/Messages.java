package com.it.sun.entity;

import java.io.Serializable;

public class Messages implements Serializable {
    private static final long serialVersionUID = -4233414172825048591L;
    private String code;
    private String auth;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
