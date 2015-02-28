package com.daxia.core.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.daxia.core.common.Sex;
import com.daxia.core.common.UserType;

/**
 * User
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "user") // 指定与数据库映射的表名
public class User extends BaseModel implements UserDetails {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 用户名
	 */
	@Column(name = "username")
    private String username;
	
	/**
	 * 密码
	 */
	@Column(name = "password")
    private String password;

	
	/**
	 * 用户类型
	 */
	private int userType = UserType.Normal.getValue();
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 性别
	 */
	private int sex = Sex.Unknow.value();
	
	private String mobile;
	 
    @Transient
    private boolean accountNonExpired = true;
    
    @Transient
    private boolean accountNonLocked = true;
    
    @Transient
    private boolean credentialsNonExpired = true;
    
    @Transient
    private boolean  enabled = true;
    
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 一个用户有多个角色，每个角色又包括了不同的权限。
     * 所以，一个用户的权限的集合，就是这个用户的 所有的角色的 所包括的权限 的集合
     */
    @ManyToMany(targetEntity = Role.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "userrole", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles;
    
    
    
   /* @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private Set<OnlineUser> onlineUsers = new HashSet<OnlineUser>();
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private Set<Contract> contracts = new HashSet<Contract>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "send_user_id")
    private Set<Chat> chats = new HashSet<Chat>();
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "receive_user_id")
    private Set<Chat> chats2 = new HashSet<Chat>();
*/    
    /*@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private Set<Order> orders = new HashSet<Order>();*/
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @JSONField(serialize = false)
    public String getPassword() {
        return password;
    }
    @JSONField(serialize = false)
    public void setPassword(String password) {
        this.password = password;
    }
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    @JSONField(serialize = false)
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @JSONField(serialize = false)
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @JSONField(serialize = false)
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return enabled;
    }
    @JSONField(serialize = false)
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @JSONField(serialize = false)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JSONField(serialize = false)
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    /**
     * @return the roles
     */
    @JSONField(serialize = false)
    public Set<Role> getRoles() {
        return roles;
    }
    /**
     * @param roles the roles to set
     */
    @JSONField(serialize = false)
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


	
	
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

   
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    
}
