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
import com.daxia.wy.common.IUserRelatedModel;
import com.daxia.wy.common.QuestionStatus;

/**
 * Question
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "question") // 指定与数据库映射的表名
public class Question extends BaseModel implements IUserRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 提问者
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	/**
	 * 问题标题
	 */
	@Column(name = "title")
    private String title;
	/**
	 * 问题内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 提问时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	/**
	 * 回复状态
	 */
	@Column(name = "status")
    private Integer status = QuestionStatus.NotAnswered.getValue();
	
	private String reply;
	
	@ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
	
	public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

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
	 * 获取值：提问者
	 */
	public User getUser() {
    	return user;
    }
	
	/** 
	 * 设置值：提问者
	 */    
    public void setUser(User user) {
    	this.user = user;
    }
	
	/** 
	 * 获取值：问题标题
	 */
	public String getTitle() {
    	return title;
    }
	
	/** 
	 * 设置值：问题标题
	 */    
    public void setTitle(String title) {
    	this.title = title;
    }
	
	/** 
	 * 获取值：问题内容
	 */
	public String getContent() {
    	return content;
    }
	
	/** 
	 * 设置值：问题内容
	 */    
    public void setContent(String content) {
    	this.content = content;
    }
	
	
	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
	 * 获取值：回复状态
	 */
	public Integer getStatus() {
    	return status;
    }
	
	/** 
	 * 设置值：回复状态
	 */    
    public void setStatus(Integer status) {
    	this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
    
}
