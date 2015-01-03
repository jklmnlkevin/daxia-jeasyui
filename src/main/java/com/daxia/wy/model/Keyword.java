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
 * Keyword
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "keyword") // 指定与数据库映射的表名
public class Keyword extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 关键词，以逗号分隔
	 */
	@Column(name = "keywords")
    private String keywords;
	/**
	 * 文章
	 */
	@ManyToOne
	@JoinColumn(name = "article_id")
    private Article article;
	/**
	 * 商家
	 */
	@ManyToOne
	@JoinColumn(name = "store_id")
    private Store store;
	
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
	 * 获取值：关键词，以逗号分隔
	 */
	public String getKeywords() {
    	return keywords;
    }
	
	/** 
	 * 设置值：关键词，以逗号分隔
	 */    
    public void setKeywords(String keywords) {
    	this.keywords = keywords;
    }
	
	/** 
	 * 获取值：文章
	 */
	public Article getArticle() {
    	return article;
    }
	
	/** 
	 * 设置值：文章
	 */    
    public void setArticle(Article article) {
    	this.article = article;
    }
	
	/** 
	 * 获取值：商家
	 */
	public Store getStore() {
    	return store;
    }
	
	/** 
	 * 设置值：商家
	 */    
    public void setStore(Store store) {
    	this.store = store;
    }
	
}
