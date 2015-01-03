package com.daxia.wy.dto.api;

import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;


public class TopicAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发表时间
     */
    private Date createTime;
    /**
     * 最后回复时间
     */
    private Date lastReplyTime;
    /**
     * 点击次数
     */
    private String clickCount;
    /**
     * 回复次数
     */
    private String replyCount;
    /**
     * 发起人
     */
    
    private UserAPIDTO user;
    /**
     * 是否邀请物业回复
     */
    private String isInviteEstate;
    /**
     * 物业是否已经回复
     */
    private String isEstateReplied;
    /**
     * 图片列表
     */
    @Column(name = "images")
    private String[] images;
    
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getLastReplyTime() {
        return lastReplyTime;
    }
    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }
    public String getClickCount() {
        return clickCount;
    }
    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }
    public String getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }
    public UserAPIDTO getUser() {
        return user;
    }
    public void setUser(UserAPIDTO user) {
        this.user = user;
    }
    public String getIsInviteEstate() {
        return isInviteEstate;
    }
    public void setIsInviteEstate(String isInviteEstate) {
        this.isInviteEstate = isInviteEstate;
    }
    public String getIsEstateReplied() {
        return isEstateReplied;
    }
    public void setIsEstateReplied(String isEstateReplied) {
        this.isEstateReplied = isEstateReplied;
    }
    public String[] getImages() {
        return images;
    }
    public void setImages(String[] images) {
        this.images = images;
    }
    
}
