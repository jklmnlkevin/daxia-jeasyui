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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.daxia.core.common.Sex;
import com.daxia.core.common.UserType;
import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.model.City;
import com.daxia.wy.model.Community;
import com.daxia.wy.model.District;
import com.daxia.wy.model.Province;

/**
 * User
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "user") // 指定与数据库映射的表名
public class User extends BaseModel implements UserDetails, ICommunityRelatedModel {
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
	 * 用户卡号
	 */
    private String idCard;
	/**
	 * 电话
	 */
	@Column(name = "telephone")
    private String telephone;
	/**
	 * 经度
	 */
	@Column(name = "longitude")
    private String longitude;
	/**
	 * 纬度
	 */
	@Column(name = "latitude")
    private String latitude;
	/**
	 * 楼号
	 */
	@Column(name = "building")
    private String building;
	/**
	 * 门牌号
	 */
	@Column(name = "doorplate")
    private String doorplate;
	/**
	 * 业主
	 */
	@Column(name = "owner")
    private String owner;
	/**
	 * 业主卡号
	 */
    private String ownerIdCard;
	/**
	 * 省份
	 */
	@ManyToOne
	@JoinColumn(name = "province_id")
    private Province province;
	/**
	 * 地区
	 */
	@ManyToOne
	@JoinColumn(name = "district_id")
    private District district;
	/**
	 * 城市
	 */
	@ManyToOne
	@JoinColumn(name = "city_id")
    private City city;
	/**
	 * 密码
	 */
	@Column(name = "password")
    private String password;
	/**
	 * 小区
	 */
	@ManyToOne
	@JoinColumn(name = "community_id")
    private Community community;

	/**
	 * push id
	 */
	private String pushId;
	/**
	 * 是否已认证
	 */
	@Column(name = "isAuthenticated")
	private boolean authenticated;
	/**
	 * 是否接受物业缴费提醒
	 */
	@Column(name = "isReceivePayRemind")
	private boolean receivePayRemind;
	/**
	 * 是否公开手机号码
	 */
	@Column(name = "isOpenMobile")
	private boolean openMobile;
	/**
	 * 是否接收通知公告
	 */
	@Column(name = "isReceiveNotice")
	private boolean receiveNotice = true;
	
	private String authenticateImages;
	
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


	
	/** 
	 * 获取值：电话
	 */
	public String getTelephone() {
    	return telephone;
    }
	
	/** 
	 * 设置值：电话
	 */    
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
	
	/** 
	 * 获取值：经度
	 */
	public String getLongitude() {
    	return longitude;
    }
	
	/** 
	 * 设置值：经度
	 */    
    public void setLongitude(String longitude) {
    	this.longitude = longitude;
    }
	
	/** 
	 * 获取值：纬度
	 */
	public String getLatitude() {
    	return latitude;
    }
	
	/** 
	 * 设置值：纬度
	 */    
    public void setLatitude(String latitude) {
    	this.latitude = latitude;
    }
	
	/** 
	 * 获取值：楼号
	 */
	public String getBuilding() {
    	return building;
    }
	
	/** 
	 * 设置值：楼号
	 */    
    public void setBuilding(String building) {
    	this.building = building;
    }
	
	/** 
	 * 获取值：门牌号
	 */
	public String getDoorplate() {
    	return doorplate;
    }
	
	/** 
	 * 设置值：门牌号
	 */    
    public void setDoorplate(String doorplate) {
    	this.doorplate = doorplate;
    }
	
	/** 
	 * 获取值：业主
	 */
	public String getOwner() {
    	return owner;
    }
	
	/** 
	 * 设置值：业主
	 */    
    public void setOwner(String owner) {
    	this.owner = owner;
    }
	
	/** 
	 * 获取值：省份
	 */
	public Province getProvince() {
    	return province;
    }
	
	/** 
	 * 设置值：省份
	 */    
    public void setProvince(Province province) {
    	this.province = province;
    }
	
	/** 
	 * 获取值：地区
	 */
	public District getDistrict() {
    	return district;
    }
	
	/** 
	 * 设置值：地区
	 */    
    public void setDistrict(District district) {
    	this.district = district;
    }
	
	/** 
	 * 获取值：城市
	 */
	public City getCity() {
    	return city;
    }
	
	/** 
	 * 设置值：城市
	 */    
    public void setCity(City city) {
    	this.city = city;
    }
	
	/** 
	 * 获取值：小区
	 */
	public Community getCommunity() {
    	return community;
    }
	
	/** 
	 * 设置值：小区
	 */    
    public void setCommunity(Community community) {
    	this.community = community;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOwnerIdCard() {
        return ownerIdCard;
    }

    public void setOwnerIdCard(String ownerIdCard) {
        this.ownerIdCard = ownerIdCard;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isReceiveNotice() {
        return receiveNotice;
    }

    public void setReceiveNotice(boolean receiveNotice) {
        this.receiveNotice = receiveNotice;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isReceivePayRemind() {
        return receivePayRemind;
    }

    public void setReceivePayRemind(boolean receivePayRemind) {
        this.receivePayRemind = receivePayRemind;
    }

    public boolean isOpenMobile() {
        return openMobile;
    }

    public void setOpenMobile(boolean openMobile) {
        this.openMobile = openMobile;
    }

    public String getAuthenticateImages() {
        return authenticateImages;
    }

    public void setAuthenticateImages(String authenticateImages) {
        this.authenticateImages = authenticateImages;
    }
    
}
