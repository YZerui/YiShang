package com.yishang.B.module.e.SelfEntity;

/**
 * 编辑个人信息的请求参数
 * @author MM_Zerui
 *
 */
public class Req_updateIfo {
	private String uid;
	//修改类型
	private String t;
	//修改内容
	private String c;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	
}
