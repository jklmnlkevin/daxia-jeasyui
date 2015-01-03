package com.daxia.wy.common;

/**
 * 这是用维修基金的
 */
public enum RepairStatus {
    Apply(0, "申请"), 
    Statistics(1, "统计中"),
    Reported(2, "上报当地维修基金委员会"),
    Accepted(3, "维修基金已通过审批"),
    Refused(4, "维修申请退回"),
    AllocatingMoney(5, "维修款下拨中"),
    MoneyReady(6, "维修款已到账，准备维修");
    

    private int value;
    private String remark;

    private RepairStatus(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public RepairStatus getByValue(int value) {
        for (RepairStatus o : RepairStatus.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}