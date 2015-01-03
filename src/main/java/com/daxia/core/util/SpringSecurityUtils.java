package com.daxia.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.daxia.core.model.User;

public class SpringSecurityUtils {
	public static User getCurrentUser() {
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof User) {
			User user = (User) au.getPrincipal();
			return user;
		}
		return null;
	}
	
	public static Long getCurrentCommunityId() {
	    User user = getCurrentUser();
	    if (user != null && user.getCommunity() != null) {
	        return user.getCommunity().getId();
	    }
	    return null;
	}
}
