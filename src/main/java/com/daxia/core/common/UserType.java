package com.daxia.core.common;

public enum UserType { // -1的表示删除，但不写在这里
    Normal(0, "普通用户"),
    StoreManager(2, "管理员"),
    EstateManager(3, "物业"); // 物业

    private int value;
    private String remark;

    private UserType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public UserType getByValue(int value) {
        for (UserType o : UserType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
