package com.yishang.B.module.b.ContactsEntity;

/**
 * 提交服务端进行号码验证的Bean
 * @author MM_Zerui
 *
 */
public class Req_phoneCheck {
	private String uid;
	private String phones;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	
}
