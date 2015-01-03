package com.daxia.wy.dto.api;

import org.apache.commons.lang3.StringUtils;

import com.daxia.core.util.ImageUtils;
import com.daxia.core.util.MyWebUtils;

public class UserSimpleAPIDTO {
    private String id = "";
    private String username = "";
    private String userType = "";
    private String headImage = "";
    private String telephone = "";
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
