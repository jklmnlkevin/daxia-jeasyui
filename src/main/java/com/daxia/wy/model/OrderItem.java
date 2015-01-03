package com.daxia.wy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.daxia.core.model.BaseModel;

import com.daxia.wy.model.Order;
import com.daxia.wy.model.Product;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * OrderItem
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "orderitem") // 指定与数据库映射的表名
public class OrderItem extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 订单
	 */
	@ManyToOne
	@JoinColumn(name = "order_id")
    private Order order;
	/**
	 * 产品
	 */
	@ManyToOne
	@JoinColumn(name = "product_id")
    private Product product;
	/**
	 * 产品价格
	 */
	@Column(name = "price")
    private BigDecimal price;
	/**
	 * 产品数量
	 */
	@Column(name = "amount")
    private Integer amount;
	
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
	 * 获取值：订单
	 */
	public Order getOrder() {
    	return order;
    }
	
	/** 
	 * 设置值：订单
	 */    
    public void setOrder(Order order) {
    	this.order = order;
    }
	
	/** 
	 * 获取值：产品
	 */
	public Product getProduct() {
    	return product;
    }
	
	/** 
	 * 设置值：产品
	 */    
    public void setProduct(Product product) {
    	this.product = product;
    }
	
	/** 
	 * 获取值：产品价格
	 */
	public BigDecimal getPrice() {
    	return price;
    }
	
	/** 
	 * 设置值：产品价格
	 */    
    public void setPrice(BigDecimal price) {
    	this.price = price;
    }
	
	/** 
	 * 获取值：产品数量
	 */
	public Integer getAmount() {
    	return amount;
    }
	
	/** 
	 * 设置值：产品数量
	 */    
    public void setAmount(Integer amount) {
    	this.amount = amount;
    }
	
}
