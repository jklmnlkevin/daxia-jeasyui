package com.daxia.core.common;

public enum DiscountType {
	Invalid(0, "无效"),
	Effective(1, "有效");
	
	
	private int value;
	private String remark;
	
	private DiscountType(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}
	
	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}
	
	public DiscountType getByValue(int value) {
		for (DiscountType o : DiscountType.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}
