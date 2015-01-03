package com.daxia.wy.web.controller.m;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.core.util.WebParamUtils;
import com.daxia.wy.dto.QuestionDTO;
import com.daxia.wy.dto.api.QuestionAPIDTO;
import com.daxia.wy.dto.api.info.QuestionInfoAPIDTO;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/m/question", produces = "text/html;charset=UTF-8")
public class MQuestionController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(QuestionDTO dto, MPage page) throws Exception {
        dto.setTerm(WebParamUtils.getUTF8Param(dto.getTerm()));
        ValidationUtils.assertTrue(dto.getUser() != null && dto.getUser().getId() != null, "user.id参数不能为空");
        List<QuestionDTO> questionDTOs = questionService.find(dto, page);

        return toApiOutput(questionDTOs, QuestionAPIDTO.class, QuestionInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping(value = "ask", method = RequestMethod.POST)
    public String ask(QuestionDTO dto, Integer pageNum, Integer numPerPage) throws Exception {
        ValidationUtils.assertTrue(StringUtils.isNotBlank(dto.getTitle()) && StringUtils.isNotBlank(dto.getContent()), "咨询的标题和内容不能为空");
        ValidationUtils.assertTrue(dto.getUser() != null && dto.getUser().getId() != null, "user.id参数不能为空");
        Long id = questionService.create(dto);
        QuestionDTO questionDTO = questionService.load(id);
        List<QuestionDTO> questionDTOs = Lists.newArrayList(questionDTO);
        return toApiOutput(questionDTOs, QuestionAPIDTO.class, QuestionInfoAPIDTO.class);
    }
    
}
