package com.daxia.wy.dto.api;



public class ConvenienceAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 服务名称
     */
    private String name;
    /**
     * 办事流程
     */
    private String flow;
    /**
     * 办公地址
     */
    private String address;
    /**
     * 办公电话
     */
    private String phone;
    /**
     * 照片
     */
    private String image;
    /**
     * 联系人
     */
    private String contacter;
    
    private String type;
    private String subType;
    
    
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
    public String getFlow() {
        return flow;
    }
    public void setFlow(String flow) {
        this.flow = flow;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getContacter() {
        return contacter;
    }
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSubType() {
        return subType;
    }
    public void setSubType(String subType) {
        this.subType = subType;
    }
    
}
