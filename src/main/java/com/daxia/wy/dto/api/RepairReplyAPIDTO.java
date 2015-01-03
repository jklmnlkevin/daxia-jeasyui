package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class RepairReplyAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    private UserSimpleAPIDTO user;
    // private RepairAPIDTO repair;
    private String content;
    private Date createTime;
    
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
    
    public UserSimpleAPIDTO getUser() {
        return user;
    }
    public void setUser(UserSimpleAPIDTO user) {
        this.user = user;
    }
    /*public RepairAPIDTO getRepair() {
        return repair;
    }
    public void setRepair(RepairAPIDTO repair) {
        this.repair = repair;
    }*/
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
