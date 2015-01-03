package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.SystemMessageAPIDTO;

public class SystemMessageInfoAPIDTO extends BaseInfoAPIDTO {
    
    public SystemMessageInfoAPIDTO(List<SystemMessageAPIDTO> systemMessageInfos) {
        super();
        this.systemMessageInfos = systemMessageInfos;
    }

    private List<SystemMessageAPIDTO> systemMessageInfos;

    /**
     * @return the systemMessageInfos
     */
    public List<SystemMessageAPIDTO> getSystemMessageInfos() {
        return systemMessageInfos;
    }

    /**
     * @param systemMessageInfos the systemMessageInfos to set
     */
    public void setSystemMessageInfos(List<SystemMessageAPIDTO> systemMessageInfos) {
        this.systemMessageInfos = systemMessageInfos;
    }
    
}
