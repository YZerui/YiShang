package com.yishang.F.receive.model;

import com.yishang.A.global.writting.W_Msg;

public class Recv_push {
	private String alert;
	private String other;
	private String msgType;
	private String sendTime;
	private String sourceID;
	private int msgSource;
	private String msgSourceStr;
	private String msg_id;//消息ID
	private int relateS; //关系强度
	
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsgSourceStr() {
		return W_Msg.Y(msgSourceStr);
	}
	public void setMsgSourceStr(String msgSourceStr) {
		this.msgSourceStr = msgSourceStr;
	}
	public String getSourceID() {
		return W_Msg.Y(sourceID);
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getOther() {
		return W_Msg.Y(other);
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getMsgType() {
		return W_Msg.Y(msgType);
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSendTime() {
		return W_Msg.Y(sendTime);
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getMsgSource() {
		return msgSource;
	}
	public void setMsgSource(int msgSource) {
		this.msgSource = msgSource;
	}
	public int getRelateS() {
		return relateS;
	}
	public void setRelateS(int relateS) {
		this.relateS = relateS;
	}

	
}
