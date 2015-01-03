package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.wy.common.CategoryType;
import com.daxia.wy.dto.CategoryDTO;
import com.daxia.wy.dto.api.CategoryAPIDTO;
import com.daxia.wy.dto.api.info.CategoryInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/category", produces = "text/html;charset=UTF-8")
public class MCategoryController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(CategoryDTO dto, MPage page) throws Exception {
        if (dto.getType() == null) {
            dto.setType(CategoryType.Market.getValue());
        }
        List<CategoryDTO> categoryDTOs = categoryService.find(dto, null);

        return toApiOutput(categoryDTOs, CategoryAPIDTO.class, CategoryInfoAPIDTO.class, null);
    }
    
}
