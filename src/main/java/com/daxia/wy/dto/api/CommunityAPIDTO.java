package com.daxia.wy.dto.api;


public class CommunityAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 省份
     */
    private ProvinceAPIDTO province;
    /**
     * 城市
     */
    private CityAPIDTO city;
    /**
     * 地区
     */
    private DistrictAPIDTO district;
    /**
     * 物业电话
     */
    private String telephone;
    /**
     * 物业手机
     */
    private String mobilephone;
    /**
     * 联系人
     */
    private String contacter;
    /**
     * 地址
     */
    private String address;
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
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the province
     */
    public ProvinceAPIDTO getProvince() {
        return province;
    }
    /**
     * @param province the province to set
     */
    public void setProvince(ProvinceAPIDTO province) {
        this.province = province;
    }
    /**
     * @return the city
     */
    public CityAPIDTO getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(CityAPIDTO city) {
        this.city = city;
    }
    /**
     * @return the district
     */
    public DistrictAPIDTO getDistrict() {
        return district;
    }
    /**
     * @param district the district to set
     */
    public void setDistrict(DistrictAPIDTO district) {
        this.district = district;
    }
    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * @return the mobilephone
     */
    public String getMobilephone() {
        return mobilephone;
    }
    /**
     * @param mobilephone the mobilephone to set
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    /**
     * @return the contacter
     */
    public String getContacter() {
        return contacter;
    }
    /**
     * @param contacter the contacter to set
     */
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
