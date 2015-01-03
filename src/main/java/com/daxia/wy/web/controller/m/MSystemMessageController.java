package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.SystemMessageDTO;
import com.daxia.wy.dto.api.SystemMessageAPIDTO;
import com.daxia.wy.dto.api.info.SystemMessageInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/systemMessage", produces = "text/html;charset=UTF-8")
public class MSystemMessageController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(SystemMessageDTO dto, MPage page) throws Exception {
        dto.setQueryNonCommunity(true);
        List<SystemMessageDTO> systemMessageDTOs = systemMessageService.find(dto, page);

        return toApiOutput(systemMessageDTOs, SystemMessageAPIDTO.class, SystemMessageInfoAPIDTO.class, page);
    }
    
}
