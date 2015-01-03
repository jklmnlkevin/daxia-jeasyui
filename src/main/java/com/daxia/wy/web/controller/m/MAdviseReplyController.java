package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.AdviseReplyDTO;
import com.daxia.wy.dto.api.AdviseReplyAPIDTO;
import com.daxia.wy.dto.api.info.AdviseReplyInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/adviseReply", produces = "text/html;charset=UTF-8")
public class MAdviseReplyController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(AdviseReplyDTO dto, MPage page) throws Exception {
    
        List<AdviseReplyDTO> adviseReplyDTOs = adviseReplyService.find(dto, page);

        return toApiOutput(adviseReplyDTOs, AdviseReplyAPIDTO.class, AdviseReplyInfoAPIDTO.class, page);
    }
    
}
