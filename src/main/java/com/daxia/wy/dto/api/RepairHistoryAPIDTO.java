package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class RepairHistoryAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    private String state;
    private Date updateTime;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
