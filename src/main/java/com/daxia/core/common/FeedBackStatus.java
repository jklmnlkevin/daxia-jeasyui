package com.daxia.core.common;

public enum FeedBackStatus {
	UnRead(0,"未读"),
	Read(1,"已读");
	
	private int value;
	private String remark;
	
	private FeedBackStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public FeedBackStatus getByValue(int value) {
		for(FeedBackStatus fb : FeedBackStatus.values()) {
			if(fb.value == value) {
				return fb;
			}
		}
		return null;
	}
}