package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.DoorplateDTO;
import com.daxia.wy.dto.api.DoorplateAPIDTO;
import com.daxia.wy.dto.api.info.DoorplateInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/doorplate", produces = "text/html;charset=UTF-8")
public class MDoorplateController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(DoorplateDTO dto, MPage page) throws Exception {
        ValidationUtils.assertTrue(dto.getBuilding() != null && dto.getBuilding().getId() != null, "必须传入building.id参数");
        List<DoorplateDTO> doorplateDTOs = doorplateService.find(dto, page);

        return toApiOutput(doorplateDTOs, DoorplateAPIDTO.class, DoorplateInfoAPIDTO.class, page);
    }
    
}
