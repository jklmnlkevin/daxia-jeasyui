package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.RepairAPIDTO;

public class RepairInfoAPIDTO extends BaseInfoAPIDTO {
    private List<RepairAPIDTO> repairInfos;
    public RepairInfoAPIDTO(List<RepairAPIDTO> repairInfos) {
        this.repairInfos = repairInfos;
    }
    public RepairInfoAPIDTO() {
    }
    public List<RepairAPIDTO> getRepairInfos() {
        return repairInfos;
    }
    public void setRepairInfos(List<RepairAPIDTO> repairInfos) {
        this.repairInfos = repairInfos;
    }
    
}
