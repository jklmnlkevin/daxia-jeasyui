package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class NoticeReplyAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    private NoticeReplyAPIDTO parentNoticeReply;
    
    /**
     * 用户
     */
    private UserAPIDTO user;
    private String content;
    private Date createTime;
    private String floor;
    
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
    
    public NoticeReplyAPIDTO getParentNoticeReply() {
        return parentNoticeReply;
    }
    public void setParentNoticeReply(NoticeReplyAPIDTO parentNoticeReply) {
        this.parentNoticeReply = parentNoticeReply;
    }
    public UserAPIDTO getUser() {
        return user;
    }
    public void setUser(UserAPIDTO user) {
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
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    
}
