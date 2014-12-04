package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.Enum_Gender;
import com.yishang.Z.utils.FormatUtils;

/**
 * 个人信息的显示文案
 * @author MM_Zerui
 *
 */
public class W_UserIfo {
	public static String name(String name){
		if(DataValidate.checkDataValid(name)){
			return name;
		}
		return "暂无姓名信息";
	}
	public static String gender(String name){
		if(name.equals(Enum_Gender.MAN.value())){
			return "男";
		}else if (name.equals(Enum_Gender.WOMEN.value())) {
			return "女";
		}
		return "未知性别";
	}
	public static String email(String email){
		if(DataValidate.checkDataValid(email)){
			return email;
		}
		return "暂无邮箱信息";
	}
	public static String birthday(String birthday){
		if(DataValidate.checkDataValid(birthday)){
			return FormatUtils.getDateTime_BIRTHDAY(birthday);
		}
		return "暂无生日信息";
	}
	public static String label(String label){
		if(DataValidate.checkDataValid(label)){
			return label;
		}
		return "暂无个人标签";
	}
	public static String phone(String phone){
		if(DataValidate.checkDataValid(phone)){
			return phone;
		}
		return "暂无手机联系方式";
	}
	public static String rank(String rank){
		if(DataValidate.checkDataValid(rank)){
			return rank;
		}
		return "暂无个人头衔";
	}
	public static String intro(String intro){
		if(DataValidate.checkDataValid(intro)){
			return intro;
		}
		return "暂无个人简介";
	}
	public static String address(String addr){
		if(DataValidate.checkDataValid(addr)){
			return addr;
		}
		return "暂无地址信息";
	}
	public static String fax(String fax){
		if(DataValidate.checkDataValid(fax)){
			return fax;
		}
		return "暂无传真号码";
	}
}
