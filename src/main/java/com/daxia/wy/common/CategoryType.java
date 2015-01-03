package com.daxia.wy.common;

public enum CategoryType {
    Market(0, "超市"), 
    Fresh(1, "生鲜");

    private int value;
    private String remark;

    private CategoryType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public CategoryType getByValue(int value) {
        for (CategoryType o : CategoryType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
