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
 * RepairReply
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "repairreply") // 指定与数据库映射的表名
public class RepairReply extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 维修申请
	 */
	@ManyToOne
	@JoinColumn(name = "repair_id")
    private Repair repair;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	/**
	 * 回复内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 回复时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	
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
	 * 获取值：维修申请
	 */
	public Repair getRepair() {
    	return repair;
    }
	
	/** 
	 * 设置值：维修申请
	 */    
    public void setRepair(Repair repair) {
    	this.repair = repair;
    }
	
	/** 
	 * 获取值：用户
	 */
	public User getUser() {
    	return user;
    }
	
	/** 
	 * 设置值：用户
	 */    
    public void setUser(User user) {
    	this.user = user;
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
	 * 获取值：回复时间
	 */
	public Date getCreateTime() {
    	return createTime;
    }
	
	/** 
	 * 设置值：回复时间
	 */    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
	
}
