package com.daxia.wy.web.controller.m;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.daxia.core.model.User;
import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.NoticeDTO;
import com.daxia.wy.dto.NoticeReplyDTO;
import com.daxia.wy.dto.api.NoticeReplyAPIDTO;
import com.daxia.wy.dto.api.info.NoticeReplyInfoAPIDTO;
import com.daxia.wy.model.Notice;
import com.daxia.wy.model.NoticeReply;

/**
 * 物业缴费
 */
@Controller
@RequestMapping(value = "/m/pay", produces = "text/html;charset=UTF-8")
public class MPayController extends MBaseController {
    
    /**
     * 物业缴费情况
     * @param dto
     * @param page
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("wy")
    public String wy(NoticeDTO dto, MPage page) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("area", "100");
        obj.put("price", "1.1");
        return toJson(obj);
    }
    
    @ResponseBody
    @RequestMapping("car")
    public String car(NoticeDTO dto, MPage page) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("price", "100");
        return toJson(obj);
    }
    
    /**
     * http://localhost:8080/wy/m/notice/getReply?id=1
     * @param id
     * @param pageNum
     * @param numPerPage
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getReply")
    public String getReply(Long id, MPage page) throws Exception {
        ValidationUtils.assertTrue(id != null, "id不能为空");
        List<NoticeReplyDTO> replies = noticeReplyService.findByNotice(id, page);
        return toApiOutput(replies, NoticeReplyAPIDTO.class, NoticeReplyInfoAPIDTO.class, page);
    }
    
    /**
     * http://localhost:8080/wy/m/notice/reply?user.id=3&id=1&content=javaisgood&parentReplyId=
     * @param request
     * @param id
     * @param content
     * @param parentReplyId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("reply")
    public String reply(HttpServletRequest request, Long id, String content, Long parentReplyId) throws Exception {
        ValidationUtils.assertTrue(request.getParameter("user.id") != null, "user.id不能为空");
        Long userId = Long.valueOf(request.getParameter("user.id"));
        ValidationUtils.assertTrue(id != null, "id不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(content), "回复内容不能为空");
        
        NoticeReplyDTO replyDTO = new NoticeReplyDTO();
        replyDTO.setNotice(new Notice());
        replyDTO.getNotice().setId(id);
        
        replyDTO.setUser(new User());
        replyDTO.getUser().setId(userId);
        
        replyDTO.setContent(content);
        replyDTO.setCreateTime(new Date());
        
        if (parentReplyId != null) {
            replyDTO.setParentNoticeReply(new NoticeReply());
            replyDTO.getParentNoticeReply().setId(parentReplyId);
        }
        
        Long replyId = noticeReplyService.reply(replyDTO);
        NoticeReply reply = noticeReplyService.load(replyId);
        return toApiOutput(reply, NoticeReplyAPIDTO.class, NoticeReplyInfoAPIDTO.class);
    }
}
