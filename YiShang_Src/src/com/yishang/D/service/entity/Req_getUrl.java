package com.yishang.D.service.entity;

/**
 * 请求获取转发文档的URL
 * @author MM_Zerui
 *
 */
public class Req_getUrl {
	private String bid;
	private String ini;
	private String send;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getIni() {
		return ini;
	}
	public void setIni(String ini) {
		this.ini = ini;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	
}
