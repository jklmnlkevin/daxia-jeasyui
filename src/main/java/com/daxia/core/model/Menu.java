
package com.daxia.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 * 
 * Sep 1, 2012
 *
 */
@Entity
@Table (name = "menu")
//@Cacheable
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Menu extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * starts with 0.
	 */
	private Integer level;
	private String name;
	private String href;
	/**
	 * this field is for dwz only.
	 */
	private String rel;

	/**
	 * 顺序哦
	 */
	private Integer seq;
	
	@OneToOne
	@JoinColumn(name = "authority_id")
	private Authority authority;

	@ManyToOne
	private Menu parent;
	// @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "parent")
	@OrderBy(value  = "level ASC, seq ASC")
	private List<Menu> children;
	@Column(name = "`show`")
	private boolean show;
	
	public Integer getLevel() {
    	return level;
    }
	public void setLevel(Integer level) {
    	this.level = level;
    }
	public String getName() {
    	return name;
    }
	public void setName(String name) {
    	this.name = name;
    }
	public String getHref() {
    	return href;
    }
	public void setHref(String href) {
    	this.href = href;
    }
	public String getRel() {
    	return rel;
    }
	public void setRel(String rel) {
    	this.rel = rel;
    }
	public Menu getParent() {
    	return parent;
    }
	public void setParent(Menu parent) {
    	this.parent = parent;
    }
	public List<Menu> getChildren() {
    	return children;
    }
	public void setChildren(List<Menu> children) {
    	this.children = children;
    }
	
	public void addChild(Menu child) {
		if (children == null) {
			children = new ArrayList<Menu>();
		}
		children.add(child);
	}
	public Long getId() {
    	return id;
    }
	public void setId(Long id) {
    	this.id = id;
    }
	/**
     * @return the authority
     */
    public Authority getAuthority() {
    	return authority;
    }
	/**
     * @param authority the authority to set
     */
    public void setAuthority(Authority authority) {
    	this.authority = authority;
    }
	public Integer getSeq() {
    	return seq;
    }
	public void setSeq(Integer seq) {
    	this.seq = seq;
    }
	/**
     * @return the show
     */
    public boolean getShow() {
    	return show;
    }
	/**
     * @param show the show to set
     */
    public void setShow(boolean show) {
    	this.show = show;
    }
	
}
