package com.daxia.wy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;

/**
 * TopicReply
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "topicreply") // 指定与数据库映射的表名
public class TopicReply extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 帖子
	 */
	@ManyToOne
	@JoinColumn(name = "topic_id")
    private Topic topic;
	/**
	 * 回复内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 回复用户
	 */
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	private int floor;
	/**
	 * 回复时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "replyTime")
    private Date replyTime;
	/**
	 * 是否是物业回复
	 */
	@Column(name = "isestate")
    private Boolean isEstate;
	/**
	 * 引用回复
	 */
	@ManyToOne
	@JoinColumn(name = "parent_reply_id")
    private TopicReply parentTopicReply;
	
	/** 
	 * 获取值：id
	 */
	public Long getId() {
    	return id;
    }
	
	/** 
	 * 设置值：id
	 */    
    public void setId(Long id) {
    	this.id = id;
    }
	
	/** 
	 * 获取值：帖子
	 */
	public Topic getTopic() {
    	return topic;
    }
	
	/** 
	 * 设置值：帖子
	 */    
    public void setTopic(Topic topic) {
    	this.topic = topic;
    }
	
	/** 
	 * 获取值：回复内容
	 */
	public String getContent() {
    	return content;
    }
	
	/** 
	 * 设置值：回复内容
	 */    
    public void setContent(String content) {
    	this.content = content;
    }
	
	/** 
	 * 获取值：回复用户
	 */
	public User getUser() {
    	return user;
    }
	
	/** 
	 * 设置值：回复用户
	 */    
    public void setUser(User user) {
    	this.user = user;
    }
	
	/** 
	 * 获取值：回复时间
	 */
	public Date getReplyTime() {
    	return replyTime;
    }
	
	/** 
	 * 设置值：回复时间
	 */    
    public void setReplyTime(Date replyTime) {
    	this.replyTime = replyTime;
    }
	
	

    public Boolean getIsEstate() {
        return isEstate;
    }

    public void setIsEstate(Boolean isEstate) {
        this.isEstate = isEstate;
    }

    public TopicReply getParentTopicReply() {
        return parentTopicReply;
    }

    public void setParentTopicReply(TopicReply parentTopicReply) {
        this.parentTopicReply = parentTopicReply;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
}
