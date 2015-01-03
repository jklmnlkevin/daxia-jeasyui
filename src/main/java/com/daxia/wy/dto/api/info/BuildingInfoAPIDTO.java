package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.BuildingAPIDTO;

public class BuildingInfoAPIDTO extends BaseInfoAPIDTO {
    private List<BuildingAPIDTO> buildingInfos;
    public BuildingInfoAPIDTO() {
    }
    public BuildingInfoAPIDTO(List<BuildingAPIDTO> buildingInfos) {
        this.buildingInfos = buildingInfos;
    }
    public List<BuildingAPIDTO> getBuildingInfos() {
        return buildingInfos;
    }
    public void setBuildingInfos(List<BuildingAPIDTO> buildingInfos) {
        this.buildingInfos = buildingInfos;
    }
    
    
    
}
