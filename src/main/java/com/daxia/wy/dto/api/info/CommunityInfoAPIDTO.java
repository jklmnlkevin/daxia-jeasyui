package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.CommunityAPIDTO;

public class CommunityInfoAPIDTO extends BaseInfoAPIDTO {
    
    public CommunityInfoAPIDTO(List<CommunityAPIDTO> communityInfos) {
        super();
        this.communityInfos = communityInfos;
    }

    private List<CommunityAPIDTO> communityInfos;

    /**
     * @return the communityInfos
     */
    public List<CommunityAPIDTO> getCommunityInfos() {
        return communityInfos;
    }

    /**
     * @param communityInfos the communityInfos to set
     */
    public void setCommunityInfos(List<CommunityAPIDTO> communityInfos) {
        this.communityInfos = communityInfos;
    }
    
}
