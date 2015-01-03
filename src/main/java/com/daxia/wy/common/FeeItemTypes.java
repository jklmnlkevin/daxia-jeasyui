package com.daxia.wy.common;

public enum FeeItemTypes {
    Estate(0, "物业费"),
    Park(1, "停车费"),
    /*Gas(2, "天然气"),
    Water(3, "水费"),
    Elec(4, "电费")*/;

    private int value;
    private String remark;

    private FeeItemTypes(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public static FeeItemTypes getByValue(int value) {
        for (FeeItemTypes o : FeeItemTypes.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}