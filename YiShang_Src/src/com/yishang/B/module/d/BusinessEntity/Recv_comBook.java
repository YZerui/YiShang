package com.yishang.B.module.d.BusinessEntity;

/**
 * 获取企业文档的项参数信息
 * @author MM_Zerui
 *
 */
public class Recv_comBook {
	private String book_id;
	private String book_cat;
	private String book_com;
	private String book_ct;
	private String book_kw;
	private String book_name;
	private String book_status;
	private String book_sum;
	private String book_url;
	private String book_user;
	private String com_abb; //企业简称
	private String com_logo;//企业Logo
	private String forwardtimes;//转发次数
    private String interesttimes;//感兴趣次数
    private String book_readtimes;//阅读次数
    
	public String getBook_readtimes() {
		return book_readtimes;
	}
	public void setBook_readtimes(String book_readtimes) {
		this.book_readtimes = book_readtimes;
	}
	public String getForwardtimes() {
		return forwardtimes;
	}
	public void setForwardtimes(String forwardtimes) {
		this.forwardtimes = forwardtimes;
	}
	public String getInteresttimes() {
		return interesttimes;
	}
	public void setInteresttimes(String interesttimes) {
		this.interesttimes = interesttimes;
	}
	public String getCom_abb() {
		return com_abb;
	}
	public void setCom_abb(String com_abb) {
		this.com_abb = com_abb;
	}
	public String getCom_logo() {
		return com_logo;
	}
	public void setCom_logo(String com_logo) {
		this.com_logo = com_logo;
	}
	public String getBook_url() {
		return book_url;
	}
	public void setBook_url(String book_url) {
		this.book_url = book_url;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_cat() {
		return book_cat;
	}
	public void setBook_cat(String book_cat) {
		this.book_cat = book_cat;
	}
	public String getBook_com() {
		return book_com;
	}
	public void setBook_com(String book_com) {
		this.book_com = book_com;
	}
	public String getBook_ct() {
		return book_ct;
	}
	public void setBook_ct(String book_ct) {
		this.book_ct = book_ct;
	}
	public String getBook_kw() {
		return book_kw;
	}
	public void setBook_kw(String book_kw) {
		this.book_kw = book_kw;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_status() {
		return book_status;
	}
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	public String getBook_sum() {
		return book_sum;
	}
	public void setBook_sum(String book_sum) {
		this.book_sum = book_sum;
	}
	public String getBook_user() {
		return book_user;
	}
	public void setBook_user(String book_user) {
		this.book_user = book_user;
	}
	
}
