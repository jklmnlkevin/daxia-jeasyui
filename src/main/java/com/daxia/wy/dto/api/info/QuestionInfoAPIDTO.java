package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.QuestionAPIDTO;

public class QuestionInfoAPIDTO extends BaseInfoAPIDTO {
    private List<QuestionAPIDTO> questionInfos;
    public QuestionInfoAPIDTO(List<QuestionAPIDTO> questionInfos) {
        this.questionInfos = questionInfos;
    }
    public QuestionInfoAPIDTO() {
    }
    public List<QuestionAPIDTO> getQuestionInfos() {
        return questionInfos;
    }
    public void setQuestionInfos(List<QuestionAPIDTO> questionInfos) {
        this.questionInfos = questionInfos;
    }
    
}
