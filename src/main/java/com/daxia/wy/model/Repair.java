package com.daxia.wy.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.daxia.core.model.BaseModel;
import com.daxia.core.model.User;
import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.common.IUserRelatedModel;

/**
 * Repair
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "repair") // 指定与数据库映射的表名
public class Repair extends BaseModel implements IUserRelatedModel, ICommunityRelatedModel {
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
	 * 图片.改成存多张图片，用逗号,隔开
	 */
	@Column(name = "image")
    private String image;
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createtime")
    private Date createTime;
	/**
	 * 状态
	 */
	@Column(name = "state")
    private Integer state = 0;
	/**
	 * 批次
	 */
	@Column(name = "batch")
    private Integer batch;
	private String title;
	@Column(name = "isusefund")
	private boolean useFund;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE}
       ,fetch = FetchType.LAZY, mappedBy = "repair")
	@OrderBy(value  = "id ASC")
	private List<RepairHistory> repairHistories = new ArrayList<RepairHistory>();
	
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
	 * 获取值：图片
	 */
	public String getImage() {
    	return image;
    }
	
	/** 
	 * 设置值：图片
	 */    
    public void setImage(String image) {
    	this.image = image;
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
    
    
	
	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
	 * 获取值：批次
	 */
	public Integer getBatch() {
    	return batch;
    }
	
	/** 
	 * 设置值：批次
	 */    
    public void setBatch(Integer batch) {
    	this.batch = batch;
    }
    
    public String[] getImageArr() {
        if (StringUtils.isBlank(getImage())) {
            return new String[0];
        }
        return getImage().split("\\ *,\\ *");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUseFund() {
        return useFund;
    }

    public void setUseFund(boolean useFund) {
        this.useFund = useFund;
    }

    public List<RepairHistory> getRepairHistories() {
        return repairHistories;
    }

    public void setRepairHistories(List<RepairHistory> repairHistories) {
        this.repairHistories = repairHistories;
    }
    
}
