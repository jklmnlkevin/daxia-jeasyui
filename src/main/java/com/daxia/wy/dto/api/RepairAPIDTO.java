package com.daxia.wy.dto.api;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class RepairAPIDTO {
    /**
     * id
     */
    private String id;
    /**
     * 图片
     */
    private String[] images;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private String state;
    private String title;
    private String useFund;
    /**
     * 批次
     */
    private String batch;
    private List<RepairHistoryAPIDTO> repairHistories;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getState() {
        return state;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public String[] getImages() {
        return images;
    }
    public void setImages(String[] images) {
        this.images = images;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUseFund() {
        return useFund;
    }
    public void setUseFund(String useFund) {
        this.useFund = useFund;
    }
    public List<RepairHistoryAPIDTO> getRepairHistories() {
        return repairHistories;
    }
    public void setRepairHistories(List<RepairHistoryAPIDTO> repairHistories) {
        this.repairHistories = repairHistories;
    }
    
}
