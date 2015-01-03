package com.daxia.wy.common;

public enum CommunityAddApplyStatus {
    New(0, "新增"), Agreed(1, "通过"), Denied(1, "拒绝");

    private int value;
    private String remark;

    private CommunityAddApplyStatus(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public CommunityAddApplyStatus getByValue(int value) {
        for (CommunityAddApplyStatus o : CommunityAddApplyStatus.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
