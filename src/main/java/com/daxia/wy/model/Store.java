package com.daxia.wy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;



/**
 * store
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "store") // 指定与数据库映射的表名
public class Store extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

    /**
     * 商家名称
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 百度纬度
     */
    private Double baiduLat;
    /**
     * 百度经度
     */
    private Double baiduLong;
    /**
     * google纬度
     */
    private Double googleLat;
    /**
     * google经度
     */
    private Double googleLong;
    /**
     * 公交站
     */
    private String busStop;
    /**
     * 商家图片
     */
    private String storeImage;
    /**
     * 联系电话
     */
    private String phone;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * 获取值 ：商家名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置值 ：商家名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获取值 ：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置值 ：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 获取值 ：百度纬度
     */
    public Double getBaiduLat() {
        return baiduLat;
    }
    /**
     * 设置值 ：百度纬度
     */
    public void setBaiduLat(Double baiduLat) {
        this.baiduLat = baiduLat;
    }
    /**
     * 获取值 ：百度经度
     */
    public Double getBaiduLong() {
        return baiduLong;
    }
    /**
     * 设置值 ：百度经度
     */
    public void setBaiduLong(Double baiduLong) {
        this.baiduLong = baiduLong;
    }
    /**
     * 获取值 ：google纬度
     */
    public Double getGoogleLat() {
        return googleLat;
    }
    /**
     * 设置值 ：google纬度
     */
    public void setGoogleLat(Double googleLat) {
        this.googleLat = googleLat;
    }
    /**
     * 获取值 ：google经度
     */
    public Double getGoogleLong() {
        return googleLong;
    }
    /**
     * 设置值 ：google经度
     */
    public void setGoogleLong(Double googleLong) {
        this.googleLong = googleLong;
    }
    /**
     * 获取值 ：公交站
     */
    public String getBusStop() {
        return busStop;
    }
    /**
     * 设置值 ：公交站
     */
    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }
    /**
     * 获取值 ：商家图片
     */
    public String getStoreImage() {
        return storeImage;
    }
    /**
     * 设置值 ：商家图片
     */
    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }
    /**
     * 获取值 ：联系电话
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置值 ：联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
