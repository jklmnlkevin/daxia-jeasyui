package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.DoorplateAPIDTO;

public class DoorplateInfoAPIDTO extends BaseInfoAPIDTO {
    private List<DoorplateAPIDTO> doorplateInfos;
    public DoorplateInfoAPIDTO() {
    }
    public DoorplateInfoAPIDTO(List<DoorplateAPIDTO> doorplateInfos) {
        this.doorplateInfos = doorplateInfos;
    }

    public List<DoorplateAPIDTO> getDoorplateInfos() {
        return doorplateInfos;
    }

    public void setDoorplateInfos(List<DoorplateAPIDTO> doorplateInfos) {
        this.doorplateInfos = doorplateInfos;
    }
    
}
