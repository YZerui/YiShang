package com.yishang.B.module.d.BusinessEntity;

/**
 * 检测用户是否被企业屏蔽
 * @author MM_Zerui
 *
 */
public class Req_comIfBlock {
	private String uid;
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
