package com.daxia.wy.common;

public enum QuestionStatus {
    NotAnswered(0, "未回复"), 
    Answered(1, "已回复");

    private int value;
    private String remark;

    private QuestionStatus(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public QuestionStatus getByValue(int value) {
        for (QuestionStatus o : QuestionStatus.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
