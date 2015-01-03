package com.daxia.wy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;



/**
 * article
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "article") // 指定与数据库映射的表名
public class Article extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 正文链接
     */
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    
    private String content;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * 获取值 ：标题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置值 ：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取值 ：描述
     */
    public String getDescription() {
        return description;
    }
    /**
     * 设置值 ：描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 获取值 ：图片链接
     */
    public String getPicUrl() {
        return picUrl;
    }
    /**
     * 设置值 ：图片链接
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    /**
     * 获取值 ：正文链接
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置值 ：正文链接
     */
    public void setUrl(String url) {
        this.url = url;
    }
	public Store getStore() {
    	return store;
    }
	public void setStore(Store store) {
    	this.store = store;
    }
	/**
     * @return the content
     */
    public String getContent() {
    	return content;
    }
	/**
     * @param content the content to set
     */
    public void setContent(String content) {
    	this.content = content;
    }
	
}
