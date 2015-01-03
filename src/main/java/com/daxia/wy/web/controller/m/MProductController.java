package com.daxia.wy.web.controller.m;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.MPage;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.ProductDTO;
import com.daxia.wy.dto.api.ProductAPIDTO;
import com.daxia.wy.dto.api.info.ProductInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/product", produces = "text/html;charset=UTF-8")
public class MProductController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(ProductDTO dto, MPage page) throws Exception {
        ValidationUtils.assertTrue(dto.getCategory() != null && dto.getCategory().getId() != null, "请传入产品类别");
        List<ProductDTO> productDTOs = productService.find(dto, page);

        return toApiOutput(productDTOs, ProductAPIDTO.class, ProductInfoAPIDTO.class, page);
    }
    
}
