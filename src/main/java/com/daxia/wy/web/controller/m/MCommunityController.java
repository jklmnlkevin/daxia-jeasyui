package com.daxia.wy.web.controller.m;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.core.util.WebParamUtils;
import com.daxia.wy.dto.CommunityAddApplyDTO;
import com.daxia.wy.dto.CommunityDTO;
import com.daxia.wy.dto.api.CommunityAPIDTO;
import com.daxia.wy.dto.api.info.CommunityInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/community", produces = "text/html;charset=UTF-8")
public class MCommunityController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(CommunityDTO dto, MPage page) throws Exception {
        dto.setName(WebParamUtils.getUTF8Param(dto.getName()));
        List<CommunityDTO> communityDTOs = communityService.find(dto, page);

        return toApiOutput(communityDTOs, CommunityAPIDTO.class, CommunityInfoAPIDTO.class, page);
    }
    
    @ResponseBody
    @RequestMapping("add")
    public String add(CommunityAddApplyDTO dto) throws Exception {
        ValidationUtils.assertTrue(StringUtils.isNotBlank(dto.getName()), "小区名不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(dto.getAddress()), "小区地址不能为空");
        ValidationUtils.assertTrue(dto.getDistrict() != null && dto.getDistrict().getId() != null, "地区不能为空");
        communityAddApplyService.create(dto);
        return toJson("true");
    }
}
