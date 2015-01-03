package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.TopicReplyAPIDTO;

public class TopicReplyInfoAPIDTO extends BaseInfoAPIDTO {
    
    public TopicReplyInfoAPIDTO(List<TopicReplyAPIDTO> topicReplyInfos) {
        super();
        this.topicReplyInfos = topicReplyInfos;
    }

    private List<TopicReplyAPIDTO> topicReplyInfos;

    /**
     * @return the topicReplyInfos
     */
    public List<TopicReplyAPIDTO> getTopicReplyInfos() {
        return topicReplyInfos;
    }

    /**
     * @param topicReplyInfos the topicReplyInfos to set
     */
    public void setTopicReplyInfos(List<TopicReplyAPIDTO> topicReplyInfos) {
        this.topicReplyInfos = topicReplyInfos;
    }
    
}
