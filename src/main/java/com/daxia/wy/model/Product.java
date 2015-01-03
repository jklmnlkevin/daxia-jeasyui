package com.daxia.wy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;
import com.daxia.core.util.BigDecimalUtils;
import com.daxia.wy.common.ICommunityRelatedModel;

/**
 * Product
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "product") // 指定与数据库映射的表名
public class Product extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 商品名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 价格
	 */
	@Column(name = "price")
    private BigDecimal price;
	/**
	 * 型号
	 */
	@Column(name = "specification")
    private String specification;

	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	/**
	 * 图片路径
	 */
	@Column(name = "imagePath")
    private String image;
	
	@ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
    
	/**
	 * 单位
	 */
	private String unit;
	
	private int stock;
	
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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
	 * 获取值：商品名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：商品名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }
	
	/** 
	 * 获取值：价格
	 */
	
	
	
	public String getSpecification() {
        return specification;
    }

    public BigDecimal getPrice() {
        return BigDecimalUtils.moneyValue(price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /** 
	 * 获取值：备注
	 */
	public String getRemark() {
    	return remark;
    }
	
	/** 
	 * 设置值：备注
	 */    
    public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
