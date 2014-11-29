package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.yishang.A.global.constant.CONSTANT;


/** 
 * 封装通讯录信息的表
 * @author MM_Zerui
 *
 */
@Table(name="t_contacts",execAfterTableCreated = "CREATE INDEX index_contacts ON t_contacts(phone)")
public class T_Contacts extends EntityBase{
	@Column(column="name")
	private String name;
	
	@Column(column="phone")
	private String phone;
	
	@Column(column="ifRegister",defaultValue=CONSTANT.DAO_CONTACTS_UNREGI)
	private int ifRegister;

	@Column(column="uid")
	private String uid;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIfRegister() {
		return ifRegister;
	}
	public void setIfRegister(int ifRegister) {
		this.ifRegister = ifRegister;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
