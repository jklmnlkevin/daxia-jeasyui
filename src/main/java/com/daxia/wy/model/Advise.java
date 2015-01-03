package com.daxia.wy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;
import com.daxia.wy.common.ICommunityRelatedModel;

/**
 * Advise
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "advise") // 指定与数据库映射的表名
public class Advise extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	/**
	 * 评分
	 */
	@Column(name = "score")
    private Double score;
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
	 * 物业
	 */
	//@ManyToOne
	//@JoinColumn(name = "store_id")
	@Transient
    private Store store;
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
	 * 获取值：评分
	 */
	public Double getScore() {
    	return score;
    }
	
	/** 
	 * 设置值：评分
	 */    
    public void setScore(Double score) {
    	this.score = score;
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
	 * 获取值：物业
	 */
	public Store getStore() {
    	return store;
    }
	
	/** 
	 * 设置值：物业
	 */    
    public void setStore(Store store) {
    	this.store = store;
    }
	
}
