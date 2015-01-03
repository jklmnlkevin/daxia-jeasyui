package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.RepairReplyAPIDTO;

public class RepairReplyInfoAPIDTO extends BaseInfoAPIDTO {
    
    public RepairReplyInfoAPIDTO(List<RepairReplyAPIDTO> repairReplyInfos) {
        super();
        this.repairReplyInfos = repairReplyInfos;
    }

    private List<RepairReplyAPIDTO> repairReplyInfos;

    /**
     * @return the repairReplyInfos
     */
    public List<RepairReplyAPIDTO> getRepairReplyInfos() {
        return repairReplyInfos;
    }

    /**
     * @param repairReplyInfos the repairReplyInfos to set
     */
    public void setRepairReplyInfos(List<RepairReplyAPIDTO> repairReplyInfos) {
        this.repairReplyInfos = repairReplyInfos;
    }
    
}
