package com.yishang.B.module.b.ContactsEntity;

/**
 * 请求获取人脉信息的参数
 * @author MM_Zerui
 *
 */
public class Req_contacts {
	private String uid;
	private String kw;
	private String phone;
	private String start;
	private String limit;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	
}
