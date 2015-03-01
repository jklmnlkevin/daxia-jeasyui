package com.daxia.core.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.service.ApiModuleService;

@Controller
@RequestMapping(value = "/dev", produces="text/html;charset=UTF-8")
public class DevController extends BaseController {
    
    @Autowired
    private ApiModuleService apiModuleService;
    
	@RequestMapping(value = "api")
	public String upload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
	    map.put("apiModules", apiModuleService.find(new ApiModuleDTO(), null));
	    return "dev/dev_api";
	}
	
}
