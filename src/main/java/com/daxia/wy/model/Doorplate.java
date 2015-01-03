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
 * Doorplate
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "doorplate") // 指定与数据库映射的表名
public class Doorplate extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 门牌号
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 楼栋号
	 */
	@ManyToOne
	@JoinColumn(name = "building_id")
    private Building building;
	
	private Double area;
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
	 * 获取值：门牌号
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：门牌号
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：楼栋号
	 */
	public Building getBuilding() {
    	return building;
    }
	
	/** 
	 * 设置值：楼栋号
	 */    
    public void setBuilding(Building building) {
    	this.building = building;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
    
}
