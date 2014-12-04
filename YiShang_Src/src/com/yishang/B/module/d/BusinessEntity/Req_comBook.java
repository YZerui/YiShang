package com.yishang.B.module.d.BusinessEntity;

/**
 * 获取企业文档
 * @author MM_Zerui
 *
 */
public class Req_comBook {
	//企业ID
	private String cid;
	private String start;
	private String limit;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
