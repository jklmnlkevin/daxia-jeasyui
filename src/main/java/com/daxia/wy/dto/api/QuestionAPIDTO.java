package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class QuestionAPIDTO {
    private String id;
    private UserAPIDTO user;
    private String title;
    private String content;
    private Date createTime;
    private String status;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public UserAPIDTO getUser() {
        return user;
    }
    public void setUser(UserAPIDTO user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
