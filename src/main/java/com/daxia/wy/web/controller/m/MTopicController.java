package com.daxia.wy.web.controller.m;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.model.User;
import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.TopicDTO;
import com.daxia.wy.dto.TopicReplyDTO;
import com.daxia.wy.dto.api.TopicAPIDTO;
import com.daxia.wy.dto.api.TopicReplyAPIDTO;
import com.daxia.wy.dto.api.info.TopicInfoAPIDTO;
import com.daxia.wy.dto.api.info.TopicReplyInfoAPIDTO;
import com.daxia.wy.model.Topic;
import com.daxia.wy.model.TopicReply;

@Controller
@RequestMapping(value = "/m/topic", produces = "text/html;charset=UTF-8")
public class MTopicController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(TopicDTO dto, MPage page, HttpServletRequest request) throws Exception {
        List<TopicDTO> topicDTOs = topicService.find(dto, page);
        if (CollectionUtils.isNotEmpty(topicDTOs)) {
            for (TopicDTO topicDTO : topicDTOs) {
                if (topicDTO.getImageArr().length > 0) {
                    String[] arr = new String[topicDTO.getImageArr().length];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = getCtx(request) + "/image/" + topicDTO.getImageArr()[i];
                    }
                    topicDTO.setImages(arr);
                }
            }
        }
        return toApiOutput(topicDTOs, TopicAPIDTO.class, TopicInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping(value = "publish")
    public String publish(TopicDTO dto, HttpServletRequest request) throws Exception {
        if (ArrayUtils.isNotEmpty(dto.getImages())) {
            dto.setImage(StringUtils.join(dto.getImages(), ","));
        }
        Long id = topicService.create(dto);
        TopicDTO topicDTO = topicService.load(id);
        if (ArrayUtils.isNotEmpty(topicDTO.getImageArr())) {
            String[] arr = new String[topicDTO.getImageArr().length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = getCtx(request) + "/image/" + topicDTO.getImageArr()[i];
            }
            topicDTO.setImages(arr);
        }

        return toApiOutput(topicDTO, TopicAPIDTO.class, TopicInfoAPIDTO.class);
    }
    
    @ResponseBody
    @RequestMapping("reply")
    public String reply(HttpServletRequest request, Long id, String content, Long parentReplyId) throws Exception {
        ValidationUtils.assertTrue(request.getParameter("user.id") != null, "user.id不能为空");
        Long userId = Long.valueOf(request.getParameter("user.id"));
        ValidationUtils.assertTrue(id != null, "id不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(content), "回复内容不能为空");
        
        TopicReplyDTO replyDTO = new TopicReplyDTO();
        replyDTO.setTopic(new Topic());
        replyDTO.getTopic().setId(id);
        
        replyDTO.setUser(new User());
        replyDTO.getUser().setId(userId);
        
        replyDTO.setContent(content);
        replyDTO.setReplyTime(new Date());
        
        if (parentReplyId != null) {
            replyDTO.setParentTopicReply(new TopicReply());
            replyDTO.getParentTopicReply().setId(parentReplyId);
        }
        
        Long replyId = topicReplyService.reply(replyDTO);
        TopicReply reply = topicReplyService.load(replyId);
        return toApiOutput(reply, TopicReplyAPIDTO.class, TopicReplyInfoAPIDTO.class);
    }
    
    @ResponseBody
    @RequestMapping("click")
    public String click(Long id) throws Exception {
        topicService.addClick(id);
        return toJson("ok");
    }
    
    @ResponseBody
    @RequestMapping("getReply")
    public String getReply(Long id, boolean isEstate, MPage page) throws Exception {
        ValidationUtils.assertTrue(id != null, "id不能为空");
        List<TopicReplyDTO> replies = topicReplyService.findByTopic(id, isEstate, page);
        return toApiOutput(replies, TopicReplyAPIDTO.class, TopicReplyInfoAPIDTO.class, page);
    }
}
