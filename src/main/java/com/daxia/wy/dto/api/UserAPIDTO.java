package com.daxia.wy.dto.api;

import org.apache.commons.lang3.StringUtils;

import com.daxia.core.util.ImageUtils;
import com.daxia.core.util.MyWebUtils;


public class UserAPIDTO {
	private String id = "";
	private String username = "";
	private String userType = "";
	private ProvinceAPIDTO province;
    /**
     * 用户卡号
     */
    private String idCard;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 楼号
     */
    private String building;
    /**
     * 门牌号
     */
    private String doorplate;
    /**
     * 业主
     */
    private String owner;
    /**
     * 业主卡号
     */
    private String ownerIdCard;
    /**
     * 地区
     */
    private DistrictAPIDTO district;
    /**
     * 城市
     */
    private CityAPIDTO city;
    private CommunityAPIDTO community;

    /**
     * push id
     */
    private String pushId;
    /**
     * 是否已认证
     */
    private String authenticated;
    /**
     * 是否接收通知公告
     */
    private String receiveNotice;
    /**
     * 头像
     */
    private String headImage;
    private String sex;
    private String pushAlias;
    private String pushTag;
    
    public String getPushAlias() {
        return username ;
    }
    public void setPushAlias(String pushAlias) {
        this.pushAlias = pushAlias;
    }
    public String getPushTag() {
        if (getCommunity() != null) {
            return "community_" + getCommunity().getId();
        } else {
            return "no_community";
        }
    }
    public void setPushTag(String pushTag) {
        this.pushTag = pushTag;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public ProvinceAPIDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceAPIDTO province) {
        this.province = province;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public String getDoorplate() {
        return doorplate;
    }
    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwnerIdCard() {
        return ownerIdCard;
    }
    public void setOwnerIdCard(String ownerIdCard) {
        this.ownerIdCard = ownerIdCard;
    }
    public DistrictAPIDTO getDistrict() {
        return district;
    }
    public void setDistrict(DistrictAPIDTO district) {
        this.district = district;
    }
    public CityAPIDTO getCity() {
        return city;
    }
    public void setCity(CityAPIDTO city) {
        this.city = city;
    }
    public CommunityAPIDTO getCommunity() {
        return community;
    }
    public void setCommunity(CommunityAPIDTO community) {
        this.community = community;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
    public String getAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }
    public String getReceiveNotice() {
        return receiveNotice;
    }
    public void setReceiveNotice(String receiveNotice) {
        this.receiveNotice = receiveNotice;
    }
    public String getHeadImage() {
        if (StringUtils.isNotBlank(headImage)) {
            if (!headImage.startsWith("http")) {
                return ImageUtils.getImageFullPath(MyWebUtils.getCurrentRequest(), headImage);
            }
        }
        return headImage;
    }
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    
}
