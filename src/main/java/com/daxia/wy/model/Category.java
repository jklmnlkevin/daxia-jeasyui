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
 * Category
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "category") // 指定与数据库映射的表名
public class Category extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 类别名称
	 */
	@Column(name = "name")
    private String name;
	
	private Integer type;
	
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
	 * 获取值：类别名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：类别名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
	
}
