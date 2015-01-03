package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.AdviseReplyAPIDTO;

public class AdviseReplyInfoAPIDTO extends BaseInfoAPIDTO {
    
    public AdviseReplyInfoAPIDTO(List<AdviseReplyAPIDTO> adviseReplyInfos) {
        super();
        this.adviseReplyInfos = adviseReplyInfos;
    }

    private List<AdviseReplyAPIDTO> adviseReplyInfos;

    /**
     * @return the adviseReplyInfos
     */
    public List<AdviseReplyAPIDTO> getAdviseReplyInfos() {
        return adviseReplyInfos;
    }

    /**
     * @param adviseReplyInfos the adviseReplyInfos to set
     */
    public void setAdviseReplyInfos(List<AdviseReplyAPIDTO> adviseReplyInfos) {
        this.adviseReplyInfos = adviseReplyInfos;
    }
    
}
