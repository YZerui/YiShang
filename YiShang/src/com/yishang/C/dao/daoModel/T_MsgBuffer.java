package com.yishang.C.dao.daoModel;

import com.exception.utils.P;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;
import com.yishang.Z.utils.FormatUtils;

@Table(name="T_MsgBuffer")
public class T_MsgBuffer extends EntityBase{
	@Column(column="msg_ini")
	private String msg_ini;
	
	@Column(column="msg_receive")
	private String msg_receive;
	
	@Column(column="ur_type")
	private String ur_type;
	
	@Column(column="ur_co")
	private String ur_co;
	
	@Column(column="msg_send")
	private int msg_send;
	
	@Column(column="msg_type")
	private int msg_type;
	
	@Column(column="msg_user")
	private String msg_user;
	
	@Column(column="msg_com")
	private String msg_com;
	
	@Column(column="msg_content")
	private String msg_content;
	
	@Unique
	@Column(column="msg_id")
	private long msg_id;
	
	@Column(column="name")
	private String name;
	
	@Column(column="head")
	private String head;
	
	@Column(column="msg_time")
	private String msg_time;
	
	@Column(column="book_id")
	private String book_id;

	
	public String getMsg_ini() {
		return msg_ini;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public void setMsg_ini(String msg_ini) {
		this.msg_ini = msg_ini;
	}

	public String getMsg_receive() {
		return msg_receive;
	}

	public void setMsg_receive(String msg_receive) {
		this.msg_receive = msg_receive;
	}

	public String getUr_type() {
		return ur_type;
	}

	public void setUr_type(String ur_type) {
		this.ur_type = ur_type;
	}



	

	
	public String getUr_co() {
		return ur_co;
	}

	public void setUr_co(String ur_co) {
		this.ur_co = ur_co;
	}

	public int getMsg_send() {
		return msg_send;
	}

	public void setMsg_send(int msg_send) {
		this.msg_send = msg_send;
	}



	public int getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg_user() {
		return msg_user;
	}

	public void setMsg_user(String msg_user) {
		this.msg_user = msg_user;
	}

	public String getMsg_com() {
		return msg_com;
	}

	public void setMsg_com(String msg_com) {
		this.msg_com = msg_com;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	

	public long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getMsg_time() {
//		P.v("推送产生时间:"+FormatUtils.getDateValue_Default(msg_time));
//		return FormatUtils.getDateValue_Default(msg_time);
		return msg_time;
	}

	public void setMsg_time(String msg_time) {
		this.msg_time = msg_time;
	}

//	public String getSelf_id() {
//		return self_id;
//	}
//
//	public void setSelf_id(String self_id) {
//		this.self_id = self_id;
//	}
	
	

}
