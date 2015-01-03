package com.daxia.wy.pojo;

/**
 * 微信通用接口凭证,普通对象
 * @author zzc
 * @email zzc6055@gmail.com
 * @version 1.0
 * @since 2014-1-23
 */
public class AccessToken {
	// 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	} 
    
}
