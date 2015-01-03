package com.daxia.core.common;

public enum SystemConfigType {
    ApiDocPath("apiDoc.path"),
    XmppHost("xmppHost"),
    XmppPort("xmppPort"),
    PushServerHttpPort("pushServerHttpPort"),
	ImagePath("image.path"); 
	

	private String value;

	private SystemConfigType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
