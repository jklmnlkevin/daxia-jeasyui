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
import com.daxia.wy.common.ICommunityRelatedModel;

/**
 * Notice
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "notice") // 指定与数据库映射的表名
public class Notice extends BaseModel implements ICommunityRelatedModel {
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
	 * 内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Column(name = "createtime")
    private Date createTime = new Date();
	/**
	 * 商家
	 */
	@ManyToOne
	@JoinColumn(name = "store_id")
    private Store store;
	
	@ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
	
	private boolean isPublished;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date publishTime;
	
	/**
	 * 发布人
	 */
	private String publisher;
	/**
	 * 当前回复到了第几楼
	 */
	private Integer currentFloor;
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
	 * 获取值：商家
	 */
	public Store getStore() {
    	return store;
    }
	
	/** 
	 * 设置值：商家
	 */    
    public void setStore(Store store) {
    	this.store = store;
    }

    /**
     * @return the community
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * @param community the community to set
     */
    public void setCommunity(Community community) {
        this.community = community;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }
    
}
