package com.daxia.core.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Role is necessary, for access control, etc. 
 * 
 * Aug 30, 2012
 *
 */
@Entity
@Table(name = "role")
public class Role extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    @ManyToMany(targetEntity = Authority.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "roleauthority", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	private Set<Authority> authorities;
    
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", targetEntity = User.class)
	private Set<User> users;
    
    /**
     * 描述
     */
    private String description;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	/**
     * @return the authorities
     */
    public Set<Authority> getAuthorities() {
    	return authorities;
    }
	/**
     * @param authorities the authorities to set
     */
    public void setAuthorities(Set<Authority> authorities) {
    	this.authorities = authorities;
    }
	/**
     * @return the users
     */
    public Set<User> getUsers() {
    	return users;
    }
	/**
     * @param users the users to set
     */
    public void setUsers(Set<User> users) {
    	this.users = users;
    }
	/**
     * @return the description
     */
    public String getDescription() {
    	return description;
    }
	/**
     * @param description the description to set
     */
    public void setDescription(String description) {
    	this.description = description;
    }
    
}
