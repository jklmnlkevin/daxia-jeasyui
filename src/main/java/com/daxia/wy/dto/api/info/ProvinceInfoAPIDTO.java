package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.ProvinceAPIDTO;

public class ProvinceInfoAPIDTO extends BaseInfoAPIDTO {
    public ProvinceInfoAPIDTO() {
    }
    
    public ProvinceInfoAPIDTO(List<ProvinceAPIDTO> provinces) {
        this.provinceInfos = provinces;
    }
    
    private List<ProvinceAPIDTO> provinceInfos;

    /**
     * @return the provinceInfos
     */
    public List<ProvinceAPIDTO> getProvinceInfos() {
        return provinceInfos;
    }

    /**
     * @param provinceInfos the provinceInfos to set
     */
    public void setProvinceInfos(List<ProvinceAPIDTO> provinceInfos) {
        this.provinceInfos = provinceInfos;
    }
    
}
