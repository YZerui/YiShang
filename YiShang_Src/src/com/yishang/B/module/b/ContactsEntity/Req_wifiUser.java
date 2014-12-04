package com.yishang.B.module.b.ContactsEntity;

/**
 * 获取同个Wifi下用户的请求参数
 * @author MM_Zerui
 *
 */
public class Req_wifiUser {
	private String uid;
	private String wifiMac;
	private String start;
	private String limit;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
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
