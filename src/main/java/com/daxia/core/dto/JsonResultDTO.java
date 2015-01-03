package com.daxia.core.dto;

import com.daxia.core.common.CallbackType;

public class JsonResultDTO {
	
	private Integer statusCode;
	private String message;
	private CallbackType callbackType;
	private String navTabId;
	private String forwardUrl;
	
	public String getMessage() {
    	return message;
    }
	public void setMessage(String message) {
    	this.message = message;
    }
	public CallbackType getCallbackType() {
    	return callbackType;
    }
	public void setCallbackType(CallbackType callbackType) {
    	this.callbackType = callbackType;
    }
	public Integer getStatusCode() {
    	return statusCode;
    }
	public void setStatusCode(Integer statusCode) {
    	this.statusCode = statusCode;
    }
	
	/**
     * @return the navTabId
     */
    public String getNavTabId() {
    	return navTabId;
    }
	/**
     * @param navTabId the navTabId to set
     */
    public void setNavTabId(String navTabId) {
    	this.navTabId = navTabId;
    }
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
}
