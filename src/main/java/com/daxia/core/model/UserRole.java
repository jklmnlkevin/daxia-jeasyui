package com.daxia.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * 
 * @date 2012-9-9
 */
@Entity
@Table(name = "user_role")
public class UserRole extends BaseModel {
    @Id
    private Long id;
    
    @ManyToOne
    private User user;
    @ManyToOne
    private Role role;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
    	return user;
    }
	public void setUser(User user) {
    	this.user = user;
    }
	public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
    /*
    private Long userId;
    private Long roleId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    */
}
