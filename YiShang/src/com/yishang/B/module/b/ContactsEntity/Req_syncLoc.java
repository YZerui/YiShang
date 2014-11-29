package com.yishang.B.module.b.ContactsEntity;

/**
 * 请求同步经纬度的参数
 * @author MM_Zerui
 *
 */
public class Req_syncLoc {
	private String uid;
	private String user_long;
	private String user_lat;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUser_long() {
		return user_long;
	}
	public void setUser_long(String user_long) {
		this.user_long = user_long;
	}
	public String getUser_lat() {
		return user_lat;
	}
	public void setUser_lat(String user_lat) {
		this.user_lat = user_lat;
	}
	
}
