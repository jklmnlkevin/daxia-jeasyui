package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.CategoryAPIDTO;

public class CategoryInfoAPIDTO extends BaseInfoAPIDTO {
    
    public CategoryInfoAPIDTO(List<CategoryAPIDTO> categoryInfos) {
        super();
        this.categoryInfos = categoryInfos;
    }

    private List<CategoryAPIDTO> categoryInfos;

    /**
     * @return the categoryInfos
     */
    public List<CategoryAPIDTO> getCategoryInfos() {
        return categoryInfos;
    }

    /**
     * @param categoryInfos the categoryInfos to set
     */
    public void setCategoryInfos(List<CategoryAPIDTO> categoryInfos) {
        this.categoryInfos = categoryInfos;
    }
    
}
