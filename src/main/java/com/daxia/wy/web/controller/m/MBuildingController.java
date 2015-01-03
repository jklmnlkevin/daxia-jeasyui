package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.BuildingDTO;
import com.daxia.wy.dto.api.BuildingAPIDTO;
import com.daxia.wy.dto.api.info.BuildingInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/building", produces = "text/html;charset=UTF-8")
public class MBuildingController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(BuildingDTO dto, MPage page) throws Exception {
        ValidationUtils.assertTrue(dto.getCommunity() != null && dto.getCommunity().getId() != null, "必须传入community.id参数");
        List<BuildingDTO> buildingDTOs = buildingService.find(dto, page);

        return toApiOutput(buildingDTOs, BuildingAPIDTO.class, BuildingInfoAPIDTO.class, page);
    }
    
}
