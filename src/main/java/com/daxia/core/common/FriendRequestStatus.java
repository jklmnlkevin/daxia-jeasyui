package com.daxia.core.common;

public enum FriendRequestStatus {
	New(0, "新请求"), 
	Accepted(1, "已同意"),
	Rejected(2, "已拒绝");

	private int value;
	private String remark;

	private FriendRequestStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public static FriendRequestStatus getByValue(int value) {
		for (FriendRequestStatus o : FriendRequestStatus.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}
