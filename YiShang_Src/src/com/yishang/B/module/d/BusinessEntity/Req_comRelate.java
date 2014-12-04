package com.yishang.B.module.d.BusinessEntity;

/**
 * 和企业进行关联操作的请求参数
 * @author MM_Zerui
 *
 */
public class Req_comRelate {
	private String cid;
	private String uid;
	private String isRelation;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIsRelation() {
		return isRelation;
	}
	public void setIsRelation(String isRelation) {
		this.isRelation = isRelation;
	}
	
}
