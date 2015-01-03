package com.daxia.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.daxia.core.model.User;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication au) throws IOException, ServletException {
		if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof User) {
		    User user = (User) au.getPrincipal();
			// request.getSession().setAttribute("isHead", user.getType() == ManagerType.Head.getValue());
		    request.getSession().setAttribute("isHead", false);
			// request.getSession().setAttribute("currentStoreId", user.getStore().getId());
		    request.getSession().setAttribute("currentStoreId", 0);
			// request.getSession().setAttribute("currentStoreName", user.getStoreName());
		    request.getSession().setAttribute("currentStoreName", "");
		    request.getSession().setAttribute("currentUser", user);
		}
		super.onAuthenticationSuccess(request, response, au);
    }

}
