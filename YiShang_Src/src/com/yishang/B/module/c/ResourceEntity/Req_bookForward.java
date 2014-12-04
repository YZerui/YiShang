package com.yishang.B.module.c.ResourceEntity;

/**
 * 转发某文档需要的请求参数
 * @author MM_Zerui
 *
 */
public class Req_bookForward {
	private String bid;
	private String uid;
	private String tran_ini;
	private String tran_send;
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
