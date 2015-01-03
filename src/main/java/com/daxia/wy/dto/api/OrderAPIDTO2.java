package com.daxia.wy.dto.api;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 这个类的字段都是与返回给手机的json的数组里的内容相对应的，
 * 所有的字段都定义成字符串的形式，除了集合和数组，名称与model类里的字段名相同。
 */
public class OrderAPIDTO2 {
	private String id;
	private UserAPIDTO user;
    private Date orderTime;
    private String status;
    /**
     * 订单金额
     */
    private String amount;
    /**
     * 订单号
     */
    private String orderNo;
    
	/**
     * @return the id
     */
    public String getId() {
    	return id;
    }

	/**
     * @param id the id to set
     */
    public void setId(String id) {
    	this.id = id;
    }

	
	/**
     * @return the orderTime
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getOrderTime() {
    	return orderTime;
    }

	/**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(Date orderTime) {
    	this.orderTime = orderTime;
    }

	/**
     * @return the status
     */
    public String getStatus() {
    	return status;
    }

	/**
     * @param status the status to set
     */
    public void setStatus(String status) {
    	this.status = status;
    }

	/**
     * @return the amount
     */
    public String getAmount() {
    	return amount;
    }

	/**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
    	this.amount = amount;
    }

	/**
     * @return the orderNo
     */
    public String getOrderNo() {
    	return orderNo;
    }

	/**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
    	this.orderNo = orderNo;
    }
	
	/**
     * @return the user
     */
    public UserAPIDTO getUser() {
    	return user;
    }

	/**
     * @param user the user to set
     */
    public void setUser(UserAPIDTO user) {
    	this.user = user;
    }

}
