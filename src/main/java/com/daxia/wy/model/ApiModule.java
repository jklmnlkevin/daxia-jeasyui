package com.daxia.wy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.daxia.core.model.BaseModel;


/**
 * ApiModule
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "apimodule") // 指定与数据库映射的表名
public class ApiModule extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 名称
	 */
	@Column(name = "name")
    private String name;
	
	@OneToMany(mappedBy="apiModule",cascade={CascadeType.ALL},fetch=FetchType.LAZY)  
    private List<ApiTest> apiTests;
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
	 * 获取值：名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }

    public List<ApiTest> getApiTests() {
        return apiTests;
    }

    public void setApiTests(List<ApiTest> apiTests) {
        this.apiTests = apiTests;
    }
    
}
