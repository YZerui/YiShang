package com.yishang.D.service.entity;

public class Req_setClient {
	private String uid; //用户本身ID
	private String cid;//对方ID
	private String bid;//文档ID
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	
}
