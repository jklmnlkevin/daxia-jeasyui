package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.daxia.core.model.BaseModel;

import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.JoinColumn;

import com.daxia.wy.common.FeeItemTypes;
import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.model.Community;

/**
 * FeeItem
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "feeitem") // 指定与数据库映射的表名
public class FeeItem extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 缴费项目名称
	 */
	/*@Column(name = "name")
    private String name;*/
	
	/**
	 * 单价
	 */
	@Column(name = "price")
    private Double price;
	/**
	 * 单位
	 */
	@Column(name = "unit")
    private String unit;
	/**
	 * 小区
	 */
	@ManyToOne
	@JoinColumn(name = "community_id")
    private Community community;
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	
	private Integer type;
	
	@Transient
	private String name;

	public String getName() {
	    return FeeItemTypes.getByValue(getType()).getRemark();
	}

	public void setName(String name) {
	    this.name = name;
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
	 * 获取值：单价
	 */
	public Double getPrice() {
    	return price;
    }
	
	/** 
	 * 设置值：单价
	 */    
    public void setPrice(Double price) {
    	this.price = price;
    }
	
	/** 
	 * 获取值：单位
	 */
	public String getUnit() {
    	return unit;
    }
	
	/** 
	 * 设置值：单位
	 */    
    public void setUnit(String unit) {
    	this.unit = unit;
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
	 * 获取值：备注
	 */
	public String getRemark() {
    	return remark;
    }
	
	/** 
	 * 设置值：备注
	 */    
    public void setRemark(String remark) {
    	this.remark = remark;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
}
