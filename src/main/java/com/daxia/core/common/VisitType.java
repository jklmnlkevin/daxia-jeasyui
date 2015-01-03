package com.daxia.core.common;

public enum VisitType {
	Login(0),
	Visit(1);
	
	private int value;
	private VisitType(int value) {
		this.value = value;
    }
	
	public int value() {
		return value;
	}
}
