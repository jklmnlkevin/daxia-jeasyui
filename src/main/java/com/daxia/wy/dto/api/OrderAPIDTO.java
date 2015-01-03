package com.daxia.wy.dto.api;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;


public class OrderAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    private String orderNo;
    /**
     * 下单时间
     */
    private Date createTime;
    /**
     * 金额
     */
    private String money;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单状态
     */
    private String status;
    private String type;
    
    private List<OrderItemAPIDTO> orderItems;
    private Date lastUpdatedTime;

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
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<OrderItemAPIDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemAPIDTO> orderItems) {
        this.orderItems = orderItems;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
}
