package com.daxia.wy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;

/**
 * SystemMessage
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "systemmessage") // 指定与数据库映射的表名
public class SystemMessage extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 标题
	 */
	@Column(name = "title")
    private String title;
	/**
	 * 小区
	 */
//	@ManyToOne
//	@JoinColumn(name = "community_id")
//    private Community community;
	/**
	 * 内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	/**
	 * 推送时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "pushTime")
    private Date pushTime;
	
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
	 * 获取值：标题
	 */
	public String getTitle() {
    	return title;
    }
	
	/** 
	 * 设置值：标题
	 */    
    public void setTitle(String title) {
    	this.title = title;
    }
	
	/** 
	 * 获取值：内容
	 */
	public String getContent() {
    	return content;
    }
	
	/** 
	 * 设置值：内容
	 */    
    public void setContent(String content) {
    	this.content = content;
    }
	
	/** 
	 * 获取值：创建时间
	 */
	public Date getCreateTime() {
    	return createTime;
    }
	
	/** 
	 * 设置值：创建时间
	 */    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
	
	/** 
	 * 获取值：推送时间
	 */
	public Date getPushTime() {
    	return pushTime;
    }
	
	/** 
	 * 设置值：推送时间
	 */    
    public void setPushTime(Date pushTime) {
    	this.pushTime = pushTime;
    }
	
}
