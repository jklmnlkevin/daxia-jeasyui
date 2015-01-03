package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.TopicAPIDTO;

public class TopicInfoAPIDTO extends BaseInfoAPIDTO {
    
    public TopicInfoAPIDTO(List<TopicAPIDTO> topicInfos) {
        super();
        this.topicInfos = topicInfos;
    }

    private List<TopicAPIDTO> topicInfos;

    /**
     * @return the topicInfos
     */
    public List<TopicAPIDTO> getTopicInfos() {
        return topicInfos;
    }

    /**
     * @param topicInfos the topicInfos to set
     */
    public void setTopicInfos(List<TopicAPIDTO> topicInfos) {
        this.topicInfos = topicInfos;
    }
    
}
