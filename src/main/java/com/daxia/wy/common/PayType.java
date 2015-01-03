package com.daxia.wy.common;

public enum PayType {
    Cash(0, "现金"),
    AliPay(1, "支付宝"); 

    private int value;
    private String remark;

    private PayType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public static PayType getByValue(int value) {
        for (PayType o : PayType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}