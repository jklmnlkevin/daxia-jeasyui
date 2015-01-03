package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;
import com.daxia.wy.common.ICommunityRelatedModel;

/**
 * Building
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "building") // 指定与数据库映射的表名
public class Building extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 楼栋号
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 小区
	 */
	@ManyToOne
	@JoinColumn(name = "community_id")
    private Community community;
	
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
	 * 获取值：楼栋号
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：楼栋号
	 */    
    public void setName(String name) {
    	this.name = name;
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
	
}
