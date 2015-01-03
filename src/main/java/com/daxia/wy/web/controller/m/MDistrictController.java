package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.dto.api.DistrictAPIDTO;
import com.daxia.wy.dto.api.info.DistrictInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/district", produces = "text/html;charset=UTF-8")
public class MDistrictController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(DistrictDTO dto) throws Exception {
        List<DistrictDTO> districts = districtService.find(dto, null);

        return toApiOutput(districts, DistrictAPIDTO.class, DistrictInfoAPIDTO.class);
    }
    
}
