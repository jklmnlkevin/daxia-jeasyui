package com.daxia.wy.model;

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

/**
 * RepairHistory
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "repairhistory") // 指定与数据库映射的表名
public class RepairHistory extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 维修
	 */
	@ManyToOne
	@JoinColumn(name = "repair_id")
    private Repair repair;
	/**
	 * 状态
	 */
	@Column(name = "state")
    private Integer state;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "updateTime")
    private Date updateTime;
	
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
	 * 获取值：维修
	 */
	public Repair getRepair() {
    	return repair;
    }
	
	/** 
	 * 设置值：维修
	 */    
    public void setRepair(Repair repair) {
    	this.repair = repair;
    }
	
	/** 
	 * 获取值：状态
	 */
	public Integer getState() {
    	return state;
    }
	
	/** 
	 * 设置值：状态
	 */    
    public void setState(Integer state) {
    	this.state = state;
    }
	
	/** 
	 * 获取值：更新时间
	 */
	public Date getUpdateTime() {
    	return updateTime;
    }
	
	/** 
	 * 设置值：更新时间
	 */    
    public void setUpdateTime(Date updateTime) {
    	this.updateTime = updateTime;
    }
	
}
