package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.wy.dto.CityDTO;
import com.daxia.wy.dto.api.CityAPIDTO;
import com.daxia.wy.dto.api.info.CityInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/city", produces = "text/html;charset=UTF-8")
public class MCityController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(CityDTO dto) throws Exception {
        List<CityDTO> citys = cityService.find(dto, null);

        return toApiOutput(citys, CityAPIDTO.class, CityInfoAPIDTO.class);
    }
    
}
