package com.yishang.B.module.f.LoginEntity;

/**
 * 登录所需的参数
 * @author MM_Zerui
 *
 */
public class Req_login {
	private String mobile;
	private String password;
	private String token;
	private String eqType;
	private String avid;
	private String wifiMac;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEqType() {
		return eqType;
	}
	public void setEqType(String eqType) {
		this.eqType = eqType;
	}
	public String getAvid() {
		return avid;
	}
	public void setAvid(String avid) {
		this.avid = avid;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	
}
