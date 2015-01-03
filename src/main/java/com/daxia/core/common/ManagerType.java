package com.daxia.core.common;

public enum ManagerType {
	Head(0, "总店"), 
	Branch(1, "分店");

	private int value;
	private String remark;

	private ManagerType(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public ManagerType getByValue(int value) {
		for (ManagerType o : ManagerType.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}