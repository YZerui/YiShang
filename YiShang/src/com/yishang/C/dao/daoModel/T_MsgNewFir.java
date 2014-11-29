package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * 新朋友表（用于记录新朋友商机消息顺序表）
 * @author MM_Zerui
 *
 */
@Table(name = "T_MsgNewFriend")
public class T_MsgNewFir extends EntityBase {
	
	@Column(column = "msg_new_sendId")
	private String msg_new_sendId;
	
	@Column(column = "msg_new_sendHead")
	private String msg_new_sendHead;
	
	@Column(column = "msg_new_sendName")
	private String msg_new_sendName;
	
	@Column(column="msg_new_resId")
	private String msg_new_resId;

	@Column(column = "msg_new_resName")
	private String msg_new_resName;
	
	@Column(column = "msg_new_type")
	private int msg_new_type;
	
	@Column(column = "msg_new_content")
	private String msg_new_content;
	
	@Column(column = "msg_new_serverTime")
	private String msg_new_serverTime;
	
	@Column(column = "msg_new_recvTime")
	private String msg_new_recvTime;
	
	@Column(column = "self_id")
	private String self_id;
	
	@Column(column="msg_new_unReadNum",defaultValue="0")
	private int msg_new_unReadNum;
	
	

	public String getMsg_new_resId() {
		return msg_new_resId;
	}
	public void setMsg_new_resId(String msg_new_resId) {
		this.msg_new_resId = msg_new_resId;
	}
	public int getMsg_new_unReadNum() {
		return msg_new_unReadNum;
	}
	public void setMsg_new_unReadNum(int msg_new_unReadNum) {
		this.msg_new_unReadNum = msg_new_unReadNum;
	}
	public String getMsg_new_sendId() {
		return msg_new_sendId;
	}
	public void setMsg_new_sendId(String msg_new_sendId) {
		this.msg_new_sendId = msg_new_sendId;
	}
	public String getMsg_new_sendHead() {
		return msg_new_sendHead;
	}
	public void setMsg_new_sendHead(String msg_new_sendHead) {
		this.msg_new_sendHead = msg_new_sendHead;
	}
	public String getMsg_new_sendName() {
		return msg_new_sendName;
	}
	public void setMsg_new_sendName(String msg_new_sendName) {
		this.msg_new_sendName = msg_new_sendName;
	}
	public String getMsg_new_resName() {
		return msg_new_resName;
	}
	public void setMsg_new_resName(String msg_new_resName) {
		this.msg_new_resName = msg_new_resName;
	}
	public int getMsg_new_type() {
		return msg_new_type;
	}
	public void setMsg_new_type(int msg_new_type) {
		this.msg_new_type = msg_new_type;
	}
	public String getMsg_new_content() {
		return msg_new_content;
	}
	public void setMsg_new_content(String msg_new_content) {
		this.msg_new_content = msg_new_content;
	}
	public String getMsg_new_serverTime() {
		return msg_new_serverTime;
	}
	public void setMsg_new_serverTime(String msg_new_serverTime) {
		this.msg_new_serverTime = msg_new_serverTime;
	}
	public String getMsg_new_recvTime() {
		return msg_new_recvTime;
	}
	public void setMsg_new_recvTime(String msg_new_recvTime) {
		this.msg_new_recvTime = msg_new_recvTime;
	}
	public String getSelf_id() {
		return self_id;
	}
	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}
	

}
