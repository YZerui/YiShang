package com.yishang.B.module.d.BusinessEntity;

/**
 * 搜索企业的请求参数
 * @author MM_Zerui
 *
 */
public class Req_comSearch {
	private String uid;
	private String status;
	private String kw;
	private String start;
	private String limit;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
