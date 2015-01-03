package com.daxia.core.common;

/**
 * 预定桌位状态： 1、取消   2、成功   3、待处理
 * */
public enum BookTableStatus {
	Pending(2, "待处理"),
	Success(1, "成功"),
	Cancel(0, "取消");
	
	
	private int value;
	private String remark;
	
	private BookTableStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public BookTableStatus getByValue(int value) {
		for(BookTableStatus ots : BookTableStatus.values()) {
			if(ots.getValue() == value) {
				return ots;
			}
		}
		return null;
	}
	
}
