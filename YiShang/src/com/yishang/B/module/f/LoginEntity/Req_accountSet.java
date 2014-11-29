package com.yishang.B.module.f.LoginEntity;

/**
 * 提交服务端进行账户注册的Bean
 * @author MM_Zerui
 *
 */
public class Req_accountSet {
	private String mobile;
	private String password;
	private String code;
	private String smsType;
	public String getMobile() {
		return mobile;
	}
	public String getPassword() {
		return password;
	}
	public String getCode() {
		return code;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	
}
