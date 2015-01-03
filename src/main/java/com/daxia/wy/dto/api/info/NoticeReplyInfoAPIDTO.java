package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.NoticeReplyAPIDTO;

public class NoticeReplyInfoAPIDTO extends BaseInfoAPIDTO {
    
    public NoticeReplyInfoAPIDTO(List<NoticeReplyAPIDTO> noticeReplyInfos) {
        super();
        this.noticeReplyInfos = noticeReplyInfos;
    }

    private List<NoticeReplyAPIDTO> noticeReplyInfos;

    /**
     * @return the noticeReplyInfos
     */
    public List<NoticeReplyAPIDTO> getNoticeReplyInfos() {
        return noticeReplyInfos;
    }

    /**
     * @param noticeReplyInfos the noticeReplyInfos to set
     */
    public void setNoticeReplyInfos(List<NoticeReplyAPIDTO> noticeReplyInfos) {
        this.noticeReplyInfos = noticeReplyInfos;
    }
    
}
