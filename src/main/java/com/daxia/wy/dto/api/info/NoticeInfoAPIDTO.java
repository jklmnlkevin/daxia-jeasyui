package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.NoticeAPIDTO;

public class NoticeInfoAPIDTO extends BaseInfoAPIDTO {
    public NoticeInfoAPIDTO() {
    }
    
    public NoticeInfoAPIDTO(List<NoticeAPIDTO> notices) {
        this.noticeInfos = notices;
    }
    
    private List<NoticeAPIDTO> noticeInfos;

    /**
     * @return the noticeInfos
     */
    public List<NoticeAPIDTO> getNoticeInfos() {
        return noticeInfos;
    }

    /**
     * @param noticeInfos the noticeInfos to set
     */
    public void setNoticeInfos(List<NoticeAPIDTO> noticeInfos) {
        this.noticeInfos = noticeInfos;
    }
    
    
}
