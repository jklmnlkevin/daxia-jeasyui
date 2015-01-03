package com.daxia.wy.dto.api;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.daxia.core.util.ImageUtils;
import com.daxia.core.util.MyWebUtils;


public class ProductAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    
    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 价格
     */
    @Column(name = "price")
    private String price;
    /**
     * 型号
     */
    private String specification;

    /**
     * 备注
     */
    private String remark;
    /**
     * 图片路径
     */
    private String image;
    private String stock;
    /**
     * 单位
     */
    private String unit;
    private CategoryAPIDTO category;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        if (StringUtils.isNotBlank(price)) {
            if (price.endsWith(".00")) {
                price = price.replace(".00", "");
            }
        }
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getImage() {
        if (StringUtils.isNotBlank(image)) {
            if (!image.startsWith("http")) {
                return ImageUtils.getImageFullPath(MyWebUtils.getCurrentRequest(), image);
            }
        }
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public CategoryAPIDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryAPIDTO category) {
        this.category = category;
    }
    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
    
}
