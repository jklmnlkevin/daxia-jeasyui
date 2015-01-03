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
 * Convenience
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "convenience") // 指定与数据库映射的表名
public class Convenience extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 服务名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 办事流程
	 */
	@Column(name = "flow")
    private String flow;
	/**
	 * 办公地址
	 */
	@Column(name = "address")
    private String address;
	/**
	 * 办公电话
	 */
	@Column(name = "phone")
    private String phone;
	/**
	 * 照片
	 */
	@Column(name = "image")
    private String image;
	/**
	 * 联系人
	 */
	@Column(name = "contacter")
    private String contacter;
	
	private Integer type;
	private Integer subType;
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
	 * 获取值：服务名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：服务名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：办事流程
	 */
	public String getFlow() {
    	return flow;
    }
	
	/** 
	 * 设置值：办事流程
	 */    
    public void setFlow(String flow) {
    	this.flow = flow;
    }
	
	/** 
	 * 获取值：办公地址
	 */
	public String getAddress() {
    	return address;
    }
	
	/** 
	 * 设置值：办公地址
	 */    
    public void setAddress(String address) {
    	this.address = address;
    }
	
	/** 
	 * 获取值：办公电话
	 */
	public String getPhone() {
    	return phone;
    }
	
	/** 
	 * 设置值：办公电话
	 */    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
	
	/** 
	 * 获取值：照片
	 */
	public String getImage() {
    	return image;
    }
	
	/** 
	 * 设置值：照片
	 */    
    public void setImage(String image) {
    	this.image = image;
    }
	
	/** 
	 * 获取值：联系人
	 */
	public String getContacter() {
    	return contacter;
    }
	
	/** 
	 * 设置值：联系人
	 */    
    public void setContacter(String contacter) {
    	this.contacter = contacter;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }
    
}
