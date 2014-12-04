package com.yishang.B.module.b.ContactsEntity;

import com.format.utils.DataValidate;

/**
 * 服务端获取到的人脉信息
 * @author MM_Zerui
 *
 */
public class Recv_contacts {
	private String user_name;
	private String ur_type;
	private String ur_remark;
	private String ur_ob;
	private String ur_time;
	private String user_locity;
	private String user_head;
	private String user_sex;
	private String ur_source;
	private String user_lable;
	private String user_title1;//头衔
	private String ur_co;//关系强度
	
	public String getUser_lable() {
		return user_lable;
	}
	public void setUser_lable(String user_lable) {
		this.user_lable = user_lable;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUr_type() {
		if(!DataValidate.checkDataValid(ur_type)){
			return "00000";
		}
		return ur_type;
	}
	public void setUr_type(String ur_type) {
		this.ur_type = ur_type;
	}
	public String getUr_remark() {
		return ur_remark;
	}
	public void setUr_remark(String ur_remark) {
		this.ur_remark = ur_remark;
	}
	public String getUr_ob() {
		return ur_ob;
	}
	public void setUr_ob(String ur_ob) {
		this.ur_ob = ur_ob;
	}
	public String getUr_time() {
		return ur_time;
	}
	public void setUr_time(String ur_time) {
		this.ur_time = ur_time;
	}
	public String getUser_locity() {
		return user_locity;
	}
	public void setUser_locity(String user_locity) {
		this.user_locity = user_locity;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUr_source() {
		return ur_source;
	}
	public void setUr_source(String ur_source) {
		this.ur_source = ur_source;
	}
	public String getUser_title1() {
		return user_title1;
	}
	public void setUser_title1(String user_title1) {
		this.user_title1 = user_title1;
	}
	public String getUr_co() {
		return ur_co;
	}
	public void setUr_co(String ur_co) {
		this.ur_co = ur_co;
	}
	
}
