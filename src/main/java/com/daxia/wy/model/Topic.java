package com.daxia.wy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;

/**
 * Topic
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "topic") // 指定与数据库映射的表名
public class Topic extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 小区
	 */
	@ManyToOne
	@JoinColumn(name = "community_id")
    private Community community;
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
	 * 发表时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	/**
	 * 最后回复时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "lastReplyTime")
    private Date lastReplyTime;
	/**
	 * 点击次数
	 */
	@Column(name = "clickCount")
    private Integer clickCount;
	/**
	 * 回复次数
	 */
	@Column(name = "replyCount")
    private Integer replyCount = 0;
	/**
	 * 发起人
	 */
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	/**
	 * 是否邀请物业回复
	 */
	@Column(name = "isInviteEstate")
    private Boolean isInviteEstate;
	/**
	 * 物业是否已经回复
	 */
	@Column(name = "isEstateReplied")
    private Boolean isEstateReplied;
	/**
	 * 图片列表
	 */
	@Column(name = "image")
    private String image;
	/**
	 * 当前是第几楼
	 */
	private int currentFloor;
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
	 * 获取值：小区
	 */
	public Community getCommunity() {
    	return community;
    }
	
	/** 
	 * 设置值：小区
	 */    
    public void setCommunity(Community community) {
    	this.community = community;
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
	 * 获取值：发表时间
	 */
	public Date getCreateTime() {
    	return createTime;
    }
	
	/** 
	 * 设置值：发表时间
	 */    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
	
	/** 
	 * 获取值：最后回复时间
	 */
	public Date getLastReplyTime() {
    	return lastReplyTime;
    }
	
	/** 
	 * 设置值：最后回复时间
	 */    
    public void setLastReplyTime(Date lastReplyTime) {
    	this.lastReplyTime = lastReplyTime;
    }
	
	/** 
	 * 获取值：点击次数
	 */
	public Integer getClickCount() {
    	return clickCount;
    }
	
	/** 
	 * 设置值：点击次数
	 */    
    public void setClickCount(Integer clickCount) {
    	this.clickCount = clickCount;
    }
	
	/** 
	 * 获取值：回复次数
	 */
	public Integer getReplyCount() {
    	return replyCount;
    }
	
	/** 
	 * 设置值：回复次数
	 */    
    public void setReplyCount(Integer replyCount) {
    	this.replyCount = replyCount;
    }
	
	/** 
	 * 获取值：发起人
	 */
	public User getUser() {
    	return user;
    }
	
	/** 
	 * 设置值：发起人
	 */    
    public void setUser(User user) {
    	this.user = user;
    }
	
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsInviteEstate() {
        return isInviteEstate;
    }

    public void setIsInviteEstate(Boolean isInviteEstate) {
        this.isInviteEstate = isInviteEstate;
    }

    public Boolean getIsEstateReplied() {
        return isEstateReplied;
    }

    public void setIsEstateReplied(Boolean isEstateReplied) {
        this.isEstateReplied = isEstateReplied;
    }
    public String[] getImageArr() {
        if (StringUtils.isBlank(getImage())) {
            return new String[0];
        }
        return getImage().split("\\ *,\\ *");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
}
