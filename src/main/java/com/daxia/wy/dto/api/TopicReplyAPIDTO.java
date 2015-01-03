package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class TopicReplyAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 帖子
     */
    private TopicAPIDTO topic;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复用户
     */
    private UserAPIDTO user;
    private String floor;
    /**
     * 回复时间
     */
    private Date replyTime;
    private String isEstate;
    private TopicReplyAPIDTO parentTopicReply;
    
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
    public TopicAPIDTO getTopic() {
        return topic;
    }
    public void setTopic(TopicAPIDTO topic) {
        this.topic = topic;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public UserAPIDTO getUser() {
        return user;
    }
    public void setUser(UserAPIDTO user) {
        this.user = user;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    public String getIsEstate() {
        return isEstate;
    }
    public void setIsEstate(String isEstate) {
        this.isEstate = isEstate;
    }
    public TopicReplyAPIDTO getParentTopicReply() {
        return parentTopicReply;
    }
    public void setParentTopicReply(TopicReplyAPIDTO parentTopicReply) {
        this.parentTopicReply = parentTopicReply;
    }
    
}
