package com.daxia.core.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 权限 
 */
@Entity
@Table(name = "authority")
public class Authority extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 中文名称，如“系统管理”，“系统管理.菜单管理”之类的
     */
    private String name;
    /**
     * 英文code，如"system", "system.menu"之类的
     */
    private String code;
    
    @ManyToOne()
	@JoinColumn(name = "parent_authority_id")
	private Authority parentAuthority;

	@OneToMany(mappedBy = "parentAuthority")
	private List<Authority> children;
	
	@Transient
	private boolean contains;
	
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
     * @return the parentAuthority
     */
    public Authority getParentAuthority() {
    	return parentAuthority;
    }
	/**
     * @param parentAuthority the parentAuthority to set
     */
    public void setParentAuthority(Authority parentAuthority) {
    	this.parentAuthority = parentAuthority;
    }
	/**
     * @return the children
     */
    public List<Authority> getChildren() {
    	return children;
    }
	/**
     * @param children the children to set
     */
    public void setChildren(List<Authority> children) {
    	this.children = children;
    }

    /**
	 * @return the simpleName
	 */
	public String getSimpleName() {
		if (name == null) {
			return null;
		} else {
			String[] arr = name.split("\\.");
			return arr[arr.length - 1];
		}
	}

	/**
	 * @param simpleName
	 *            the simpleName to set
	 */
	public void setSimpleName(String simpleName) {
		// this.simpleName = simpleName;
	}
	/**
     * @return the contains
     */
    public boolean isContains() {
    	return contains;
    }
	/**
     * @param contains the contains to set
     */
    public void setContains(boolean contains) {
    	this.contains = contains;
    }
	/**
     * @return the code
     */
    public String getCode() {
    	return code;
    }
	/**
     * @param code the code to set
     */
    public void setCode(String code) {
    	this.code = code;
    }
	
	
}
