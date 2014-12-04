package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="T_MsgSeq")
public class T_MsgSeq extends EntityBase{
	@Column(column="msg_seq_sendId")
	private String msg_seq_sendId;
	
	@Column(column="msg_seq_sendHead")
	private String msg_seq_sendHead;
	
	@Column(column="msg_seq_sendName")
	private String msg_seq_sendName;
	
	@Column(column="msg_seq_resId")
	private String msg_seq_resId;
	
	@Column(column="msg_seq_resName")
	private String msg_seq_resName;
	
	@Column(column="msg_seq_comId")
	private String msg_seq_comId;
	
	@Column(column="msg_seq_comLogo")
	private String msg_seq_comLogo;
	
	@Column(column="msg_seq_comName")
	private String msg_seq_comName;
	
	@Column(column="msg_seq_source")
	private int msg_seq_source;
	
	@Column(column="msg_seq_type")
	private int msg_seq_type;
	
	@Column(column="msg_seq_content")
	private String msg_seq_content;
	
	@Column(column="msg_seq_serverTime")
	private String msg_seq_serverTime;
	
	@Column(column="msg_seq_recvTime")
	private String msg_seq_recvTime;

	@Column(column="msg_seq_success")
	private int msg_seq_success=0;
	
	@Column(column="self_id")
	private String self_id;
	
	@Column(column="msg_seq_unReadNum",defaultValue="0")
	private int msg_seq_unReadNum;
	
	


	public String getMsg_seq_resId() {
		return msg_seq_resId;
	}

	public void setMsg_seq_resId(String msg_seq_resId) {
		this.msg_seq_resId = msg_seq_resId;
	}

	public int getMsg_seq_unReadNum() {
		return msg_seq_unReadNum;
	}

	public void setMsg_seq_unReadNum(int msg_seq_unReadNum) {
		this.msg_seq_unReadNum = msg_seq_unReadNum;
	}

	public String getSelf_id() {
		return self_id;
	}

	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}

	public String getMsg_seq_resName() {
		return msg_seq_resName;
	}

	public void setMsg_seq_resName(String msg_seq_resName) {
		this.msg_seq_resName = msg_seq_resName;
	}

	public String getMsg_seq_sendId() {
		return msg_seq_sendId;
	}

	public void setMsg_seq_sendId(String msg_seq_sendId) {
		this.msg_seq_sendId = msg_seq_sendId;
	}

	public String getMsg_seq_sendHead() {
		return msg_seq_sendHead;
	}

	public void setMsg_seq_sendHead(String msg_seq_sendHead) {
		this.msg_seq_sendHead = msg_seq_sendHead;
	}

	public String getMsg_seq_sendName() {
		return msg_seq_sendName;
	}

	public void setMsg_seq_sendName(String msg_seq_sendName) {
		this.msg_seq_sendName = msg_seq_sendName;
	}

	public String getMsg_seq_comId() {
		return msg_seq_comId;
	}

	public void setMsg_seq_comId(String msg_seq_comId) {
		this.msg_seq_comId = msg_seq_comId;
	}

	public String getMsg_seq_comLogo() {
		return msg_seq_comLogo;
	}

	public void setMsg_seq_comLogo(String msg_seq_comLogo) {
		this.msg_seq_comLogo = msg_seq_comLogo;
	}

	public String getMsg_seq_comName() {
		return msg_seq_comName;
	}

	public void setMsg_seq_comName(String msg_seq_comName) {
		this.msg_seq_comName = msg_seq_comName;
	}

	public int getMsg_seq_source() {
		return msg_seq_source;
	}

	public void setMsg_seq_source(int msg_seq_source) {
		this.msg_seq_source = msg_seq_source;
	}

	public int getMsg_seq_type() {
		return msg_seq_type;
	}

	public void setMsg_seq_type(int msg_seq_type) {
		this.msg_seq_type = msg_seq_type;
	}

	public String getMsg_seq_content() {
		return msg_seq_content;
	}

	public void setMsg_seq_content(String msg_seq_content) {
		this.msg_seq_content = msg_seq_content;
	}

	public String getMsg_seq_serverTime() {
		return msg_seq_serverTime;
	}

	public void setMsg_seq_serverTime(String msg_seq_serverTime) {
		this.msg_seq_serverTime = msg_seq_serverTime;
	}

	public String getMsg_seq_recvTime() {
		return msg_seq_recvTime;
	}

	public void setMsg_seq_recvTime(String msg_seq_recvTime) {
		this.msg_seq_recvTime = msg_seq_recvTime;
	}

	public int getMsg_seq_success() {
		return msg_seq_success;
	}

	public void setMsg_seq_success(int msg_seq_success) {
		this.msg_seq_success = msg_seq_success;
	}




}
