package com.daxia.wy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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

	@Transient
	private String fullUrl;
    
	private String exampleUrl;
	private String exampleResponse;
	private String requestMethod;
	
	@ManyToOne
    @JoinColumn(name = "apimodule_id")
    private ApiModule apiModule;
	
    @OneToMany(mappedBy="apiTest",cascade={CascadeType.ALL},fetch=FetchType.EAGER)  
    private List<ApiTestParameter> apiTestParameters;
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

    public ApiModule getApiModule() {
        return apiModule;
    }

    public void setApiModule(ApiModule apiModule) {
        this.apiModule = apiModule;
    }

    public List<ApiTestParameter> getApiTestParameters() {
        return apiTestParameters;
    }

    public void setApiTestParameters(List<ApiTestParameter> apiTestParameters) {
        this.apiTestParameters = apiTestParameters;
    }
    
    public String getFullUrl() {
        List<ApiTestParameter> parameters = getApiTestParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return getUrl();
        }
        
        List<String> list = new ArrayList<String>();
        for (ApiTestParameter p : parameters) {
            list.add(p.getName() + "=");
        }
        return getUrl() + "?" + StringUtils.join(list, "&");
    }

    public String getExampleUrl() {
        return exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        this.exampleUrl = exampleUrl;
    }

    public String getExampleResponse() {
        return exampleResponse;
    }

    public void setExampleResponse(String exampleResponse) {
        this.exampleResponse = exampleResponse;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    
}
