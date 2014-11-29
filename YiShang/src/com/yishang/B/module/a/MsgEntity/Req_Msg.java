package com.yishang.B.module.a.MsgEntity;

/**
 * 商机接口的请求
 * @author MM_Zerui
 *
 */
public class Req_Msg {
	private String uid;
	private String sendId;
	private String sendSource;
	private String time;
	private String msgID;
	private String start;
	private String limit;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getSendSource() {
		return sendSource;
	}
	public void setSendSource(String sendSource) {
		this.sendSource = sendSource;
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
