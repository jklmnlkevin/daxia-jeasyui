package com.daxia.core.common;

public enum MessagePushType {
	Test("00", "测试"),
	RecommendFriends("10", "好友推荐"), 
	Topic("20", "公告或话题"),
	OrderStatusChange("30", "订单状态变更"),
	Chat("40", "聊天消息"),
	ChatConfirm("45", "聊天消息确认"),
	Common("50", "一般的消息"),
	FriendRequest("60", "好友请求"),
	FriendRequestSuccess("70", "好友添加成功"),
	NewOrder("80", "新的订单");

	private String value;
	private String remark;

	private MessagePushType(String value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public String getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public MessagePushType getByValue(int value) {
		for (MessagePushType o : MessagePushType.values()) {
			if (o.getValue().equals(value)) {
				return o;
			}
		}
		return null;
	}
}
