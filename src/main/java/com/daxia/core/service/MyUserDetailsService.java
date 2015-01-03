package com.daxia.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daxia.core.dao.UserDAO;
import com.daxia.core.dto.UserDTO;
import com.daxia.core.model.Authority;
import com.daxia.core.model.Role;
import com.daxia.core.model.User;



@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, Serializable {
	private static Logger logger = Logger.getLogger(MyUserDetailsService.class);
    private static final long serialVersionUID = 1L;
    
	@Autowired
    private UserDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	try {
	        return doLoad(username);
        } catch (Exception e) {
        	logger.error(e, e);
        	throw new RuntimeException(e.getMessage(), e);
        }
    }

	private UserDetails doLoad(String username) {
	    UserDTO dto = new UserDTO();
    	dto.setUsername(username);
        User user = userDAO.findOne(dto);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find user by username [" + username + "]");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
	       Set<Authority> authoritiesSet = role.getAuthorities();
	       for (final Authority a : authoritiesSet) {
	    	   if (a == null) {
	    		   continue;
	    	   }
	    	   authorities.add(new GrantedAuthority() {
                   private static final long serialVersionUID = 1L;
                   @Override
                   public String getAuthority() {
                       return a.getCode();
                   }
               });
	       }
        }
        
        /*if (authorities.size() == 0) {
        	authorities.add(new GrantedAuthority() {
        		private static final long serialVersionUID = 1L;
        		
        		@Override
        		public String getAuthority() {
        			return "NormalUser";
        		}
        	});
        }*/
        
        authorities.add(new GrantedAuthority() {
    		private static final long serialVersionUID = 1L;
    		
    		@Override
    		public String getAuthority() {
    			return "manager";
    		}
    	});
        
        user.setAuthorities(authorities);
        return user;
    }

}
