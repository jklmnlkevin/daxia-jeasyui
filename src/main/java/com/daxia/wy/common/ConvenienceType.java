package com.daxia.wy.common;

public enum ConvenienceType {
    Contact(0, "联系方式"), 
    Flow(1, "办事流程");

    private int value;
    private String remark;

    private ConvenienceType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public ConvenienceType getByValue(int value) {
        for (ConvenienceType o : ConvenienceType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
