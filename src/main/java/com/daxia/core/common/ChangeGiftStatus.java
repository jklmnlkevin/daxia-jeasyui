package com.daxia.core.common;

/**
 * 积分兑换礼物状态： 0、不可用    1、可用
 * */
public enum ChangeGiftStatus {
	Available(1, "可用"),
	Unavailable(0, "不可用");

	
	private int value;
	private String remark;
	
	public int getValue() {
		return this.value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	private ChangeGiftStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}
	
	public ChangeGiftStatus getByValue(int value) {
		for(ChangeGiftStatus cgs : ChangeGiftStatus.values()) {
			if(cgs.value == value) {
				return cgs;
			}
		}
		return null;
	}
	
}
