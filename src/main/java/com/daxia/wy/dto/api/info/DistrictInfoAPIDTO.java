package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.DistrictAPIDTO;

public class DistrictInfoAPIDTO extends BaseInfoAPIDTO {
    public DistrictInfoAPIDTO() {
    }
    
    public DistrictInfoAPIDTO(List<DistrictAPIDTO> districts) {
        this.districtInfos = districts;
    }
    
    private List<DistrictAPIDTO> districtInfos;

    /**
     * @return the districtInfos
     */
    public List<DistrictAPIDTO> getDistrictInfos() {
        return districtInfos;
    }

    /**
     * @param districtInfos the districtInfos to set
     */
    public void setDistrictInfos(List<DistrictAPIDTO> districtInfos) {
        this.districtInfos = districtInfos;
    }

    
    
}
