package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.api.HouseKeepingAPIDTO;
import com.daxia.wy.dto.api.info.HouseKeepingInfoAPIDTO;
import com.daxia.wy.model.HouseKeeping;

@Controller
@RequestMapping(value = "/m/houseKeeping", produces = "text/html;charset=UTF-8")
public class MHouseKeepingController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(HouseKeeping dto, MPage page) throws Exception {
    
        List<HouseKeeping> houseKeepingDTOs = houseKeepingService.find(dto, page);

        return toApiOutput(houseKeepingDTOs, HouseKeepingAPIDTO.class, HouseKeepingInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping("create")
    public String create(HouseKeeping dto) throws Exception {
        Long id = houseKeepingService.create(dto);
        HouseKeeping model = houseKeepingService.load(id);
        return toApiOutput(model, HouseKeepingAPIDTO.class, HouseKeepingInfoAPIDTO.class);
    }
    
    @ResponseBody
    @RequestMapping("delete")
    public String delete(Long id) throws Exception {
        houseKeepingService.deleteById(id);
        return toJson("ok");
    }
    
}
