package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.AdviseDTO;
import com.daxia.wy.dto.AdviseReplyDTO;
import com.daxia.wy.dto.api.AdviseAPIDTO;
import com.daxia.wy.dto.api.AdviseReplyAPIDTO;
import com.daxia.wy.dto.api.info.AdviseInfoAPIDTO;
import com.daxia.wy.dto.api.info.AdviseReplyInfoAPIDTO;
import com.daxia.wy.model.Advise;

@Controller
@RequestMapping(value = "/m/advise", produces = "text/html;charset=UTF-8")
public class MAdviseController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(AdviseDTO dto, MPage page) throws Exception {
    
        List<AdviseDTO> adviseDTOs = adviseService.find(dto, page);

        return toApiOutput(adviseDTOs, AdviseAPIDTO.class, AdviseInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping("add")
    public String add(AdviseDTO dto, MPage page) throws Exception {
        Long id = adviseService.create(dto);
        AdviseDTO adviseDTO = adviseService.load(id); 

        return toApiOutput(adviseDTO, AdviseAPIDTO.class, AdviseInfoAPIDTO.class);
    }

    @ResponseBody
    @RequestMapping("getReply")
    public String getReply(Long id, MPage page) throws Exception {
        AdviseReplyDTO query = new AdviseReplyDTO();
        query.setAdvise(new Advise());
        query.getAdvise().setId(id);

        List<AdviseReplyDTO> replies = adviseReplyService.find(query, page);
        return toApiOutput(replies, AdviseReplyAPIDTO.class, AdviseReplyInfoAPIDTO.class);
    }
}
