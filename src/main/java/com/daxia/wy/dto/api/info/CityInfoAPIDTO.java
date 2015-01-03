package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.CityAPIDTO;

public class CityInfoAPIDTO extends BaseInfoAPIDTO {
    public CityInfoAPIDTO() {
    }
    
    public CityInfoAPIDTO(List<CityAPIDTO> cities) {
        this.cityInfos = cities;
    }
    
    private List<CityAPIDTO> cityInfos;

    /**
     * @return the cityInfos
     */
    public List<CityAPIDTO> getCityInfos() {
        return cityInfos;
    }

    /**
     * @param cityInfos the cityInfos to set
     */
    public void setCityInfos(List<CityAPIDTO> cityInfos) {
        this.cityInfos = cityInfos;
    }

    

    
    
}
