package com.daxia.wy.common;

public enum PushCode {
    Common("0", "一般"), 
    Notice("10", "通知公告"),
    RepairStateChanged("20", "物业维修状态变更"),
    EstateRepliedTopic("30", "物业回复了帖子"),
    SystemMessage("40", "系统消息");

    private String value;
    private String remark;

    private PushCode(String value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public String getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public PushCode getByValue(int value) {
        for (PushCode o : PushCode.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return null;
    }
}
