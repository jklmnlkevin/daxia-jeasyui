package com.daxia.core.common;

public enum Sex {
	Male(0),
	Female(1),
	Unknow(2);
	
	private int value;
	
	private Sex(int value) {
		this.value = value;
    }
	
	public int value() {
		return value;
	}
}
