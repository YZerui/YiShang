package com.yishang.B.module.c.ResourceEntity;

/**
 * 转发文档的方法
 * @bid 文档ID
 * @uid 用户ID
 * @tran_ini 原始转发人ID
 * @tran_send 直接转发人ID
 * @author MM_Zerui
 *
 */
public class Req_bookSend {
	private String bid;	//文档ID
	private String uid; //用户ID
	private String tran_ini; //原始转发人ID
	private String tran_send; //直接转发人ID
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTran_ini() {
		return tran_ini;
	}
	public void setTran_ini(String tran_ini) {
		this.tran_ini = tran_ini;
	}
	public String getTran_send() {
		return tran_send;
	}
	public void setTran_send(String tran_send) {
		this.tran_send = tran_send;
	}
	
}
