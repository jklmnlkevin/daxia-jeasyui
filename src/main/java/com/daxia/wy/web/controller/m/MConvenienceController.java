package com.daxia.wy.web.controller.m;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ImageUtils;
import com.daxia.wy.dto.ConvenienceDTO;
import com.daxia.wy.dto.api.ConvenienceAPIDTO;
import com.daxia.wy.dto.api.info.ConvenienceInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/convenience", produces = "text/html;charset=UTF-8")
public class MConvenienceController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(HttpServletRequest request, ConvenienceDTO dto, MPage page) throws Exception {
    
        List<ConvenienceDTO> convenienceDTOs = convenienceService.find(dto, page);
        if (CollectionUtils.isNotEmpty(convenienceDTOs)) {
            for (ConvenienceDTO c : convenienceDTOs) {
                if (StringUtils.isNotBlank(c.getImage())) {
                    c.setImage(ImageUtils.getImageFullPath(request, c.getImage()));
                }
            }
        }

        return toApiOutput(convenienceDTOs, ConvenienceAPIDTO.class, ConvenienceInfoAPIDTO.class, null);
    }
    
}
