package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.TopicReplyDTO;
import com.daxia.wy.dto.api.TopicReplyAPIDTO;
import com.daxia.wy.dto.api.info.TopicReplyInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/topicReply", produces = "text/html;charset=UTF-8")
public class MTopicReplyController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(TopicReplyDTO dto, MPage page) throws Exception {
    
        List<TopicReplyDTO> topicReplyDTOs = topicReplyService.find(dto, page);

        return toApiOutput(topicReplyDTOs, TopicReplyAPIDTO.class, TopicReplyInfoAPIDTO.class, page);
    }
    
}
