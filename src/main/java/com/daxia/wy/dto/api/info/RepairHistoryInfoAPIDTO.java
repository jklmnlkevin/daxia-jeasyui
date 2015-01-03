package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.RepairHistoryAPIDTO;

public class RepairHistoryInfoAPIDTO extends BaseInfoAPIDTO {
    
    public RepairHistoryInfoAPIDTO(List<RepairHistoryAPIDTO> repairHistoryInfos) {
        super();
        this.repairHistoryInfos = repairHistoryInfos;
    }

    private List<RepairHistoryAPIDTO> repairHistoryInfos;

    /**
     * @return the repairHistoryInfos
     */
    public List<RepairHistoryAPIDTO> getRepairHistoryInfos() {
        return repairHistoryInfos;
    }

    /**
     * @param repairHistoryInfos the repairHistoryInfos to set
     */
    public void setRepairHistoryInfos(List<RepairHistoryAPIDTO> repairHistoryInfos) {
        this.repairHistoryInfos = repairHistoryInfos;
    }
    
}
