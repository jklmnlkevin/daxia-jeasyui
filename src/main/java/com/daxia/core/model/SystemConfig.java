package com.daxia.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * systemConfig
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "systemconfig") // 指定与数据库映射的表名
public class SystemConfig extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

    /**
     * key
     */
    @Column(name = "_key")
    private String key;
    /**
     * value
     */
    private String value;

    /**
     * 说明
     */
    private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * 获取值 ：key
     */
    public String getKey() {
        return key;
    }
    /**
     * 设置值 ：key
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * 获取值 ：value
     */
    public String getValue() {
        return value;
    }
    /**
     * 设置值 ：value
     */
    public void setValue(String value) {
        this.value = value;
    }
	public String getRemark() {
    	return remark;
    }
	public void setRemark(String remark) {
    	this.remark = remark;
    }

}
