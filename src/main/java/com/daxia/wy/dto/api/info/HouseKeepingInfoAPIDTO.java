package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.HouseKeepingAPIDTO;

public class HouseKeepingInfoAPIDTO extends BaseInfoAPIDTO {
    
    public HouseKeepingInfoAPIDTO(List<HouseKeepingAPIDTO> houseKeepingInfos) {
        super();
        this.houseKeepingInfos = houseKeepingInfos;
    }

    private List<HouseKeepingAPIDTO> houseKeepingInfos;

    /**
     * @return the houseKeepingInfos
     */
    public List<HouseKeepingAPIDTO> getHouseKeepingInfos() {
        return houseKeepingInfos;
    }

    /**
     * @param houseKeepingInfos the houseKeepingInfos to set
     */
    public void setHouseKeepingInfos(List<HouseKeepingAPIDTO> houseKeepingInfos) {
        this.houseKeepingInfos = houseKeepingInfos;
    }
    
}
