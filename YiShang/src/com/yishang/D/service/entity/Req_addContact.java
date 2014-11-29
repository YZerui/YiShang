package com.yishang.D.service.entity;

/**
 * 新增人脉接口
 * @author MM_Zerui
 *
 */
public class Req_addContact {
	private String uid;
	private String beUID;
	private String type;
	private String source;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBeUID() {
		return beUID;
	}
	public void setBeUID(String beUID) {
		this.beUID = beUID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
