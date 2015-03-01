package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;

/**
 * ApiTestParameter
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "apitestparameter") // 指定与数据库映射的表名
public class ApiTestParameter extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * api
	 */
	@ManyToOne
	@JoinColumn(name = "apitest_id")
    private ApiTest apiTest;
	/**
	 * 参数名
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 参数描述
	 */
	@Column(name = "description")
    private String description;
	/**
	 * 是否必须
	 */
	@Column(name = "isrequired")
    private boolean required;
	
	private String defaultValue;
	
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
	 * 获取值：api
	 */
	public ApiTest getApiTest() {
    	return apiTest;
    }
	
	/** 
	 * 设置值：api
	 */    
    public void setApiTest(ApiTest apiTest) {
    	this.apiTest = apiTest;
    }
	
	/** 
	 * 获取值：参数名
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：参数名
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：参数描述
	 */
	public String getDescription() {
    	return description;
    }
	
	/** 
	 * 设置值：参数描述
	 */    
    public void setDescription(String description) {
    	this.description = description;
    }
	
	/** 
	 * 获取值：是否必须
	 */
	public boolean getRequired() {
    	return required;
    }
	
	/** 
	 * 设置值：是否必须
	 */    
    public void setRequired(boolean required) {
    	this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
}
