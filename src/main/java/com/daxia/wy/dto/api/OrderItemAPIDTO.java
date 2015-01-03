package com.daxia.wy.dto.api;



public class OrderItemAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    private ProductAPIDTO product;
    private String price;
    private String amount;
    
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
    public ProductAPIDTO getProduct() {
        return product;
    }
    public void setProduct(ProductAPIDTO product) {
        this.product = product;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
}
