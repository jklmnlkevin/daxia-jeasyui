package com.daxia.wy.common;

/**
 * 这是不用维修基金的，也就是自己出钱的
 */
public enum RepairStatus2 {
    Apply(0, "提交申请"), 
    Statistics(1, "物业已受理"),
    Reported(2, "上门维修"),
    Accepted(3, "维修完成");
    

    private int value;
    private String remark;

    private RepairStatus2(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public RepairStatus2 getByValue(int value) {
        for (RepairStatus2 o : RepairStatus2.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}