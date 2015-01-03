package com.daxia.core.common;

/**
 * 礼物状态：0、不可用  1、可用
 * */
public enum GiftStatus {
	Available(1, "可用"),
	Unavailable(0, "不可用");
	
	
	private int value;
	private String remark;
	
	private GiftStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public GiftStatus getByValue(int value) {
		for(GiftStatus gs : GiftStatus.values()) {
			if(gs.getValue() == value) {
				return gs;
			}
		}
		return null;
	}
	
}
