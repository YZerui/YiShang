package com.yishang.B.module.d.BusinessEntity;

/**
 * 检测用户审查权的接口
 * @author MM_Zerui
 *
 */
public class Req_comUserReview {
	//企业ID
	private String uid;
	//用户ID
	private String cid;
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
	
}
