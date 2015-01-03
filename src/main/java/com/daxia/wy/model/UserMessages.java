package com.daxia.wy.model;

import java.util.Date;

/**
 * UserMessages entity. @author MyEclipse Persistence Tools
 */

public class UserMessages implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private String fromUsername;
	private String toUsername;
	private Date createTime;
	private String msgType;
	private String msgId;
	private String content;

	// Constructors

	/** default constructor */
	public UserMessages() {
	}

	/** full constructor */
	public UserMessages(String fromUsername, String toUsername,
			Date createTime, String msgType, String msgId, String content) {
		this.fromUsername = fromUsername;
		this.toUsername = toUsername;
		this.createTime = createTime;
		this.msgType = msgType;
		this.msgId = msgId;
		this.content = content;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getFromUsername() {
		return this.fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getToUsername() {
		return this.toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}