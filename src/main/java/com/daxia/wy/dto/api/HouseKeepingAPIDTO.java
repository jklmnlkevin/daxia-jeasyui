package com.daxia.wy.dto.api;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.alibaba.fastjson.annotation.JSONField;
import com.daxia.wy.model.Community;


public class HouseKeepingAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    /**
     * 用户
     */
    
    private UserSimpleAPIDTO2 user;
    /**
     * 小区
     */
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
    /**
     * 标题
     */
    
    private String title;
    /**
     * 内容
     */
    
    private String content;
    /**
     * 年龄
     */
    
    private String age;
    /**
     * 价格
     */
    
    private String price;
    /**
     * 服务类型
     */
    private ServiceTypeAPIDTO serviceType;
    /**
     * 供需类型
     */
    private String type;
    /**
     * 创建时间
     */
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
    public UserSimpleAPIDTO2 getUser() {
        return user;
    }
    public void setUser(UserSimpleAPIDTO2 user) {
        this.user = user;
    }
    public Community getCommunity() {
        return community;
    }
    public void setCommunity(Community community) {
        this.community = community;
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
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public ServiceTypeAPIDTO getServiceType() {
        return serviceType;
    }
    public void setServiceType(ServiceTypeAPIDTO serviceType) {
        this.serviceType = serviceType;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
