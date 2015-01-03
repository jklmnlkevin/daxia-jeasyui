package com.daxia.wy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;

/**
 * NoticeReply
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "noticereply") // 指定与数据库映射的表名
public class NoticeReply extends BaseModel {
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
	 * 内容
	 */
	@Column(name = "content")
    private String content;
	/**
	 * 父回复id(这条回复是回复的谁的)
	 */
	@ManyToOne
	@JoinColumn(name = "parent_noticereply_id")
    private NoticeReply parentNoticeReply;
	
	@OneToMany(mappedBy = "parentNoticeReply")
    @OrderBy(value  = "createTime ASC, id ASC")
    private List<NoticeReply> childReplies;
	
	/**
	 * 回复时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	
	@ManyToOne
	@JoinColumn(name = "notice_id")
	private Notice notice;
	
	/**
	 * 几楼
	 */
	private Integer floor;
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
	 * 获取值：父回复id(这条回复是回复的谁的)
	 */
	public NoticeReply getParentNoticeReply() {
    	return parentNoticeReply;
    }
	
	/** 
	 * 设置值：父回复id(这条回复是回复的谁的)
	 */    
    public void setParentNoticeReply(NoticeReply parentNoticeReply) {
    	this.parentNoticeReply = parentNoticeReply;
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

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<NoticeReply> getChildReplies() {
        return childReplies;
    }

    public void setChildReplies(List<NoticeReply> childReplies) {
        this.childReplies = childReplies;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    
}
