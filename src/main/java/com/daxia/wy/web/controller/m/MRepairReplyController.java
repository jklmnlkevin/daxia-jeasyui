package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.RepairReplyDTO;
import com.daxia.wy.dto.api.RepairReplyAPIDTO;
import com.daxia.wy.dto.api.info.RepairReplyInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/repairReply", produces = "text/html;charset=UTF-8")
public class MRepairReplyController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(RepairReplyDTO dto, MPage page) throws Exception {
        List<RepairReplyDTO> repairReplyDTOs = repairReplyService.find(dto, page);

        return toApiOutput(repairReplyDTOs, RepairReplyAPIDTO.class, RepairReplyInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping("reply")
    public String reply(RepairReplyDTO dto) throws Exception {
        Long id = repairReplyService.create(dto);
        RepairReplyDTO repairReplyDTO = repairReplyService.load(id);
        return toApiOutput(repairReplyDTO, RepairReplyAPIDTO.class, RepairReplyInfoAPIDTO.class);
    }
}
