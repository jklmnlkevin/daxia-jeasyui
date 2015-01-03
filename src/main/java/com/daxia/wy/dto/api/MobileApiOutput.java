package com.daxia.wy.dto.api;

import java.io.Serializable;

public class MobileApiOutput implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String status = "0";
    private Object data;
    private String error;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    
}
