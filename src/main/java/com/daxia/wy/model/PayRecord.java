package com.daxia.wy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;
import com.daxia.core.util.BigDecimalUtils;
import com.daxia.wy.common.ICommunityRelatedModel;

/**
 * PayRecord
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "payrecord") // 指定与数据库映射的表名
public class PayRecord extends BaseModel implements ICommunityRelatedModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	/**
	 * 缴费时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Column(name = "payTime")
    private Date payTime;
	/**
	 * 缴费项目
	 */
	@ManyToOne
	@JoinColumn(name = "feeItem_id")
    private FeeItem feeItem;
	/**
	 * 缴费月数
	 */
	@Column(name = "monthCount")
    private Integer monthCount;
	/**
	 * 缴费开始月份
	 */
	@DateTimeFormat(pattern = "yyyy-MM")	
	@Column(name = "monthStart")
    private Date monthStart;
	/**
	 * 缴费结束月份
	 */
	@DateTimeFormat(pattern = "yyyy-MM")	
	@Column(name = "monthEnd")
    private Date monthEnd;
	/**
	 * 支付类型
	 */
	@Column(name = "payType")
    private Integer payType;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createTime")
    private Date createTime;
	/**
	 * 金额
	 */
	@Column(name = "money")
    private BigDecimal money;
	@ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
    
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
	 * 获取值：缴费时间
	 */
	public Date getPayTime() {
    	return payTime;
    }
	
	/** 
	 * 设置值：缴费时间
	 */    
    public void setPayTime(Date payTime) {
    	this.payTime = payTime;
    }
	
	/** 
	 * 获取值：缴费项目
	 */
	public FeeItem getFeeItem() {
    	return feeItem;
    }
	
	/** 
	 * 设置值：缴费项目
	 */    
    public void setFeeItem(FeeItem feeItem) {
    	this.feeItem = feeItem;
    }
	
	/** 
	 * 获取值：缴费月数
	 */
	public Integer getMonthCount() {
    	return monthCount;
    }
	
	/** 
	 * 设置值：缴费月数
	 */    
    public void setMonthCount(Integer monthCount) {
    	this.monthCount = monthCount;
    }
	
	/** 
	 * 获取值：缴费开始月份
	 */
	public Date getMonthStart() {
    	return monthStart;
    }
	
	/** 
	 * 设置值：缴费开始月份
	 */    
    public void setMonthStart(Date monthStart) {
    	this.monthStart = monthStart;
    }
	
	/** 
	 * 获取值：缴费结束月份
	 */
	public Date getMonthEnd() {
    	return monthEnd;
    }
	
	/** 
	 * 设置值：缴费结束月份
	 */    
    public void setMonthEnd(Date monthEnd) {
    	this.monthEnd = monthEnd;
    }
	
	/** 
	 * 获取值：支付类型
	 */
	public Integer getPayType() {
    	return payType;
    }
	
	/** 
	 * 设置值：支付类型
	 */    
    public void setPayType(Integer payType) {
    	this.payType = payType;
    }
	
	/** 
	 * 获取值：创建时间
	 */
	public Date getCreateTime() {
    	return createTime;
    }
	
	/** 
	 * 设置值：创建时间
	 */    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return BigDecimalUtils.moneyValue(money);
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
	
}
