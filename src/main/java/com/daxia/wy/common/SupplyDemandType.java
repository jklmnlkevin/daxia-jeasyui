package com.daxia.wy.common;

public enum SupplyDemandType {
    Supply(0, "提供"), 
    Demand(1, "需要");

    private int value;
    private String remark;

    private SupplyDemandType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public SupplyDemandType getByValue(int value) {
        for (SupplyDemandType o : SupplyDemandType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}