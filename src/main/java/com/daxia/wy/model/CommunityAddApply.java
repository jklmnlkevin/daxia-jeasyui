package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;
import com.daxia.wy.common.CommunityAddApplyStatus;

/**
 * CommunityAddApply
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "communityaddapply") // 指定与数据库映射的表名
public class CommunityAddApply extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 小区名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 小区地址
	 */
	@Column(name = "address")
    private String address;
	/**
	 * 地区
	 */
	@ManyToOne
	@JoinColumn(name = "district_id")
    private District district;
	/**
	 * 0，新增， 1，同意，2，拒绝
	 */
	@Column(name = "status")
    private Integer status = CommunityAddApplyStatus.New.getValue();
	
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
	 * 获取值：小区名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：小区名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：小区地址
	 */
	public String getAddress() {
    	return address;
    }
	
	/** 
	 * 设置值：小区地址
	 */    
    public void setAddress(String address) {
    	this.address = address;
    }
	
	/** 
	 * 获取值：地区
	 */
	public District getDistrict() {
    	return district;
    }
	
	/** 
	 * 设置值：地区
	 */    
    public void setDistrict(District district) {
    	this.district = district;
    }
	
	/** 
	 * 获取值：0，新增， 1，同意，2，拒绝
	 */
	public Integer getStatus() {
    	return status;
    }
	
	/** 
	 * 设置值：0，新增， 1，同意，2，拒绝
	 */    
    public void setStatus(Integer status) {
    	this.status = status;
    }
	
}
