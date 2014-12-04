package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.yishang.A.global.writting.W_Msg;

/**
 * 存储推送消息的表
 * @author MM_Zerui
 *
 */
@Table(name="T_Msg")
public class T_Msg extends EntityBase{
	@Column(column="msg_source")
	private int msg_source;
	
	@Column(column="msg_comId")
	private String msg_comId;
	
	@Column(column="msg_comLogo")
	private String msg_comLogo;
	
	@Column(column="msg_comName")
	private String msg_comName;
	
	@Column(column="msg_sendId")
	private String msg_sendId;
	
	@Column(column="msg_sendHead")
	private String msg_sendHead;
	
	@Column(column="msg_sendName")
	private String msg_sendName;
	
	@Column(column="msg_uId")
	private String msg_uId;
	
	@Column(column="msg_resId")
	private String msg_resId;
	
	@Column(column="msg_resName")
	private String msg_resName;
	
	@Column(column="msg_success",defaultValue="1")
	private int msg_success;
	
	@Column(column="msg_type")
	private int msg_type;
	
	@Column(column="msg_content")
	private String msg_content;
	
	
	@Column(column="msg_recvTime")
	private String msg_recvTime;
	
	@Column(column="msg_time")
	private String msg_time;
	
	@Column(column="msg_id")
	private long msg_id;
	
	@Column(column="self_id")
	private String self_id;
	
	

	public long getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_recvTime() {
		return msg_recvTime;
	}
	public void setMsg_recvTime(String msg_recvTime) {
		this.msg_recvTime = W_Msg.Y(msg_recvTime);
	}
	public String getMsg_resName() {
		return W_Msg.Y(msg_resName);
	}
	public void setMsg_resName(String msg_resName) {
		this.msg_resName = msg_resName;
	}
	public String getMsg_sendId() {
		return W_Msg.Y(msg_sendId);
	}
	public void setMsg_sendId(String msg_sendId) {
		this.msg_sendId = msg_sendId;
	}

	public String getMsg_comId() {
		return  W_Msg.Y(msg_comId);
	}
	public int getMsg_success() {
		return msg_success;
	}
	public void setMsg_success(int msg_success) {
		this.msg_success = msg_success;
	}
	public void setMsg_comId(String msg_comId) {
		this.msg_comId = msg_comId;
	}
	public String getMsg_uId() {
		return W_Msg.Y(msg_uId);
	}
	public void setMsg_uId(String msg_uId) {
		this.msg_uId = msg_uId;
	}
	public String getMsg_resId() {
		return W_Msg.Y(msg_resId);
	}
	public void setMsg_resId(String msg_resId) {
		this.msg_resId = msg_resId;
	}
	public String getSelf_id() {
		return W_Msg.Y(self_id);
	}
	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}
	public String getMsg_time() {
		return W_Msg.Y(msg_time);
	}
	public void setMsg_time(String msg_time) {
		this.msg_time = msg_time;
	}
	public int getMsg_source() {
		return msg_source;
	}
	public void setMsg_source(int msg_source) {
		this.msg_source = msg_source;
	}
	public int getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_content() {
		return W_Msg.Y(msg_content);
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_comLogo() {
		return W_Msg.Y(msg_comLogo);
	}
	public void setMsg_comLogo(String msg_comLogo) {
		this.msg_comLogo = msg_comLogo;
	}
	public String getMsg_comName() {
		return W_Msg.Y(msg_comName);
	}
	public void setMsg_comName(String msg_comName) {
		this.msg_comName = msg_comName;
	}
	public String getMsg_sendHead() {
		return W_Msg.Y(msg_sendHead);
	}
	public void setMsg_sendHead(String msg_sendHead) {
		this.msg_sendHead = msg_sendHead;
	}
	public String getMsg_sendName() {
		return W_Msg.Y(msg_sendName);
	}
	public void setMsg_sendName(String msg_sendName) {
		this.msg_sendName = msg_sendName;
	}
	
}
