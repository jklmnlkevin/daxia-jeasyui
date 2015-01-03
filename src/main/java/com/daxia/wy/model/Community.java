package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;

/**
 * Community
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "community") // 指定与数据库映射的表名
public class Community extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 省份
	 */
	@ManyToOne
	@JoinColumn(name = "province_id")
    private Province province;
	/**
	 * 城市
	 */
	@ManyToOne
	@JoinColumn(name = "city_id")
    private City city;
	/**
	 * 地区
	 */
	@ManyToOne
	@JoinColumn(name = "district_id")
    private District district;
	/**
	 * 物业电话
	 */
	@Column(name = "telephone")
    private String telephone;
	/**
	 * 物业手机
	 */
	@Column(name = "mobilephone")
    private String mobilephone;
	/**
	 * 联系人
	 */
	@Column(name = "contacter")
    private String contacter;
	/**
	 * 地址
	 */
	@Column(name = "address")
    private String address;

	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/** 
	 * 获取值：id
	 */
	public Long getId() {
    	return id;
    }
	
	/** 
	 * 设置值：id
	 */    
    public void setId(Long id) {
    	this.id = id;
    }
	
	/** 
	 * 获取值：名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：名称
	 */    
    public void setName(String name) {
    	this.name = name;
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
	 * 获取值：物业电话
	 */
	public String getTelephone() {
    	return telephone;
    }
	
	/** 
	 * 设置值：物业电话
	 */    
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
	
	/** 
	 * 获取值：物业手机
	 */
	public String getMobilephone() {
    	return mobilephone;
    }
	
	/** 
	 * 设置值：物业手机
	 */    
    public void setMobilephone(String mobilephone) {
    	this.mobilephone = mobilephone;
    }
	
	/** 
	 * 获取值：联系人
	 */
	public String getContacter() {
    	return contacter;
    }
	
	/** 
	 * 设置值：联系人
	 */    
    public void setContacter(String contacter) {
    	this.contacter = contacter;
    }
	
	/** 
	 * 获取值：地址
	 */
	public String getAddress() {
    	return address;
    }
	
	/** 
	 * 设置值：地址
	 */    
    public void setAddress(String address) {
    	this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
}
