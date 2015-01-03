package com.daxia.wy.dto.api.info;

import java.util.ArrayList;
import java.util.List;

import com.daxia.wy.dto.api.UserAPIDTO;


public class UserInfoAPIDTO extends BaseInfoAPIDTO {
    public UserInfoAPIDTO() {
    }
    public UserInfoAPIDTO(List<UserAPIDTO> userInfos) {
        this.userInfos = userInfos;
    }
	private List<UserAPIDTO> userInfos;

	/**
     * @return the userInfos
     */
    public List<UserAPIDTO> getUserInfos() {
    	return userInfos;
    }

	/**
     * @param userInfos the userInfos to set
     */
    public void setUserInfos(List<UserAPIDTO> userInfos) {
    	this.userInfos = userInfos;
    }
    
    public void addUser(UserAPIDTO user) {
    	if (userInfos == null) {
    		userInfos = new ArrayList<UserAPIDTO>();
    	}
    	userInfos.add(user);
    }
	
	
	
}
