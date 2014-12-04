package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.utils.Utils_DBRNote;

/**
 * 文档表
 * @author MM_Zerui
 *
 */
@Table(name="T_Resource")
public class T_Resource extends EntityBase{
	@Column(column="book_id")
	private String book_id;
	
	@Column(column="com_id")
	private String com_id;
	
	@Column(column="com_logo")
	private String com_logo;
	
	@Column(column="com_name")
	private String com_name;
	
	@Column(column="com_relate")
	private int com_relate;
	
	@Column(column="book_name")
	private String book_name;
	
	@Column(column="book_kw")
	private String book_kw;
	
	@Column(column="book_summary")
	private String book_summary;
	
	@Column(column="book_status")
	private int book_status;
	
	@Column(column="send_id")
	private String send_id;
	
	@Column(column="sender_name")
	private String sender_name;
	
	@Column(column="sender_head")
	private String sender_head;
	
	@Column(column="sender_freq")
	private int sender_freq;
	
	@Column(column="sender_type")
	private int sender_type;
	
	@Column(column="sender_typeResult")
	private int sender_typeResult;
	
	@Column(column="book_creater_id")
	private String book_creater_id;
	
	@Column(column="book_cTime")
	private String book_cTime;
	
	@Column(column="book_type")
	private int book_type;
	
	@Column(column="book_download")
	private int book_download;
	
	@Column(column="book_recvTime")
	private String book_recvTime;
	
	@Column(column="book_url")
	private String book_url;
	
	@Column(column="book_html")
	private String book_html;
	
	@Column(column="self_id")
	private String self_id;
	
	
	@Column(column="book_freq",defaultValue="0")
	private int book_freq;

	
	
	public String getCom_logo() {
		return com_logo;
	}

	public void setCom_logo(String com_logo) {
		this.com_logo = com_logo;
	}

	public String getSender_head() {
		return sender_head;
	}

	public void setSender_head(String sender_head) {
		this.sender_head = sender_head;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getBook_html() {
		return book_html;
	}

	public void setBook_html(String book_html) {
		this.book_html = book_html;
	}

	public int getSender_typeResult() {
		return sender_typeResult;
	}

	private void setSender_typeResult(int sender_typeResult) {
		this.sender_typeResult = sender_typeResult;
	}

	public int getCom_relate() {
		return com_relate;
	}

	public void setCom_relate(int com_relate) {
		this.com_relate = com_relate;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_kw() {
		return book_kw;
	}

	public void setBook_kw(String book_kw) {
		this.book_kw = book_kw;
	}

	public String getBook_summary() {
		return book_summary;
	}

	public void setBook_summary(String book_summary) {
		this.book_summary = book_summary;
	}

	public int getBook_status() {
		return book_status;
	}

	public void setBook_status(int book_status) {
		this.book_status = book_status;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public int getSender_freq() {
		return sender_freq;
	}

	public void setSender_freq(int sender_freq) {
		this.sender_freq = sender_freq;
	}

	public int getSender_type() {
		return sender_type;
	}

	public void setSender_type(int sender_type) {
		this.sender_type = sender_type;
		//同时
		setSender_typeResult(Utils_DBRNote.parse(sender_type));
	}

	public String getBook_creater_id() {
		return W_Msg.Y(book_creater_id);
	}

	public void setBook_creater_id(String book_creater_id) {
		this.book_creater_id = book_creater_id;
	}

	public String getBook_cTime() {
		return book_cTime;
	}

	public void setBook_cTime(String book_cTime) {
		this.book_cTime = book_cTime;
	}

	public int getBook_type() {
		return book_type;
	}

	public void setBook_type(int book_type) {
		this.book_type = book_type;
	}

	public int getBook_download() {
		return book_download;
	}

	public void setBook_download(int book_download) {
		this.book_download = book_download;
	}

	public String getBook_recvTime() {
		return book_recvTime;
	}

	public void setBook_recvTime(String book_recvTime) {
		this.book_recvTime = book_recvTime;
	}

	public String getBook_url() {
		return book_url;
	}

	public void setBook_url(String book_url) {
		this.book_url = book_url;
	}

	public String getSelf_id() {
		return self_id;
	}

	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}

	public int getBook_freq() {
		return book_freq;
	}

	public void setBook_freq(int book_freq) {
		this.book_freq = book_freq;
	}


	
}
