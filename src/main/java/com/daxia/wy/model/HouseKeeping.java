package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.daxia.core.model.BaseModel;

import javax.persistence.ManyToOne;

import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.common.IUserRelatedModel;
import com.daxia.wy.model.ServiceType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.JoinColumn;
import com.daxia.core.model.User;
import com.daxia.wy.model.Community;

/**
 * HouseKeeping
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "housekeeping") // 指定与数据库映射的表名
public class HouseKeeping extends BaseModel implements ICommunityRelatedModel, IUserRelatedModel {
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
	 * 年龄
	 */
	@Column(name = "age")
    private Integer age;
	/**
	 * 价格
	 */
	@Column(name = "price")
    private String price;
	/**
	 * 服务类型
	 */
	@ManyToOne
	@JoinColumn(name = "servicetype_id")
    private ServiceType serviceType;
	/**
	 * 供需类型
	 */
	@Column(name = "type")
    private Integer type;
	/**
	 * 创建时间
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
	 * 获取值：年龄
	 */
	public Integer getAge() {
    	return age;
    }
	
	/** 
	 * 设置值：年龄
	 */    
    public void setAge(Integer age) {
    	this.age = age;
    }
	
	/** 
	 * 获取值：价格
	 */
	public String getPrice() {
    	return price;
    }
	
	/** 
	 * 设置值：价格
	 */    
    public void setPrice(String price) {
    	this.price = price;
    }
	
	/** 
	 * 获取值：服务类型
	 */
	public ServiceType getServiceType() {
    	return serviceType;
    }
	
	/** 
	 * 设置值：服务类型
	 */    
    public void setServiceType(ServiceType serviceType) {
    	this.serviceType = serviceType;
    }
	
	/** 
	 * 获取值：供需类型
	 */
	public Integer getType() {
    	return type;
    }
	
	/** 
	 * 设置值：供需类型
	 */    
    public void setType(Integer type) {
    	this.type = type;
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
	
}
