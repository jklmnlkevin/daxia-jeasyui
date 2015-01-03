package com.daxia.core.common;

public enum TopicType {
	Notice(0, "公告"), 
	Topic(1, "话题");

	private int value;
	private String remark;

	private TopicType(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int value() {
		return value;
	}

	public String remark() {
		return remark;
	}

	public TopicType getByValue(int value) {
		for (TopicType o : TopicType.values()) {
			if (o.value() == value) {
				return o;
			}
		}
		return null;
	}
}