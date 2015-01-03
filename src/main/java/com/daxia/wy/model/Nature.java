package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;


/**
 * Nature
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "nature") // 指定与数据库映射的表名
public class Nature extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 属性名称
	 */
	@Column(name = "name")
    private String name;
	
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
	 * 获取值：属性名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：属性名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
}
