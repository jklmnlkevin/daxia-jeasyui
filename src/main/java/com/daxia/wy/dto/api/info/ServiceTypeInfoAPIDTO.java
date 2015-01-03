package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.ServiceTypeAPIDTO;

public class ServiceTypeInfoAPIDTO extends BaseInfoAPIDTO {
    
    public ServiceTypeInfoAPIDTO(List<ServiceTypeAPIDTO> serviceTypeInfos) {
        super();
        this.serviceTypeInfos = serviceTypeInfos;
    }

    private List<ServiceTypeAPIDTO> serviceTypeInfos;

    /**
     * @return the serviceTypeInfos
     */
    public List<ServiceTypeAPIDTO> getServiceTypeInfos() {
        return serviceTypeInfos;
    }

    /**
     * @param serviceTypeInfos the serviceTypeInfos to set
     */
    public void setServiceTypeInfos(List<ServiceTypeAPIDTO> serviceTypeInfos) {
        this.serviceTypeInfos = serviceTypeInfos;
    }
    
}
