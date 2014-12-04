package com.yishang.B.module.b.ContactsEntity;

import com.avos.avoscloud.AVGeoPoint;

/**
 * 附近的人信息
 * @author MM_Zerui
 *
 */
public class Contacts_Nearby {
	private String user_id;
	private String user_name;
	private String user_head;
	private int user_gender;
	private String user_label;
	private AVGeoPoint locatePoint;
	private String locateCity;
	private int ifSelect;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	public int getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(int user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_label() {
		return user_label;
	}
	public void setUser_label(String user_label) {
		this.user_label = user_label;
	}
	public AVGeoPoint getLocatePoint() {
		return locatePoint;
	}
	public void setLocatePoint(AVGeoPoint locatePoint) {
		this.locatePoint = locatePoint;
	}
	public String getLocateCity() {
		return locateCity;
	}
	public void setLocateCity(String locateCity) {
		this.locateCity = locateCity;
	}
	public int getIfSelect() {
		return ifSelect;
	}
	public void setIfSelect(int ifSelect) {
		this.ifSelect = ifSelect;
	}
	
}
