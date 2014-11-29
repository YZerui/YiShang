package com.yishang.A.global.application;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;

@AVClassName("T_UserPoint")
public class T_UserPoint extends AVObject{
	private String user_id;
	private String user_name;
	private String user_head;
	private int user_gender;
	private String user_label;
	private AVGeoPoint locatePoint;
	private String locateCity;
	private int ifSelect;

	public int getIfSelect() {
		return ifSelect;
	}

	public void setIfSelect(int ifSelect) {
		this.ifSelect = ifSelect;
	}

	public T_UserPoint() {
		
	}
	
	public String getUser_id() {
		return getString("user_id");
	}
	public void setUser_id(String user_id) {
		put("user_id", user_id);
	}
	public String getUser_name() {
		return getString("user_name");
	}
	public void setUser_name(String user_name) {
		put("user_name", user_name);
	}
	public String getUser_head() {
		return getString("user_head");
	}
	public void setUser_head(String user_head) {
		put("user_head",user_head);
	}
	public int getUser_gender() {
		return getInt("user_gender");
	}
	public void setUser_gender(int user_gender) {
		put("user_gender",user_gender);
	}
	public String getUser_label() {
		return getString("user_label");
	}
	public void setUser_label(String user_label) {
		put("user_label",user_label);
	}
	public AVGeoPoint getLocatePoint() {
		return getAVGeoPoint("locatePoint");
	}
	public void setLocatePoint(AVGeoPoint locatePoint) {
		put("locatePoint",locatePoint);
	}
	public String getLocateCity() {
		return getString("locateCity");
	}
	public void setLocateCity(String locateCity) {
		put("locateCity",locateCity);
	}

}
