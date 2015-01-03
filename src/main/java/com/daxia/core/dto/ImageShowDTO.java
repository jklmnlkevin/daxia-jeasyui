package com.daxia.core.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * 商家图片展示辅助类
 * 
 * */
public class ImageShowDTO {
	
	/**
	 * 图片类型
	 * */
	private String imageType;
	/**
	 * 图片名称
	 * */
	private String imageName;
	
	/**
	 * 缩略图
	 * */
	private String imageNameSmall;
	
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageNameSmall() {
		if(StringUtils.isBlank(imageNameSmall)) {
			return null;
		}else {
			return imageNameSmall.replace(".", ".thumbs.");
		}
	}
	public void setImageNameSmall(String imageNameSmall) {
		this.imageNameSmall = imageNameSmall;
	}
	
}
