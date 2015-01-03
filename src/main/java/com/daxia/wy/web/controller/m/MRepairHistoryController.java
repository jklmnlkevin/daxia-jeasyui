package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.RepairHistoryDTO;
import com.daxia.wy.dto.api.RepairHistoryAPIDTO;
import com.daxia.wy.dto.api.info.RepairHistoryInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/repairHistory", produces = "text/html;charset=UTF-8")
public class MRepairHistoryController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(RepairHistoryDTO dto, MPage page) throws Exception {
    
        List<RepairHistoryDTO> repairHistoryDTOs = repairHistoryService.find(dto, page);

        return toApiOutput(repairHistoryDTOs, RepairHistoryAPIDTO.class, RepairHistoryInfoAPIDTO.class, page);
    }
    
}
