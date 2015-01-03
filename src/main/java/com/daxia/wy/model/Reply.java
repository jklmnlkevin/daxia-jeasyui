package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.daxia.core.model.BaseModel;

import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import com.daxia.wy.model.Reply;
import javax.persistence.JoinColumn;
import java.util.Date;
import com.daxia.core.model.User;

/**
 * Reply
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "reply") // 指定与数据库映射的表名
public class Reply extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 关联id
	 */
	@Column(name = "refrenceId")
    private Long refrenceId;
	/**
	 * 回复内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 回复用户
	 */
	@Column(name = "user_id")
    private User user;
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
    private Byte isEstate;
	/**
	 * 引用回复
	 */
	@ManyToOne
	@JoinColumn(name = "parent_reply_id")
    private Reply parentReply;
	/**
	 * 第几楼
	 */
	@Column(name = "floor")
    private Integer floor;
	/**
	 * 关联类型
	 */
	@Column(name = "type")
    private Integer type;
	
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
	 * 获取值：关联id
	 */
	public Long getRefrenceId() {
    	return refrenceId;
    }
	
	/** 
	 * 设置值：关联id
	 */    
    public void setRefrenceId(Long refrenceId) {
    	this.refrenceId = refrenceId;
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
	
	/** 
	 * 获取值：是否是物业回复
	 */
	public Byte getIsEstate() {
    	return isEstate;
    }
	
	/** 
	 * 设置值：是否是物业回复
	 */    
    public void setIsEstate(Byte isEstate) {
    	this.isEstate = isEstate;
    }
	
	/** 
	 * 获取值：引用回复
	 */
	public Reply getParentReply() {
    	return parentReply;
    }
	
	/** 
	 * 设置值：引用回复
	 */    
    public void setParentReply(Reply parentReply) {
    	this.parentReply = parentReply;
    }
	
	/** 
	 * 获取值：第几楼
	 */
	public Integer getFloor() {
    	return floor;
    }
	
	/** 
	 * 设置值：第几楼
	 */    
    public void setFloor(Integer floor) {
    	this.floor = floor;
    }
	
	/** 
	 * 获取值：关联类型
	 */
	public Integer getType() {
    	return type;
    }
	
	/** 
	 * 设置值：关联类型
	 */    
    public void setType(Integer type) {
    	this.type = type;
    }
	
}
