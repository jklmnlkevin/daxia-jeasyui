package com.daxia.wy.web.controller.m;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.support.OrderQueue;
import com.daxia.wy.dto.RepairDTO;
import com.daxia.wy.dto.api.RepairAPIDTO;
import com.daxia.wy.dto.api.info.RepairInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/repair", produces = "text/html;charset=UTF-8")
public class MRepairController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(RepairDTO dto, MPage page, HttpServletRequest request) throws Exception {
        List<RepairDTO> repairDTOs = repairService.find(dto, page);
        if (CollectionUtils.isNotEmpty(repairDTOs)) {
            for (RepairDTO repairDTO : repairDTOs) {
                if (repairDTO.getImageArr().length > 0) {
                    String[] arr = new String[repairDTO.getImageArr().length];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = getCtx(request) + "/image/" + repairDTO.getImageArr()[i];
                    }
                    repairDTO.setImages(arr);
                }
            }
        }

        return toApiOutput(repairDTOs, RepairAPIDTO.class, RepairInfoAPIDTO.class, page);
    }
    
    /**
     * 申请，上报
     * @param dto
     * @param pageNum
     * @param numPerPage
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "report")
    public String report(RepairDTO dto, HttpServletRequest request) throws Exception {
        if (ArrayUtils.isNotEmpty(dto.getImages())) {
            dto.setImage(StringUtils.join(dto.getImages(), ","));
        }
        dto.setCreateTime(new Date());
        dto.setState(0);
        Long id = repairService.create(dto);
        RepairDTO repairDTO = repairService.load(id);
        if (ArrayUtils.isNotEmpty(repairDTO.getImageArr())) {
            String[] arr = new String[repairDTO.getImageArr().length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = getCtx(request) + "/image/" + repairDTO.getImageArr()[i];
            }
            repairDTO.setImages(arr);
        }

        orderQueue.put(dto.getCommunity().getId(), "有一条新的维修申请记录");
        
        return toApiOutput(repairDTO, RepairAPIDTO.class, RepairInfoAPIDTO.class);
    }
    
    @Autowired
    private OrderQueue orderQueue;
}
