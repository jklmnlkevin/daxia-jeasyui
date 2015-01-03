package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;


/**
 * ApiTest
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "apitest") // 指定与数据库映射的表名
public class ApiTest extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * api名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 描述
	 */
	@Column(name = "description")
    private String description;
	/**
	 * url
	 */
	@Column(name = "url")
    private String url;
	
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
	 * 获取值：api名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：api名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：描述
	 */
	public String getDescription() {
    	return description;
    }
	
	/** 
	 * 设置值：描述
	 */    
    public void setDescription(String description) {
    	this.description = description;
    }
	
	/** 
	 * 获取值：url
	 */
	public String getUrl() {
    	return url;
    }
	
	/** 
	 * 设置值：url
	 */    
    public void setUrl(String url) {
    	this.url = url;
    }
	
}
