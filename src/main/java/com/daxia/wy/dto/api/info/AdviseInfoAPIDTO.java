package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.AdviseAPIDTO;

public class AdviseInfoAPIDTO extends BaseInfoAPIDTO {
    
    public AdviseInfoAPIDTO(List<AdviseAPIDTO> adviseInfos) {
        super();
        this.adviseInfos = adviseInfos;
    }

    private List<AdviseAPIDTO> adviseInfos;

    /**
     * @return the adviseInfos
     */
    public List<AdviseAPIDTO> getAdviseInfos() {
        return adviseInfos;
    }

    /**
     * @param adviseInfos the adviseInfos to set
     */
    public void setAdviseInfos(List<AdviseAPIDTO> adviseInfos) {
        this.adviseInfos = adviseInfos;
    }
    
}
