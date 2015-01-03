package com.daxia.core.common;

public enum OnlineStatus {
	Offline(0, "下线"),
	Online(1, "在线"),
	Invisible(2, "隐身");
	
	private int value;
	private String remark;
	private OnlineStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
    }
	
	public int value() {
		return value;
	}
	public String remark() {
		return remark;
	}
	public static OnlineStatus getByValue(int value) {
		for (OnlineStatus o : OnlineStatus.values()) {
			if (o.value() == value) {
				return o;
			}
		}
		return null;
	}
}
