package com.daxia.wy.model;

import java.util.Date;

/**
 * ServerMessages entity. @author MyEclipse Persistence Tools
 */

public class ServerMessages implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private String toUsername;
	private String fromUsername;
	private Date createTime;
	private String msgType;
	private String msgId;
	private String content;

	// Constructors

	/** default constructor */
	public ServerMessages() {
	}

	/** full constructor */
	public ServerMessages(String toUsername, String fromUsername,
			Date createTime, String msgType, String msgId, String content) {
		this.toUsername = toUsername;
		this.fromUsername = fromUsername;
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

	public String getToUsername() {
		return this.toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getFromUsername() {
		return this.fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
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