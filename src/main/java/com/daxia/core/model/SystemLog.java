package com.daxia.core.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * systemLog
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "systemlog") // 指定与数据库映射的表名
public class SystemLog extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

    /**
     * 用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 模块
     */
    private String module;
    /**
     * 操作
     */
    private String operation;
    /**
     * 参数
     */
    private String params;
    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    /**
     * url
     */
    private String url;
    
    private String ip;
    
    /**
     * 操作是否成功
     */
    private Boolean isSuccess;
    /**
     * 如果操作没成功，记录异常信息
     */
    private String errorMessage;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * 获取值 ：用户
     */
    public User getUser() {
        return user;
    }
    /**
     * 设置值 ：用户
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * 获取值 ：模块
     */
    public String getModule() {
        return module;
    }
    /**
     * 设置值 ：模块
     */
    public void setModule(String module) {
        this.module = module;
    }
    /**
     * 获取值 ：操作
     */
    public String getOperation() {
        return operation;
    }
    /**
     * 设置值 ：操作
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
    /**
     * 获取值 ：参数
     */
    public String getParams() {
        return params;
    }
    /**
     * 设置值 ：参数
     */
    public void setParams(String params) {
        this.params = params;
    }
    /**
     * 获取值 ：时间
     */
    public Date getDate() {
        return date;
    }
    /**
     * 设置值 ：时间
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * 获取值 ：url
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置值 ：url
     */
    public void setUrl(String url) {
        this.url = url;
    }
	/**
     * @return the isSuccess
     */
    public Boolean getIsSuccess() {
    	return isSuccess;
    }
	/**
     * @param isSuccess the isSuccess to set
     */
    public void setIsSuccess(Boolean isSuccess) {
    	this.isSuccess = isSuccess;
    }
	/**
     * @return the errorMessage
     */
    public String getErrorMessage() {
    	return errorMessage;
    }
	/**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
    	this.errorMessage = errorMessage;
    }
	/**
     * @return the ip
     */
    public String getIp() {
    	return ip;
    }
	/**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
    	this.ip = ip;
    }

}
