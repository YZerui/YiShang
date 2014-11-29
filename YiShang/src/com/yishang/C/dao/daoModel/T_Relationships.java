package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;
import com.yishang.C.dao.utils.Utils_DBRNote;
import com.yishang.Z.utils.FormatUtils;

/**
 * 人脉表
 * @author MM_Zerui
 *	@note rela_typeResult 值将由rela_type自动得来，无法自行插入 
 *  @note rela_typeResult 值将有
 */
@Table(name="T_Relationships")
public class T_Relationships extends EntityBase{
	
	@Column(column="rela_name")
	private String rela_name;
	
	@Column(column="rela_head")
	private String rela_head;
	
	@Column(column="rela_rank")
	private String rela_rank;
	

	@Column(column="rela_id")
	private String rela_id;
	
	@Column(column="rela_type")
	private int rela_type;
	
	@Column(column="rela_typeResult")
	private int rela_typeResult;

	@Column(column="rela_register")
	private int rela_register;
	
	@Column(column="rela_phone")
	private String rela_phone;
	
	@Column(column="rela_recentTime")
	private String rela_recentTime;
	
	@Column(column="rela_intension")
	private int rela_intension;
	
	@Column(column="self_id")
	private String self_id;
	
	@Column(column="rela_addTime")
	private String rela_addTime;
	
	
	public String getRela_head() {
		return rela_head;
	}
	public void setRela_head(String rela_head) {
		this.rela_head = rela_head;
	}
	public int getRela_typeResult() {
		return rela_typeResult;
	}
	private void setRela_typeResult(int rela_typeResult) {
		this.rela_typeResult = rela_typeResult;
	}
	public String getRela_rank() {
		return rela_rank;
	}
	public void setRela_rank(String rela_rank) {
		this.rela_rank = rela_rank;
	}
	public String getRela_addTime() {
		return rela_addTime;
	}
	public void setRela_addTime(String rela_addTime) {
		this.rela_addTime = FormatUtils.getDateValue_Default(rela_addTime);
	}
	public String getRela_phone() {
		return rela_phone;
	}
	public void setRela_phone(String rela_phone) {
		this.rela_phone = rela_phone;
	}
	public String getRela_name() {
		return rela_name;
	}
	public void setRela_name(String rela_name) {
		this.rela_name = rela_name;
	}
	public String getRela_id() {
		return rela_id;
	}
	public void setRela_id(String rela_id) {
		this.rela_id = rela_id;
	}
	public int getRela_type() {
		return rela_type;
	}
	public void setRela_type(int rela_type) {
		this.rela_type = rela_type;
		this.rela_typeResult=Utils_DBRNote.parse(rela_type);
	}
	public int getRela_register() {
		return rela_register;
	}
	public void setRela_register(int rela_register) {
		this.rela_register = rela_register;
	}
	public String getRela_recentTime() {
		return rela_recentTime;
	}
	public void setRela_recentTime(String rela_recentTime) {
		this.rela_recentTime = rela_recentTime;
	}
	
	public int getRela_intension() {
		return rela_intension;
	}
	public void setRela_intension(int rela_intension) {
		this.rela_intension = rela_intension;
	}
	public String getSelf_id() {
		return self_id;
	}
	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}

	
	
}
