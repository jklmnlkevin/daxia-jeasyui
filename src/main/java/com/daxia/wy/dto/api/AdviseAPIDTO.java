package com.daxia.wy.dto.api;

import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;


public class AdviseAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    /**
     * 用户
     */
    private UserSimpleAPIDTO user;
    /**
     * 内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserSimpleAPIDTO getUser() {
        return user;
    }
    public void setUser(UserSimpleAPIDTO user) {
        this.user = user;
    }
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
