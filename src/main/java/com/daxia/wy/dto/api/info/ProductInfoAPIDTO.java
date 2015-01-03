package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.ProductAPIDTO;

public class ProductInfoAPIDTO extends BaseInfoAPIDTO {
    
    public ProductInfoAPIDTO(List<ProductAPIDTO> productInfos) {
        super();
        this.productInfos = productInfos;
    }

    private List<ProductAPIDTO> productInfos;

    /**
     * @return the productInfos
     */
    public List<ProductAPIDTO> getProductInfos() {
        return productInfos;
    }

    /**
     * @param productInfos the productInfos to set
     */
    public void setProductInfos(List<ProductAPIDTO> productInfos) {
        this.productInfos = productInfos;
    }
    
}
