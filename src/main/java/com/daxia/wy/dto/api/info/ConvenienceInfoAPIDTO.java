package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.ConvenienceAPIDTO;

public class ConvenienceInfoAPIDTO extends BaseInfoAPIDTO {
    
    public ConvenienceInfoAPIDTO(List<ConvenienceAPIDTO> convenienceInfos) {
        super();
        this.convenienceInfos = convenienceInfos;
    }

    private List<ConvenienceAPIDTO> convenienceInfos;

    /**
     * @return the convenienceInfos
     */
    public List<ConvenienceAPIDTO> getConvenienceInfos() {
        return convenienceInfos;
    }

    /**
     * @param convenienceInfos the convenienceInfos to set
     */
    public void setConvenienceInfos(List<ConvenienceAPIDTO> convenienceInfos) {
        this.convenienceInfos = convenienceInfos;
    }
    
}
