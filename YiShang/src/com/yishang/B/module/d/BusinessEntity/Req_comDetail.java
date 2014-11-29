package com.yishang.B.module.d.BusinessEntity;

/**
 * 获取企业详细信息（相对某用户）
 * @author MM_Zerui
 *
 */
public class Req_comDetail {
	private String id;
	private String uid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
