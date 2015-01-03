package com.daxia.wy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;
import com.daxia.core.util.BigDecimalUtils;
import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.common.IUserRelatedModel;

/**
 * Order
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "`order`") // 指定与数据库映射的表名
public class Order extends BaseModel implements IUserRelatedModel, ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 订单号
	 */
	@Column(name = "orderno")
    private String orderNo;
	/**
	 * 下单时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")   
    @Column(name = "lastUpdatedTime")
    private Date lastUpdatedTime;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	/**
	 * 金额
	 */
	@Column(name = "money")
    private BigDecimal money;
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	/**
	 * 订单状态
	 */
	@Column(name = "status")
    private Integer status;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;
	
	@ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
	
	/**
	 * CategoryTypes
	 */
	private Integer type;
    
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
	 * 获取值：订单号
	 */
	public String getOrderNo() {
    	return orderNo;
    }
	
	/** 
	 * 设置值：订单号
	 */    
    public void setOrderNo(String orderNo) {
    	this.orderNo = orderNo;
    }
	
	/** 
	 * 获取值：下单时间
	 */
	public Date getCreateTime() {
    	return createTime;
    }
	
	/** 
	 * 设置值：下单时间
	 */    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
	
	/** 
	 * 获取值：用户
	 */
	public User getUser() {
    	return user;
    }
	
	/** 
	 * 设置值：用户
	 */    
    public void setUser(User user) {
    	this.user = user;
    }
	
	/** 
	 * 获取值：金额
	 */
	public BigDecimal getMoney() {
    	return BigDecimalUtils.moneyValue(money);
    }
	
	/** 
	 * 设置值：金额
	 */    
    public void setMoney(BigDecimal money) {
    	this.money = money;
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
	
	/** 
	 * 获取值：订单状态
	 */
	public Integer getStatus() {
    	return status;
    }
	
	/** 
	 * 设置值：订单状态
	 */    
    public void setStatus(Integer status) {
    	this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
	
}
