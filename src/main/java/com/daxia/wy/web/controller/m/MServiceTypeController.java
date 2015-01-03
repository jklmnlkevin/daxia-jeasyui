package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.dto.api.ServiceTypeAPIDTO;
import com.daxia.wy.dto.api.info.ServiceTypeInfoAPIDTO;
import com.daxia.wy.model.ServiceType;

@Controller
@RequestMapping(value = "/m/serviceType", produces = "text/html;charset=UTF-8")
public class MServiceTypeController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(ServiceType dto, MPage page) throws Exception {
    
        List<ServiceType> serviceTypeDTOs = serviceTypeService.find(dto, page);
        return toApiOutput(serviceTypeDTOs, ServiceTypeAPIDTO.class, ServiceTypeInfoAPIDTO.class, page);
    }
    
}
