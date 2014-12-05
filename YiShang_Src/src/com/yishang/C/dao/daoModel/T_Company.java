package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * 商家列表信息的表
 * @author MM_Zerui
 *
 */
@Table(name="T_Company")
public class T_Company extends EntityBase{
	@Unique
	@Column(column="com_id")
	private String com_id;
	
	@Column(column="com_abb")
	private String com_abb;
	
	@Column(column="com_name")
	private String com_name;
	
	@Column(column="com_remark")
	private String com_remark;
	
	@Column(column="com_icon")
	private String com_icon;
	
	@Column(column="com_status",defaultValue="-1")
	private String com_status;
	
	@Column(column="com_state",defaultValue="-1")
	private int com_state;
	
	@Column(column="com_relate",defaultValue="-1")
	private int com_relate;
	
	@Column(column="com_review")
	private int com_review;
	
	@Column(column="com_relateTime")
	private String com_relateTime;
	
	@Column(column="self_id")
	private String self_id;
	
	

	public String getCom_remark() {
		return com_remark;
	}
	public void setCom_remark(String com_remark) {
		this.com_remark = com_remark;
	}
	public String getSelf_id() {
		return self_id;
	}
	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}
	public int getCom_state() {
		return com_state;
	}
	public void setCom_state(int com_state) {
		this.com_state = com_state;
	}
	public int getCom_review() {
		return com_review;
	}
	public void setCom_review(int com_review) {
		this.com_review = com_review;
	}
	public String getCom_status() {
		return com_status;
	}
	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}
	public String getCom_relateTime() {
		return com_relateTime;
	}
	public void setCom_relateTime(String com_relateTime) {
		this.com_relateTime = com_relateTime;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	
	public String getCom_abb() {
		return com_abb;
	}
	public void setCom_abb(String com_abb) {
		this.com_abb = com_abb;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_icon() {
		return com_icon;
	}
	public void setCom_icon(String com_icon) {
		this.com_icon = com_icon;
	}
	public int getCom_relate() {
		return com_relate;
	}
	public void setCom_relate(int com_relate) {
		this.com_relate = com_relate;
	}
	
}
